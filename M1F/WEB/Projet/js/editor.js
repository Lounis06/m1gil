/**
 * editor.js : Permet de rendre l'éditeur d'articles fonctionnel.
 */

// --- Mécanisme d'initialisation de l'édition
/**
 * Initialise l'éditeur
 */
function initializeEditor(article = null) {
	// Initialisation de l'article
	currentArticle = article == null ? buildArticle() : article;

	// On associe l'élément de l'article à la fenêtre de prévisualisation
	var preview = document.getElementById("preview_content");
	preview.appendChild(currentArticle.element); 

	// Rafraîchissement de l'interface
	closePreview();
	refresh();
}


// --- Mécanismes d'interface de l'édition ---
/**
 * Gère l'activation du bouton de suppression
 */
var enableDelete = false;

/**
 * Crée un bloc interactif pour la gestion du contenu
 */
var UIBlocClass = "content_item";
function createUIBloc(obj) {
	var elt = document.createElement("div");

	// Création du bloc d'interaction
	var txt = obj.label;
	elt.appendChild(document.createTextNode(txt));
	elt.className = UIBlocClass;

	// Ajout de l'action effectuée au clic
	if (obj.clickHandler != undefined) {
		elt.addEventListener("click", function() {
			// Priorité aux actions de l'éditeur
			if (enableDelete) {
				deleteUIBloc(obj);
				return;
			}

			// Lancement de l'écouteur de l'objet
			obj.clickHandler();
		});
	}

	return elt;
}


/**
 * Supprime l'objet et son bloc interactif associé
 */
function deleteUIBloc(obj) {
	if (obj.element == null) {
		return;
	}

	// Message de confirmation
	if (confirm("Souhaitez-vous supprimer cet élément ?")) {
		obj.parent.delete(obj);
	}

	// Rafraîchissement de l'interface
	if (currentSection === obj) {
		currentSection = null;
		currentSubSection = null;
	}
	if (currentSubSection === obj) {
		currentSubSection = null;
	}
	currentItem = null;
	refresh();
}


// --- Mécanisme d'actualisation de l'interface ---
/**
 * Ouvre l'éditeur de texte, afin de modifier la valeur
 * d'un bloc donné
 */
var editor_listener = null;
function openTextEditor(obj, useEditTools = false, legendEdit = false) {
	// Fermeture éventuelle du menu d'upload
	closeUploadEditor();

	// Affichage du menu d'édition
	document.getElementById("text_editor").style.display = "block";

	// Utilisation des outils d'éditions du texte
	var inputs = document.getElementById("text_editor_inputs");
	if (useEditTools) {
		inputs.style.display = "block";
	} else {
		inputs.style.display = "none";
	}

	// Utilisation de l'outil d'édition du titre de la figure
	var legend = document.getElementById("text_editor_legend");
	if (legendEdit) {
		legend.style.display = "block";
		legend.textContent = "Titre de la figure";
	} else {
		legend.style.display = "none";
	}

	// Liaison du contenu éditable au contenu de l'objet
	var editor = document.getElementById("text_editor_content");
	if (editor_listener != null) {
		editor.removeEventListener("input", editor_listener);
		legend.removeEventListener('input', editor_listener);
	}
	editor_listener = function() {
		obj.editListener();
		refresh();
	}
	legend.addEventListener('input', editor_listener);
	editor.addEventListener('input', editor_listener);

	// Chargement du contenu initial
	editor.innerHTML = obj.getEditInitialContent();
}

/**
 * Cache l'éditeur de texte
 */
function closeTextEditor() {
	// Masquage de cette partie de l'interface
	var editor = document.getElementById("text_editor");
	editor.style.display = "none";

	// Nettoyage de l'éditeur de texte
	var elt = document.getElementById("text_editor_content");
	while (elt.childNodes.length > 0) {
		elt.removeChild(elt.firstChild);
	}

	// Suppression du dernier listener attaché
	if (editor_listener != null) {
		elt.removeEventListener("input", editor_listener);
	}
}


/**
 * Ouvre l'éditeur d'upload, afin de définir la
 * valeur d'un bloc en fonction d'un fichier externe
 */
var upload_listener = null;
var upload_title_listener = null;
function openUploadEditor(obj, legendEdit = false) {
	// Fermeture éventuelle du menu texte
	closeTextEditor();

	// Affichage du menu d'édition
	var editor = document.getElementById("upload_editor");
	editor.style.display = "block";

	// Utilisation de l'outil d'édition de la légende
	var legend = document.getElementById("upload_editor_legend");
	if (legendEdit) {
		legend.style.display = "block";
		legend.textContent = "Titre de l'image";

		// Ajout de l"écouteur sur l'éditeur de titre
		if (upload_title_listener != null) {
			legend.removeEventListener("input", upload_title_listener);
		}
		upload_title_listener = function() {
			obj.editListener();
			refresh();
		}
		legend.addEventListener('input', upload_title_listener);
	} else {
		legend.style.display = "none";
	}

	// Liaison du chargement au contenu de l'objet
	var input = document.getElementById("upload_editor_content");
	if (upload_listener != null) {
		editor.removeEventListener("input", upload_listener);
	}
	upload_listener = function() {
		obj.uploadListener();
		refresh();
	}
	editor.addEventListener('input', upload_listener);

	// Ajout du dernier lien chargé
	var link = document.getElementById("upload_editor_content");
	link.textContent = obj.content.src;
}


