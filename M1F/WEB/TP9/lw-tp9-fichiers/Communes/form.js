// Déclaration de l'objet XHR
var XHR = new createXhrObject();

// Fonction d'envoi de la requête
function sendRequest(orsc, url) {
  var value = document.getElementById("cities").value;
  XHR.onreadystatechange = orsc;
  XHR.open('GET', url + '?commune=' + value, true);
  XHR.send();
}


// Réalisation de la requête : Format HTML
function receiveAnswerHTML() {
  if (XHR.readyState == 4) {
    if (XHR.status == 200) {
      document.getElementById("stops").innerHTML = XHR.responseText;
    } else {
      alert("Erreur : " + XHR.statusText);
    }
  }
}

function sendRequestHTML() {
  sendRequest(receiveAnswerHTML, "ajax.php");
}


// Réalisation de la requête : Format XML
function receiveAnswerXML() {
  if (XHR.readyState == 4) {
    if (XHR.status == 200) {
      var target = document.getElementById("stops");
      
      // Réécriture du contenu de target :
      target.innerHTML = "";
      var stations = XHR.responseXML.getElementsByTagName("station");
      
      // Ajout de chaque station dans la liste de sélection
      for (var k = 0; k < stations.length; ++k) {
        // Création d'une nouvelle option
        var elt = document.createElement("option");
        
        // Définition
        elt.value = stations[k].getElementsByTagName("code")[0].innerHTML;
        elt.innerHTML = stations[k].getElementsByTagName("nom")[0].innerHTML;
        
        // Ajout dans le bloc de sélection
        target.appendChild(elt);
      }
    } else {
      alert("Erreur : " + XHR.statusText);
    }
  }
}

function sendRequestXML() {
  sendRequest(receiveAnswerXML, "ajax_xml.php");
}



// Réalisation de la requête : Format JSON
function receiveAnswerJSON() {
  if (XHR.readyState == 4) {
    if (XHR.status == 200) {
      var target = document.getElementById("stops");
      
      // Réécriture du contenu de target :
      target.innerHTML = "";
      var stations = JSON.parse(XHR.responseText);
      
      // Ajout de chaque station dans la liste de sélection
      for (var k = 0; k < stations.length; ++k) {
        // Création d'une nouvelle option
        var elt = document.createElement("option");
        
        // Définition
        elt.value = stations[k].code;
        elt.innerHTML = stations[k].nom;
        
        // Ajout dans le bloc de sélection
        target.appendChild(elt);
      }
    } else {
      alert("Erreur : " + XHR.statusText);
    }
  }
}

function sendRequestJSON() {
  sendRequest(receiveAnswerJSON, "ajax_json.php");
}
