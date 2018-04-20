/**
 * contentStruct.js : Permet de structurer le contenu de l'article
 * en définissant les différents types utilisables : article,
 * sections, éléments...
 */

// ---- VARIABLES GLOBALES ----
/**
 * Mécanisme de désignation des titres de section
 * (pour la création du sommaire)
 */
sectionId = 0;
sectionTitle= "section_title";
/**
 * Variable de sélection de l'objet courant
 */
currentArticle = null;
currentSection = null;
currentSubSection = null;
currentItem = null;
currentSummary = null;

/* 
 * Modélisation d'un bloc de la structure d'article : 
 * Un bloc peut contenir ou non des blocs simples
 * et/ou d'autres conteneurs.
 */
function Bloc(addContainer, addItem) {
	// ATTRIBUTS
	this.isContainer = addContainer || addItem;
	this.isItem = !this.isContainer;
	this.content = this.isContainer ? [] : null;
	this.element = null;
	this.parent = null;

	// REQUETES
	this.canAddContainer = function() {
		return addContainer;
	}
	this.canAddItem = function() {
		return addItem;
	}

	// COMMANDES
	// Ajoute un élément dans le conteneur
	this.add = function(obj) {
		if (!this.canAddContainer() && obj.isContainer) {
			return;
		}
		if (!this.canAddItem() && obj.isItem) {
			return;
		}

		// Ajout
		this.content.push(obj);
		obj.parent = this;
		this.element.appendChild(obj.element);

		// Mise-à-jour éventuelle du sommaire
		if (obj.isSection && currentSummary != null) {
			currentSummary.buildContent();
		}
	}

	// Supprime un élément du conteneur
	this.delete = function(obj) {
		if (!this.content.includes(obj)) {
			return;
		}

		// Gestion de suppression du sommaire
		if (obj.isSummary) {
			currentSummary = null;
		}

		// Suppression de l'objet
		obj.parent = null;
		this.element.removeChild(obj.element);
		this.content = this.content.filter(function(elt) {
			return elt != obj;
		})

		// Mise-à-jour éventuelle du sommaire
		if (obj.isSection && currentSummary != null) {
			currentSummary.buildContent();
		}
	}
}


/*
 * Crée un nouveau bloc de section
 */ 
function buildSection(isSubSection) {
	// Une section peut contenir des sous-sections, mais
	// une sous section ne contient que des blocs.
	var section = new Bloc(!isSubSection, true);
	section.label = "Section";
	section.isSection = true;

	// Initialisation de l'élément HTML présent dans le bloc
	section.element = document.createElement("div");
	section.element.className = "section_container";

	// Ajout du titre de la section
	if (isSubSection) {
		section.title = document.createElement("h3");
		section.title.textContent = "Nouvelle sous-section";
	} else {
		section.title = document.createElement("h2");
		section.title.textContent = "Nouvelle section";
	}

	// Propriétés du bloc titre
	section.title.id = sectionTitle + sectionId;
	section.title.className = sectionTitle;
	section.element.appendChild(section.title);

	// Mise à jour de l'id de Section
	sectionId++;

	// Ajout de nouvelles fonctionnalités à la section
	/*
	 * Gestion de l'action associée au clic, sur l'icone
	 * représentant cette section
	 */
	section.clickHandler = function() {
		if (isSubSection) {
			currentSubSection = section;
		} else {
			currentSection = section;
			currentSubSection = null;
		}
		currentItem = null;
		refresh();
	}

	/**
	 * Modification du titre de la section
	 */
	section.setTitle = function(txt) {
		section.title.textContent = txt;

		if (currentSummary != null) {
			currentSummary.buildContent();
		}
	}

	return section;
}


/*
 * Crée un nouveau bloc article
 */