/**
 * Ferme l'éditeur d'upload.
 */
function closeUploadEditor() {
	// Masquage de cette partie de l'interface
	var editor = document.getElementById("upload_editor");
	editor.style.display = "none";

	// Suppression du dernier listener attaché
	if (editor_listener != null) {
		var elt = document.getElementById("text_editor_content");
		elt.removeEventListener("input", editor_listener);
	}
}


/*
 * Ferme la fenêtre de prévisualisation
 */
function closePreview() {
	document.getElementById("preview_show").style.display = "initial";
	document.getElementById("preview_hide").style.display = "none";
	document.getElementById("preview").style.display = "none";
}

/*
 * Ouvre la fenêtre de prévisualisation
 */
function openPreview() {
	document.getElementById("preview_show").style.display = "none";
	document.getElementById("preview_hide").style.display = "initial";
	document.getElementById("preview").style.display = "block";
}

/**
 * Met-à-jour le contenu de le menu d'édition spécifié
 */
function refresh_menu(menu, barId, container) {
	var menu = document.getElementById(menu);
	
	// On masque le menu, si le contenu est inexistant
	if (container == null) {
		menu.style.display = "none";
		return;
	}

	// Modification du contenu de la barre
	var bar = document.getElementById(barId);
	var children = bar.childNodes;
	var option = bar.getElementsByClassName("option_bloc")[0];

	// Suppression de l'ancien contenu
	var k = 0;
	while (k < children.length) {
		if (children[k].className == UIBlocClass) {
			bar.removeChild(children[k]);
		} else {
			++k;
		}
	}

	// Ajout du nouveau contenu
	for (var k = 0; k < container.content.length; ++k) {
		var obj = container.content[k];
		bar.insertBefore(createUIBloc(obj), option);
	}

	// Affichage
	menu.style.display = "block";
}

function refresh_title_bar(id, section) {
	var title = document.getElementById(id);

	// Mise à jour de l'affichage du titre de section
	if (section == null) {
		title.style.display = "none";
		return;
	}

	title.textContent = section.title.textContent;
	title.style.display = "block";
}


/**
 * Actualise l'interface d'édition
 */
function refresh() {
	// Mise-à-jour de la barre article
	refresh_menu("article_menu", "article_content", currentArticle);

	// Mise-à-jour de la barre section
	refresh_menu("section_menu", "section_content", currentSection);
	refresh_title_bar("section_title", currentSection);

    // Mise-à-jour de la barre sous-section
	refresh_menu("subsection_menu", "subsection_content", currentSubSection);
	refresh_title_bar("subsection_title", currentSubSection);

	// Mise-à-jour de l'interface d'édition
	if (currentItem == null) {
		closeTextEditor();
		closeUploadEditor();
	}

	// Arret des options utilisées
	enableDelete = false;
}

/**
 * Prépare l'envoi du formulaire d'article, afin d'enregistrer
 * les travaux dans la base de données
 */
function prepareSubmit() {
	// Préparation de l'abstract
	var txt = document.getElementById("abstract_window").textContent;
	var input = document.getElementById("abstract_input");
	input.value = txt;

	// Préparation des mots-clés
	txt = document.getElementById("keywords_window").textContent;
	input = document.getElementById("keywords_input");
	input.value = txt;

	// Préparation du titre
	var title = document.getElementById("article_title").textContent;
	input = document.getElementById("title_input");
	input.value = title;

	// Préparation du contenu
	var html = currentArticle.element.innerHTML;
	input = document.getElementById("content_input");
	input.value = html;
}


// --- Fonctions de construction de l'article
/**
 * Définit le type de bloc à créer pour une section donnée,
 * en fonction de l'option d'ajout choisie
 */
function addBloc(idselect, container) {
	// Récupération du type d'objet à créer
	var select = document.getElementById(idselect);
	var option = select.getElementsByTagName("option")[select.selectedIndex];
	switch(option.value) {
		case "1" :
			container.add(buildSection(false));
			break;
		case "2" :
			container.add(buildSection(true));
			break;
		case "3" :
			container.add(buildParagraph());
			break;
		case "4" :
			container.add(buildSVG());
			break;
		case "5" :
			container.add(buildImage());
			break;
		case "6" :
			container.add(buildLaTeX());
			break;
		case "7" :
			if (currentSummary == null) {
				buildSummary();
				container.add(currentSummary);
				currentSummary.buildContent();
			} else {
				alert("Un sommaire est déjà implanté dans votre article");
			}
			break;
	}
	refresh();
}

/**
 * Modifie le titre des sections.
 */
function updateSectionTitle(src, section) {
	section.setTitle(src.textContent);
	refresh();
}


// --- Mécanisme d'édition du texte ---
/*
 * La modification dynamique de texte, est réalisé au moyen
 * d'appels à la fonction Document.execCommand.
 *
 * La fonction suivante permettra de centraliser 
 * et de simplifier les appels dans les autres fonctions. 
 */
function callEditorOption(option, arg) {
	if (arg == null || arg === undefined) {
		arg = "";
	}

	//updateButtonsState();
	document.execCommand(option, false, arg);
}