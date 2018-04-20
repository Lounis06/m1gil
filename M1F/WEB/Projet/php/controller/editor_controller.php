<!--
	editor_controller.php : Gère la partie contrôleur associé 
	à la page d'édition. Ce module fera appel au javascript afin de 
	valider les saisies lors de l'édition.
-->
<?php
	// Initalisation
	require_once("../model/db/DBConnector.php");
	$DBC = new DBConnector();

	/**
	 * Fonction récursive pour construire l'arborescence des catégories
	 */
	function getCategoriesList($cats, $connexion, $tab) {
		if (count($cats) == 0) {
			return array();
		}

		foreach ($cats as $cat) {
			array_push($tab, $cat);
			$children = $cat->getChildren($connexion);

			$childTree = getCategoriesList($children, $connexion, array());
			$tab = array_merge($tab, $childTree);
		}
		return $tab;
	}

	// Chargement de l'arbre des catégories disponibles
	$categories = getCategoriesList(
		Category::load($DBC->getConnexion(), array("parent" => null)),
		$DBC->getConnexion(),
		array()
	);
?>

<div id="editor">
	<form id="editor_form" action="../model/editor_model.php" method="POST" onsubmit="prepareSubmit()";>

		<!-- Editions des propriétés du document -->
		<div id="properties_editor">
			<h2> Propriétés de l'article</h2>
			<div class="property">
				<h3> Abstract </h3>
				<div id="abstract_window" class="text_window" contenteditable="true">
					<?php if (isset($_SESSION['edit']['abstract'])) echo $_SESSION['edit']['abstract']; ?>
				</div>
				<input id="abstract_input" type="hidden" name="abstract">
			</div>
			<div id="property_container">
				<div id="select_category" class="property">
					<h3> Catégorie : </h3>
					<select name="category">
					<?php 
						foreach ($categories as $cat) {
							$depth = $cat->getDepth($DBC->getConnexion());
					?>
						<option value=<?php echo '"'.$cat->getID().'"'; ?>
							class=<?php if ($depth == 0) echo "main_category"; ?>>
							<?php
								$str = $cat->get('name');
								for ($k = 0; $k < $depth; ++$k) {
									$str = "  | ".$str;
								}
								echo $str;
							?>
						</option>
					<?php } ?>
					</select>
				</div>
				<div id="keywords_edit" class="property">
					<h3> Mot(s)-clé(s) : </h3>
					<div id="keywords_window" class="text_window" contenteditable="true">
						<?php if (isset($_SESSION['edit']['keywords'])) echo $_SESSION['edit']['keywords']; ?>
					</div>
					<input id="keywords_input" type="hidden" name="keywords">
				</div>
			</div>
			<div id="hidden_properties">
				<input type="hidden" name="author" 
				value=<?php echo $_SESSION['user']['idusr']; ?>>
				<input type="hidden" name="publishingDate" 
				value=<?php
					$d = getdate();
					echo '"'.$d['mday']."/".$d['mon']."/".$d['year'].'"';
				?>>
			</div>
		</div>
		<hr class="content_separator" />

		<!-- Nom de l'article -->
		<h2 id="article_title" contenteditable="true"> 
			<?php if (isset($_SESSION['edit']['title'])) {
				echo $_SESSION['edit']['title'];
			} else {
				echo "Nom de l'article" ;
			}?>
		</h2>
		<input id="title_input" type="hidden" name="title">
		<!-- Editeur du document -->
		<div id="content_editor">
			<!-- Fenêtre d'édition du contenu d'article -->
			<div id="article_menu" class="content_menu">
				<div id="article_options" class="content_options">
					<span>Type d'ajout : </span>
					<select id="article_select">
						<option value="1">Section</option>
						<option value="7">Sommaire</option>
					</select>
				</div>
				<div id="article_content" class="content_bar" >
					<div id=insert_article class="option_bloc" onclick="addBloc('article_select', currentArticle);">
						<img src="../../resource/icons/add.png" alt="Ajouter un bloc" title="Ajouter un nouveau bloc du type choisi">
					</div>
					<div id=delete_article class="option_bloc" onclick="enableDelete = true;">
						<img src="../../resource/icons/delete.png" alt="Supprimer un bloc" title="Supprimer un bloc existant"
						title="">
					</div>
				</div>
			</div>

			<!-- Fenêtre d'édition du contenu d'une section spécifiée -->
			<div id="section_menu" class="content_menu">
				<h3 id="section_title" class="content_bar_title" contenteditable="true" 
					onmouseout='updateSectionTitle(this, currentSection);'>
					Nom de la section
				</h3>
				<div id="section_options" class="content_options">
					<span>Type d'ajout : </span>
					<select id="section_select">
						<option value="2">Sous-section</option>
						<option value="3">Paragraphe</option>
						<option value="4">Figure SVG</option>
						<option value="5">Image</option>
						<option value="6">Formule LaTeX</option>
						<option value="7">Sommaire</option>
					</select>
				</div>
				<div id="section_content" class="content_bar" >
					<div id="insert_section" class="option_bloc" onclick="addBloc('section_select', currentSection);">
						<img src="../../resource/icons/add.png" alt="Ajouter un bloc" title="Ajouter un nouveau bloc du type choisi">
					</div>
					<div id=delete_section class="option_bloc" onclick="enableDelete = true;">
						<img src="../../resource/icons/delete.png" alt="Supprimer un bloc" title="Supprimer un bloc existant">
					</div>
				</div>
			</div>

			<!-- Fenêtre d'édition du contenu d'une sous-section spécifiée -->
			<div id="subsection_menu" class="content_menu">
				<h3 id="subsection_title" class="content_bar_title" contenteditable="true"
					onmouseout='updateSectionTitle(this, currentSubSection);'>
					Nom de la sous section
				</h3>
				<div id="subsection_options" class="content_options">
					<span>Type d'ajout : </span>
					<select id="subsection_select">
						<option value="3">Paragraphe</option>
						<option value="4">Figure SVG</option>
						<option value="5">Image</option>
						<option value="6">Formule LaTeX</option>
						<option value="7">Sommaire</option>
					</select>
				</div>
				<div id="subsection_content" class="content_bar" >
					<div id="insert_subsection" class="option_bloc" onclick="addBloc('subsection_select', currentSubSection);">
						<img src="../../resource/icons/add.png" alt="Ajouter un bloc" title="Ajouter un nouveau bloc du type choisi">
					</div>
					<div id=delete_subsection class="option_bloc" 
					onclick="enableDelete = true;">
						<img src="../../resource/icons/delete.png" alt="Supprimer un bloc" title="Supprimer un bloc existant">
					</div>
				</div>
			</div>

			<div id="text_editor">
				<!-- Liste des options d'édition disponibles -->
				<div id="text_editor_inputs">
					<input id="bold" type="button" value="G" onclick='callEditorOption("bold");' />
					<input id="italic" type="button" value="I" onclick='callEditorOption("italic");' />
					<input id="underline" type="button" value="S" onclick='callEditorOption("underline");' />
				</div>
				<!-- Fenêtre d'édition -->
				<div id="text_editor_legend" class="text_window" contenteditable="true"></div>
				<div id="text_editor_content" class="text_window" contenteditable="true"></div>
				<hr class="content_separator" />
			</div>
			
			<div id="upload_editor">
				<!-- Fenêtre de chargement -->
				<div id="upload_editor_legend" class="text_window" contenteditable="true"></div>
				<input id="upload_editor_content" type="file" class="text_window">
				<hr class="content_separator" />
			</div>
		</div>
		

		<!-- Fenêtre de prévisualisation -->
		<div id="preview">
			<div id="preview_content" class="text_window"></div>
			
			<hr class="content_separator" />
		</div>

		
		<div id="main_inputs">
			<!-- Boutons de prévisualisation -->
			<input id="preview_show" type="button" value="Prévisualiser l'article" 
			onclick="openPreview();">
			<input id="preview_hide" type="button" value="Fermer la prévisualisation" 
			onclick="closePreview();">

			<!-- Bouton de prévisualisation -->
			<input id="submit" type="submit" name="submit" value="Publier !">
			<input id="content_input" name="content" type="hidden">
		</div>
	</form>
</div>