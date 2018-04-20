/** ---- Exercice 3 ---- **/
// Question 3.1
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

printObject(window);
printObject(document);


// Question 3.2
anoname = {
  nom : "AAA",
  age : 22,
  majeur : true,
  adresse : {
    numero : 36,
    rue : "Quai des Orfèvres"
  },
  travail : function() {
    return false;
  }
}

printObject(anoname);


// Question 3.3
function merge(a, b) {
  var obj = new Object();
  
  // Ajout des attributs de a
  for (var attr in a) {
    obj[attr] = a[attr];
  }
  
  // Ajout des attributs de b
  for (var attr in b) {
    obj[attr] = b[attr];
  }
  
  return obj;
}

randomperson = {
  prenom : "BBB",
  majeur : false,
  ddn : "00/00/0000"
}

printObject(merge(anoname, randomperson));


