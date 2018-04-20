/**
 * admin.js : Gère l'interactivité de la page admin.php
 */
var checkBoxIds = ["userCheckBox", "categoryCheckBox"];
var menuIds = ["userEdit", "categoryEdit"];

function updateMenuDisplay() {
	for (var k = 0; k < menuIds.length; ++k) {
		var checkBox = document.getElementById(checkBoxIds[k]);
		var menu = document.getElementById(menuIds[k]);

		if (!checkBox.checked) {
			menu.style.display="none";
		} else {
			menu.style.display="block";
		}
	}
}