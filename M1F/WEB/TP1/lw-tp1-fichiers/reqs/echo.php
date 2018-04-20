<?php
header('Content-Type: text/plain');
?>
Un echo du serveur

Par GET, j'ai reçu :
--------------------
<?php
foreach ($_GET as $k => $v) {
	echo "$k :  $v\n";
}
?>

Par POST, j'ai reçu : 
---------------------
<?php
foreach ($_POST as $k => $v) {
	echo "$k :  $v\n";
}
?>



Dans l'en-tête, j'ai reçu : 
---------------------------
<?php
foreach (getallheaders() as $k => $v) {
	echo "$k :  $v\n";
}
?>


Le corps de la requête contenait :
----------------------------------
<?php echo file_get_contents('php://input'); ?>
