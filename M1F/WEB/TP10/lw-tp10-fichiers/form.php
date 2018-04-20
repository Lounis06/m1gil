<?php
  session_start();

  if (isset($_GET['nom'])) {
    $_SESSION['GET_old_nom'] = $_GET['nom'];
  }
  if (isset($_POST['nom'])) {
    $_SESSION['POST_old_nom'] = $_POST['nom'];
  }
?>

<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Formulaires et PHP</title>
</head>

<body>
  <!-- Exo 1.3 
  <h1>Contenu des tableaux PHP</h1>

  <?php
    function showTabContent($tab) {
      foreach ($tab as $key => $value) {
        echo "$key => $value <br />";
      }
    }

    foreach([$_GET, $_POST, $_REQUEST, $_SERVER, $_FILES] as $tab) {
      showTabContent($tab);
    }
  ?>
  -->

  <!-- Exos 2.4 et 2.5 -->
  <h1>Formulaires et PHP</h1>

  <?php foreach(['GET', 'POST'] as $method) {
  ?>
    <fieldset>
      <legend>Formulaire avec utilisant la méthode <?php echo $method;?></legend>

      <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="<?php echo $method;?>" encoding="multipart/form-data">
        Nom : <input type="text" name="nom">
        
        <!-- Ajout du nom utilisé précedemment -->
        <?php
          $var = "$method".'_old_nom';
          if (isset($_SESSION[$var])) {
            echo "(Ancien : $_SESSION[$var])";
          }
        ?>

        Mot de passe : <input type="password" name="passwd"><br>
        Civilité : <select>
          <option value="1">Mr</option>
          <option value="2">Mme</option>
        </select>
        <input type="checkbox" id="cond"><label for="cond">J'accepte les conditions</label><br>
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

  <input type="button" value="Détruire la session ! ><" action="<?php session_destroy(); ?>">
</body>
</html>