function buildArticle() {
	// Un article contient des sessions, mais pas d'autres blocs
	var article = new Bloc(true, false);
	article.label = "Article";
	article.isArticle = true;

	// Initialisation de l'élément HTML présent dans le bloc
	article.element = document.createElement("div");
	article.element.className = "article_container";

	// Ajout de nouvelles fonctionnalités à l'article
	/**
	 * Ajout d'une section à l'article
	 */
	article.addSection = function() {
		article.add(buildSection(false));
	}

	return article;
}


/**
 * Crée un nouvel élément modifiable : Factorise le comportement
 * commun à tous les types d'objets utilisables (p, svg, ...)
 */
function buildItem() {
	// Un Item ne contient que du contenu
	var item = new Bloc(false, false);

	// Initialisation de l'élément HTML présent dans le bloc
	item.element = document.createElement("div");
	item.element.className = "article_item";

	/*
	 * Gestion de l'action associée au clic
	 */
	item.clickHandler = function() {
		currentItem = item;
		openTextEditor(item, true, false);
		refresh();
	}

	/*
	 * Définit l'écouteur associé à cet objet, pour l'édition
	 * de son contenu par l'éditeur de texte.
	 */
	item.editListener = function() {
		var editor = document.getElementById("text_editor_content");
		item.element.innerHTML = editor.innerHTML;
	}

	/*
	 * Renvoie le contenu actuel pour le rééditer
	 */
	item.getEditInitialContent = function() {
		return item.element.innerHTML;
	}

	return item;
}


/**
 * Crée un nouveau paragraphe
 */
function buildParagraph() {
	// Un paragraphe ne contient que du texte
	var paragraph = buildItem();
	paragraph.label = "Paragraphe";

	// Initialisation de l'élément HTML présent dans le bloc
	paragraph.tagName = "p";

	return paragraph;
}

/**
 * Crée un nouvelle figure.
 */
function buildFigure() {
	// Une figure ne contient que du contenu
	var figure = buildItem();
	figure.label = "Figure";

	// Initialisation de l'élément HTML présent dans le bloc
	figure.tagName = "figure";

	// Initialisation des blocs contenus dans la figure
	figure.content = document.createElement("div");
	figure.element.appendChild(figure.content);
	figure.legend = document.createElement("h4");
	figure.element.appendChild(figure.legend);

	/*
	 * Gestion de l'action associée au clic, sur l'icone
	 * représentant cette figure
	 */
	figure.clickHandler = function() {
		currentItem = figure;
		openTextEditor(figure, false, true);
		refresh();
	}

	/*
	 * Définit l'écouteur associé à cette figure, pour l'édition
	 * de son contenu par l'éditeur de texte.
	 */
	figure.editListener = function() {
		var editor = document.getElementById("text_editor_content");
		figure.content.innerHTML = editor.textContent;

		var legend = document.getElementById("text_editor_legend");
		figure.legend.textContent = legend.textContent;
	}

	/*
	 * Renvoie le contenu actuel pour le rééditer
	 */
	figure.getEditInitialContent = function() {
		return figure.content.innerHTML;
	}

	return figure;
}

/**
 * Crée un nouvelle figure au format SVG.
 */
function buildSVG() {
	// Un figure SVG ne contient que du code HTML
	var svg = buildFigure();
	svg.label = "SVG";

	return svg;
}

/**
 * Crée un nouvelle formule à partir de code LaTeX.
 */
function buildLaTeX() {
	// Un paragraphe ne contient que du texte
	var latex = buildFigure();
	latex.label = "LaTeX";

	// Initialisation de l'élément HTML présent dans le bloc
	latex.element.tagName = "mathml";

	/*
	 * Définit l'écouteur associé à cette figure, pour l'édition
	 * de son contenu par l'éditeur de texte.
	 */
	latex.editListener = function() {
		var editor = document.getElementById("text_editor_content");
		/**
		 * Cette partie est un bouchon destiné à être remplacé par
		 * un appel au module de conversion.
		 */
		latex.content.innerHTML = "<mrow><mn>2</mn><mo>+</mo><mn>2</mn><mo>=</mo><mn>4</mn></mrow>";

		var legend = document.getElementById("text_editor_legend");
		latex.legend.textContent = legend.textContent;
	}

	return latex;
}

