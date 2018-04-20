<!--
	register_controller.php : Donne corps au formulaire d'inscription
	de la page register.php
-->
<form id="register_form" action="../model/register_model.php" method="POST">
	<fieldset>
		<legend>Vos identifiants de connexion</legend>
		<div class="info_table">
			<div class="info" id="login">
				<label>Nom d'utilisateur : </label>
				<div class="input_container">
					<input type="text" name="login" id="login_input"
					oninput="update('login');">
				</div>
				<div class="error">Contenu vide</div>
			</div>

			<div class="info" id="password">
				<label>Mot de passe : </label>
				<div class="input_container">
					<input type="password" name="password" id="password_input"
					oninput="update('password');">
				</div>
				<div class="error">Contenu vide</div>
			</div>

			<div class="info" id="password_confirm">
				<label>Confirmez le mot de passe : </label>
				<div class="input_container">
					<input type="password" name="password_confirm" id="password_confirm_input"
					oninput="update('password_confirm', isPasswordConfirmed);">
				</div>
				<div class="error">Les 2 mots de passes entrés sont différents</div>
			</div>
		</div>
	</fieldset>
	<fieldset>
		<legend>Vos informations personnelles</legend>
		<div class="info_table">
			<div class="info" id="firstName">
				<label>Prénom : </label>
				<div class="input_container">
					<input type="text" name="firstName" id="firstName_input"
					oninput="update('firstName', isNameUsable);">
				</div>
				<div class="error">Caractères invalides : N'utilisez que des lettres</div>
			</div>
			<div class="info" id="lastName">
				<label>Nom : </label>
				<div class="input_container">
					<input type="text" name="lastName" id="lastName_input"
					oninput="update('lastName', isNameUsable);">
				</div>
				<div class="error">Caractères invalides : N'utilisez que des lettres</div>
			</div>
			<div class="info" id="birthDate">
				<label>Date de naissance : </label>
				<div class="input_container">
					<input type="date" name="birthDate" id="birthDate_input"
					oninput="update('birthDate', isDateUsable);">
				</div>
				<div class="error">Format de la date incorrecte (souhaité : JJ/MM/AAAA)</div>
			</div>
		</div>
	</fieldset>
	<fieldset>
		<legend>Vous contacter</legend>
		<div class="info_table">
			<div class="info" id="telephone">
				<label>Téléphone : </label>
				<div class="input_container">
					<input type="text" name="telephone" placeholder="XX.XX.XX.XX.XX"
					oninput="update('telephone', isTelephoneUsable);" id="telephone_input">
				</div>
				<div class="error"> 10 chiffres demandés, séparés ou non par un point</div>
			</div>
			<div class="info" id="mail">
				<label>Adresse mail : </label>
				<div class="input_container">
					<input type="email" name="mail" placeholder="abc@xyz.com"
					oninput="update('mail', isMailUsable);" id="mail_input">
				</div>
				<div class="error"> Format de l'adresse mail incorrect </div>
			</div>
		</div>
	</fieldset>
	<div id="actions">
		<input type="reset" value="Réinitialiser le formulaire">
		<input type="submit" value="Finaliser l'inscription">
	</div>
</form>