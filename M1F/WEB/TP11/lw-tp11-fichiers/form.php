<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Formulaires et PHP</title>
</head>

<body>
  <h1>Formulaires et PHP</h1>

  <?php foreach(['GET', 'POST'] as $method) {
  ?>
    <fieldset>
      <legend>Formulaire avec utilisant la méthode <?php echo $method;?></legend>

      <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="<?php echo $method;?>" encoding="multipart/form-data">
        Nom : <input type="text" name="nom">
        Mot de passe : <input type="password" name="passwd"><br>
        Civilité : <select name="title">
          <option value="1">Mr</option>
          <option value="2">Mme</option>
        </select>
        <input type="checkbox" id="cond" name="cond"><label for="cond">J'accepte les conditions</label><br>
        Abonnement : <input type="radio" name="abo" value="oui" id="yes"><label for="yes">Oui</label>
        <input type="radio" name="abo" value="non" checked="checked" id="no"><label for="no">Non</label><br>

        Message : <textarea name="message"></textarea><br>
        Pièce jointe : <input type="file" name="pj"><br>
        <input type="submit" name="Envoyer">
        <input type="reset" name="Réinitialiser">
      </form>
    </fieldset>
    <br>
  <?php
  }
  ?>
</body>
</html>
