// ---- Prérequis ----
function printObject(obj) {
  var result = "";
  // Ajout du texte définissant l'objet
  result += "Object [" + obj + "] : [" + (typeof obj) + "] =";
  result += "<br />";
  
  // Ajout du texte décrivant chacun des attributs
  for (var a in obj) {
    result += "" + a + " : " + (typeof obj[a]) + " = " + obj[a];
    result += "<br />";
  }
  
  // Remplacement du texte du bloc div "sortie"
  document.getElementById("sortie").innerHTML = result;
}


// ---- Exercice 4 ----
// Question 4.1
function rect(lon, lar) {
  this.longueur = lon;
  this.largeur = lar;
}

printObject(rect);
printObject(rect.prototype);

r = new rect(5, 3);
printObject(r);

rect.prototype.color = "blue";

r2 = new rect(6, 2);
printObject(r2);
printObject(r);


// Question 4.2
function printObject(obj) {
  var result = "";
  // Ajout du texte définissant l'objet
  result += "Object [" + obj + "] : [" + (typeof obj) + "] =";
  result += "<br />";
  
  // Ajout du texte décrivant chacun des attributs
  for (var a in obj) {
    if (obj.constructor.prototype.hasOwnProperty(a)) {
      result += "PROPERTY : ";
    }
    result += "" + a + " : " + (typeof obj[a]) + " = " + obj[a];
    result += "<br />";
  }
  
  // Remplacement du texte du bloc div "sortie"
  document.getElementById("sortie").innerHTML = result;
}


// Question 4.3
String.prototype.repeat = function (x) {
  var tmp = "";
  for (var i = 0; i < x; ++i) {
    tmp += this;
  }
  
  return tmp;
}

console.log("cou".repeat(2));

// Question 4.4
function rand(a, b) {
  return a + Math.floor((Math.random() * (b - a)) + 1);
}