/**
 * Crée un nouvelle figure représentée par une image
 */
function buildImage() {
	// Un image ne comporte que du contenu
	var image = buildFigure();
	image.label = "Image";
	
	/* Curieusement, image.content.createElement("img") ne fonctionne
	 * pas ici, j'utiliserai alors exceptionnellement innerHTML
	 * pour cet usage...
	 */
	image.element.innerHTML = '<img src="" alt="" /><h4></h4>';
	image.content = image.element.firstChild;
	image.legend = image.element.lastChild;

	/*
	 * Gestion de l'action associée au clic, sur l'icone
	 * représentant cette figure
	 */
	image.clickHandler = function() {
		// On supprime l'éditeur
		currentItem = image;
		openUploadEditor(image, true);

		// On rafraichît l'interface
		refresh();
	}

	/*
	 * Définit l'écouteur associé à cette figure, pour l'édition
	 * du contenu de son titre.
	 */
	image.editListener = function() {
		// Récupération du titre de l'image
		var legend = document.getElementById("upload_editor_legend");
		image.legend.textContent = legend.textContent;
	}

	/*
	 * Définit l'écouteur associé à cette figure, pour l'upload
	 * de l'image associée
	 */
	image.uploadListener = function() {
		// Initialisation des données requises pour la lecture
		var input = document.getElementById('upload_editor_content');
		var file = input.files[0];

		// Instanciation de l'objet servant au chargement
		var reader  = new FileReader();

		// Ajout de l'action à la fin du chargement
		reader.addEventListener("load", function() {
			// Chargement de la nouvelle image
		    image.content.src = reader.result;
		});

		// Lecture du fichier cible, s'il existe
		if (file) {
		    reader.readAsDataURL(file);
		}
	}

	return image;
}


/**
 * Crée le bloc summary associé à l'article
 */
function buildSummary() {
	// Un image ne comporte que du contenu
	var summary = buildItem();
	summary.label = "Sommaire";
	summary.isItem = false;
	summary.isSummary = true;
			
	// Remplacement des propriétés du bloc de contenu
	summary.element.id = "summary";
	summary.title = document.createElement("h1");
	summary.title.appendChild(document.createTextNode("Sommaire"));
	summary.content = document.createElement("div");

	// Remplissage du bloc element
	summary.element.appendChild(summary.title);
	summary.element.appendChild(summary.content);

	/*
	 * Gestion de l'action associée au clic, sur l'icone
	 * représentant cette figure, ce bloc étant
	 * automatique, on ne fait aucun traitement
	 */
	summary.clickHandler = function() {
		// On rafraichît l'interface
		refresh();
	}

	/*
	 * Définit la méthode de construction d'un noeud
	 * du sommaire
	 */
	summary.createSummaryNode = function(cName, title) {
		var elt = document.createElement("div");
		elt.className = cName;

		// Création du lien vers le titre
		var link = document.createElement("a");
		link.href = "#" + title.id;
		link.appendChild(title.firstChild.cloneNode());

		elt.appendChild(link);
		return elt;
	}

	/*
	 * Définit la méthode de construction du contenu du sommaire
	 */
	summary.buildContent = function() {
		// Suppression du contenu précédent
		while (summary.content.childNodes.length > 0) {
			summary.content.removeChild(summary.content.firstChild);
		}

		// Création des menus correspondant aux sections
		var text = currentArticle.element;
		var nodes = text.getElementsByClassName(sectionTitle);

		for (var k = 0; k < nodes.length; ++k) {
			var tName = nodes[k].tagName;
			if (tName == "H2" || tName == "H3") {
				var node = summary.createSummaryNode("som" + tName, nodes[k]);
				summary.content.appendChild(node);
			}
		}
	}

	currentSummary = summary;
}
