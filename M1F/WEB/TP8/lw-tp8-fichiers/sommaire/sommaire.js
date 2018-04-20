function createSummaryNode(cName, title) {
	var elt = document.createElement("div");
	elt.className = cName;

	var link = document.createElement("a");
	link.href = "#" + title.id;
	link.appendChild(title.firstChild.cloneNode());

	elt.appendChild(link);
	return elt;
}

function buildSummary() {
	// Création d'un bloc qui contiendra le contenu du sommaire
	var content = document.createElement("div");

	// Création des menus correspondant aux sections
	var nodes = document.getElementsByTagName("body")[0].childNodes;
	for (var k = 0; k < nodes.length; ++k) {
		var tName = nodes[k].tagName;
		if (tName == "H2" || tName == "H3" || tName == "H4") {
			var node = createSummaryNode("som" + tName, nodes[k]);
			content.appendChild(node);
		}
	}

	// Ajout dans le sommaire
	document.getElementById("sommaire").append(content);
}

buildSummary();
/*
function buildSummaryOld() {
	// Création d'un bloc qui contiendra le contenu du sommaire
	var content = document.createElement("div");

	// Création des menus correspondant aux sections
	var titres_h2 = document.getElementsByTagName("h2");
	for (var i = 0; i < titres_h2.length; ++i) {
		var section = document.createElement("div");
		section.className = "somH2";

		var link = document.createElement("a");
		link.href = "#" + titres_h2[i].id;
		link.append(document.createTextNode(titres_h2[i].textContent));

		section.append(link);
	}

	// Ajout de la section
	content.append(section);

	// Ajout dans le sommaire
	document.getElementById("sommaire").append(content);
}*/