// ---- Prérequis ----
function printObject(obj) {
  var result = "";
  // Ajout du texte définissant l'objet
  result += "Object [" + obj + "] : [" + (typeof obj) + "] =";
  result += "\n";
  
  // Ajout du texte décrivant chacun des attributs
  for (var a in obj) {
    result += "" + a + " : " + (typeof obj[a]) + " = " + obj[a];
    result += "\n";
  }
  
  return result;
}

/** Exercice 1 **/
/* 
1.1)
Il y a 6 éléments de type <P> et 7 éléments appartenant à la classe content.

Les deux blocs div, fils directs du bloc body, correspondent aux éléments
d'id components (pour le 1er) et intro (pour le 2nd).
*/

/*
1.2) Cette méthode appartient à l'objet HTML document.
*/
alert(typeof document.getElementById("intro"));

/* 1.3) */
var elems_p = document.getElementsByTagName("p");
alert(elems_p.length);

var elems_content = document.getElementsByClassName("content");
alert(elems_content.length);

/* 1.4) */
var txt = printObject(elems_content);
console.log(txt);

/*
L'objet retourné est une collection, contenant tous les éléments de classe
content, indexés de 0 à(taille de la collection) - 1.
*/

/* 1.5) */
console.log("Nb d'éléments : " + elems_content.length);
for (var k = 0; k < elems_content.length; ++k) {
  var o = elems_content[k];
  console.log("Elt n°" + k + " de type : " + (typeof o));
  console.log(" | Classe : " + o.className + "\n");
}

/* 1.6) */
while (elems_content.length > 0) {
  elems_content[0].className = "comment";
}

/* 
Ici, le nombre d'éléments de elems_content diminue au fur et à mesure
que les éléments changent de classe. A chaque changement de classe 
=> retrait de l'élément de l'ensemble.
*/

/* 1.7) */
var span = document.createElement("span");
span.className = "comment";
span.innerHTML = "Je suis un commentaire dans une span."

document.getElementsByTagName("body")[0].appendChild(span);

/* 1.8) En plus de 1.7... */
alert();
document.getElementById("intro").appendChild(span);

/*
L'ajout dans le bloc div, replace le bloc au lieu de le dupliquer.
Cela vient du fait que l'on manipule le bloc span directement, et qu'il est impossible
qu'un me élément soit contenu dans deux blocs différents (non parents).
*/

/* 1.9) */
var elems_p = document.getElementsByTagName("p");
var src = document.getElementById("pmenu");
for (var k = 0; k < elems_p.length; ++k) {
  elems_p[k].insertBefore(src.cloneNode(true), elems_p[k].firstChild);
}

/**
L'attribut de profondeur deep, de la fonction cloneNode(), permet d'indiquer s'il faut
également cloner les enfants du bloc source (i.e copier l'arborescence entière).
*/