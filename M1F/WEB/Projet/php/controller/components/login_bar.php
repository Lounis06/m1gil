<!--
	login_bar.php : Définit le composant permettant à l'utilisateur
	de se logger, depuis une page quelconque de l'applicatif.
-->
<?php
	if (isset($_SESSION['user'])) {
?>
	<div id="logoff_bar">
		<p>
			Bonjour <?php echo $_SESSION['user']['firstName']; ?> !
		</p>
		<form id="login_form" action="../model/login_bar_model.php" method="POST">
			<input type="hidden" name="logoff" value="true">
			<input type="submit" name="submit" value="Se déconnecter">
		</form>
	</div>
<?php
	} else {
?>
	<div id="login_bar">
		<form id="login_form" action="../model/login_bar_model.php" method="POST">
			<fieldset>
				<legend>Se connecter</legend>
				<input type="hidden" name="login" value="true">
				<input type="text" name="username" placeholder="Nom d'utilisateur">
				<input type="password" name="password" placeholder="Mot de passe">
				<input type="submit" name="submit" value="Se connecter">
			</fieldset>
		</form>
		<p>
			Vous n'êtes pas entregistré ici ?
			<a id="login_register" href="register.php">S'enregistrer</a>
		</p>
	</div>
<?php
	}
?>