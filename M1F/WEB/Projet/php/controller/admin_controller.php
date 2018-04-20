<!--
	admin_controller.php : Définit un contrôleur de 
	l'interface d'administration, permettant d'effectuer 
	des modifications sur un type d'objet particulier

	Cette interface étant généraliste à tout type de DBObject. Plusieurs
	variables doivent être définies au préalable, avant inclusion de ce contrôleur.

	Variables utilisées :
		- $_ADMIN['model'] : L'URL du modèle PHP spécifique à ce contrôleur
		- $_ADMIN['class'] : Le nom du type d'objet manipulé par ce contrôleur
		- $_ADMIN['tableName'] : Le nom de la table associée 
		au type d'objet à utiliser.
		- $_ADMIN['id'] : L'attribut du type d'objet, servant d'identificateur
		auprès de la base de données.
		- $_ADMIN['editableKeys'] : L'ensemble des attributs du type d'objet édité
		à faire apparaître dans l'interface,, pouvant y être modifiés
		- $_ADMIN['uneditableKeys'] : L'ensemble des attributs qui apparaîtront dans
		l'interface, mais qui ne seront pas éditables.
		- $_ADMIN['desc'] : Les descriptions textuelles des attributs.
		(nom de colonnes dans l'interface)
		- $_ADMIN['crits'] : Les conditions devant être vérifiées par 
		les objets pour pouvoir être éditables.
-->

<?php
	// Initialisation des objets requis
	require_once("../model/db/DBConnector.php");
	require_once("../model/db/DBObject.php");

	// Vérification des informations à utiliser
	foreach (array('model', 'tableName', 'id', 'editableKeys', 
		'uneditableKeys', 'desc', 'crits') as $key) {

		if (!isset($_ADMIN[$key])) {
			$_SESSION['error'] = "Création d'interface impossible : Information ".$key." manquante";
			header("location:home.php");
		}
	}

	// Initialisation des informations requises
	$keys = array_merge($_ADMIN['uneditableKeys'], $_ADMIN['editableKeys']);
	$controllerId = "admin_".$_ADMIN["tableName"];

	// Chargement des objets à éditer
	$objs = AbstractDBObject::extractFromTable(
		$DBC->getConnexion(), 
		$_ADMIN['tableName'],
		$_ADMIN['crits']
	);
?>


<!-- Fonction de mise-à-jour des variables du contrôleur -->
<script>
	<?php $infosFunction = "set".$_ADMIN["tableName"]."Infos"; ?>
	function <?php echo $infosFunction; ?>() {
		// ID du bloc table associé à ce contrôleur
		controllerId = <?php echo '"'.$controllerId.'"'; ?>;

		// Nom du modèle PHP associé à ce contrôleur
		url = <?php echo '"'.$_ADMIN['model'].'"'; ?>;

		// Nom de l'attribut ID pour ce type d'objet
		idName = <?php echo '"'.$_ADMIN['id'].'"'; ?>

		// Nom des attributs de ce type d'objet
		keys = [];
		<?php foreach ($keys as $key) { ?>
			keys.push(<?php echo '"'.$key.'"'; ?>);
		<?php } ?>
	}
</script>

<!-- Création de la table d'édition - id : admin_(nom de la table) -->
<table id=<?php echo $controllerId; ?> class="admin_table" 
	onmouseover=<?php echo $infosFunction.'();'; ?>>
	<!-- Ligne des informations sur les colonnes attribut -->
	<thead class="admin_table_head">
		<tr>
			<?php foreach ($keys as $key) { ?>
				<th><?php echo $_ADMIN['desc'][$key]; ?></th>
			<?php } ?>
			<th colspan="2">Actions</th>
		</tr>
	</thead>

	<!-- Ligne de modification -->
	<tbody class="admin_table_body">
		<!-- Ligne d'ajout d'un nouvel utilisateur -->
		<tr class="insert">
			<?php
				foreach ($keys as $key) {
			?>
				<td class="attr" <?php 
					if (!in_array($key, $_ADMIN['uneditableKeys'])) {
					echo 'contenteditable="true"'; } 
					?>>
				</td>
			<?php
				}
			?>
			<td colspan="2">
				<img class="insert" src="../../resource/icons/add.png" width="24" height="24" alt="Ajouter" onclick='insertLine();'>
			</td>
		</tr>
		<?php
			foreach ($objs as $obj) {
				$id = $obj[$_ADMIN['id']];
				$idrow = $_ADMIN['tableName'].$id;
		?>	
			<!-- Ligne d'édition d'un utilisateur -->
			<tr id=<?php echo $idrow; ?>>
			<?php
				foreach ($keys as $key) {
			?>
				<td class="attr" <?php 
					if (!in_array($key, $_ADMIN['uneditableKeys'])) {
					echo 'contenteditable="true"'; } 
					?>>
					<?php echo $obj[$key]; ?>
				</td>
			<?php
				}
			?>
			<td>
				<img class="delete" src="../../resource/icons/delete.png" width="24" height="24" alt="Supprimer" onclick='deleteLine(<?php echo $id; ?>);'>
			</td>
			<td>
				
				<img class="save" src="../../resource/icons/save.png" width="24" height="24" alt="Sauver" onclick='saveLine(<?php echo $id; ?>, "<?php echo $idrow ?>");'>
			</td>
			</tr>
		<?php
			}
		?>
	</tbody>
</table>