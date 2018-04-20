/**
 * register.js : Définit une aide interactive pour le formulaire d'inscription
 * L'aide permet de colorer les informations valides en vert, et
 * d'afficher les erreur en rouge tout en montrant une indication.
 *
 * Ici, on procède à l'affichage/masquage des "tooltips" en les 
 * colorant de façon à être visibles (Evite à modifier la propriété display
 * des blocs, et facilite le rendu table).
 */

// Met à jour l'affichage de l'erreur pour un bloc d'information donné 
function update(id, test = null) {
	var bloc = document.getElementById(id);
	var input = bloc.getElementsByTagName("input")[0];
	var error = bloc.getElementsByClassName("error")[0];

	// Test de la valeur du bloc d'entrée
	var value = input.value;
	var checked = value.length > 0;

	if (test != null) {
		checked = test(value);
	}

	// Actualisation de l'affichage
	if (checked) {
		input.className = "right";

		// Masquage de l'aide
		error.style.color = "#f9f9f9";
	} else {
		input.className = "wrong";

		//Affichage de l'aide
		error.style.color = "#ff3311";
	}
}

/* 
 * Tests de validité disponibles : chaque fonction de test,
 * ne prend en unique paramètre que le bloc input (instance de DOM)
 * contenant la valeur à tester.
 */
// Test de validité d'un numéro de téléphone
function isTelephoneUsable(str) {
	var regexp = /^0[1-9]([-. ]?[0-9]{2}){4}$/;

	return regexp.test(str);
}

// Test de validité d'un numéro de téléphone
function isMailUsable(str) {
	/** 
	 * Expression rationnelle associée à la détection d'une adresse mail
	 * (Identique à celle utilisée par la balise input de type "email").
	 */
	var regexp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

	return regexp.test(str);
}

// Test de validité d'un nom
function isNameUsable(str) {
	var regexp = /^[a-zA-Z]+$/;

	return regexp.test(str);
}

// test de validité d'une date
function isDateUsable(str) {
	var regexp = /^(0[1-9]|[1-2][0-9]|3[0-1])\/(0[1-9]|1[0-2])\/[0-9]{4}$/;

	return regexp.test(str);
}

// Test de confirmation
function isPasswordConfirmed(str) {
	var input = document.getElementById("password_input");

	return str == input.value;
}
