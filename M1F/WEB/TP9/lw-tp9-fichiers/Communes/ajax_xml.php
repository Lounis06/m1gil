<?php

    if(empty($_GET['commune'])) {
        echo "<station>Pas de commune</station>";
    }
    else {
        $c = trim($_GET['commune']);
        switch($c) {
            case "GQ":
                $res = "<station><code>GB</code><nom>Georges Braque</nom></station>".
                        "<station><code>JK</code><nom>J. F. Kennedy</nom></station>".
                        "<station><code>LP</code><nom>Léon Blum</nom></station>".
                        "<station><code>PC</code><nom>Paul Cézanne</nom></station>".
                        "<station><code>PR</code><nom>Provinces</nom></station>";
                break;
            case "PQ":
                $res = "<station><code>CG</code><nom>Charles de Gaulle</nom></station>".
                        "<station><code>FT</code><nom>François Truffaut</nom></station>".
                        "<station><code>JJ</code><nom>Jean Jaurès</nom></station>".
                        "<station><code>PM</code><nom>Place du 8 Mai</nom></station>".
                        "<station><code>SJ</code><nom>Saint-Julien</nom></station>";
                break;
            case "RO":
                $res = "<station><code>AC</code><nom>Avenue de Caen</nom></station>".
                        "<station><code>BE</code><nom>Beauvoisine</nom></station>".
                        "<station><code>BO</code><nom>Boulingrin</nom></station>".
                        "<station><code>EU</code><nom>Europe</nom></station>".
                        "<station><code>GR</code><nom>Gare-Rue Verte</nom></station>".
                        "<station><code>HB</code><nom>Honoré de Balzac</nom></station>".
                        "<station><code>JM</code><nom>Joffre-Mutualité</nom></station>".
                        "<station><code>PJ</code><nom>Palais de Justice</nom></station>".
                        "<station><code>SS</code><nom>Saint-Sever</nom></station>".
                        "<station><code>TA</code><nom>Théâtre des Arts</nom></station>";
            break;
            case "SE":
                $res = "<station><code>CC</code><nom>Champ de Courses</nom></station>".
                        "<station><code>ER</code><nom>Ernest Renan</nom></station>".
                        "<station><code>LP</code><nom>Le Parc</nom></station>".
                        "<station><code>MB</code><nom>Maryse Bastié</nom></station>".
                        "<station><code>TE</code><nom>Technopôle</nom></station>";
                break;
            case "SO":
                $res = "<station><code>JU</code><nom>14-Juillet</nom></station>".
                        "<station><code>GA</code><nom>Garibaldi</nom></station>".
                        "<station><code>HV</code><nom>Hôtel de Ville-Sotteville</nom></station>".
                        "<station><code>JZ</code><nom>Jean Zay</nom></station>".
                        "<station><code>TF</code><nom>Toit Familial</nom></station>".
                        "<station><code>VO</code><nom>Voltaire</nom></station>";
                break;
            default:
                $res = "<station><code></code><nom>Pas de station</nom></station>";
                break;
        }
        $res = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>".
                "<stations>".$res."</stations>";
        header("Content-Type: text/xml; charset=utf-8");
        echo $res;
    }
?>
