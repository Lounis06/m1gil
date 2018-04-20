<!--
	top_layouts.php : Contient le code html et PHP, permettant afficher
	la partie supérieure du site web.

	Cela comprend :
		- le layout associé au haut de page
		- le layout d'affichage des erreurs
		- le layout d'affichage des notifications
-->

<!-- Couche NORD : haut de page -->
<div id="north_layout" class="layout">
	<header class="layout_content">
		<div id="header_title"> SciMS </div>
	</header>
</div>

<!-- Couche ALTERNATIVE : affichage des erreurs de l'applicatif -->
<?php
	if (isset($_SESSION['error'])) {
?>
	<div id="error_layout" class="layout">
		<div class="layout_content">
			<p><?php echo $_SESSION['error']; ?><p>
		</div>
	</div>
<?php
	}
	$_SESSION['error'] = null;
?>

<!-- Couche ALTERNATIVE : notifications de l'applicatif -->
<?php 
	if (isset($_SESSION['note'])) {
?>
	<div id="notification_layout" class="layout">
		<div class="layout_content">
			<p><?php echo $_SESSION['note']; ?><p>
		</div>
	</div>
<?php
	}
	$_SESSION['note'] = null;
?>