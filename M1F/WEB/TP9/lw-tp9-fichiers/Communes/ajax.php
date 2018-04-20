<?php

    if(empty($_GET['commune'])) {
        echo "Pas de commune";
    }
    else {
        $c = trim($_GET['commune']);
        switch($c) {
            case "GQ":
                $res = "<option value='GB'>Georges Braque</option>".
                        "<option value='JK'>J. F. Kennedy</option>".
                        "<option value='LP'>Léon Blum</option>".
                        "<option value='PC'>Paul Cézanne</option>".
                        "<option value='PR'>Provinces</option>";
                break;
            case "PQ":
                $res = "<option value='CG'>Charles de Gaulle</option>".
                        "<option value='FT'>François Truffaut</option>".
                        "<option value='JJ'>Jean Jaurès</option>".
                        "<option value='PM'>Place du 8 Mai</option>".
                        "<option value='SJ'>Saint-Julien</option>";
                break;
            case "RO":
                $res = "<option value='AC'>Avenue de Caen</option>".
                        "<option value='BE'>Beauvoisine</option>".
                        "<option value='BO'>Boulingrin</option>".
                        "<option value='EU'>Europe</option>".
                        "<option value='GR'>Gare-Rue Verte</option>".
                        "<option value='HB'>Honoré de Balzac</option>".
                        "<option value='JM'>Joffre-Mutualité</option>".
                        "<option value='PJ'>Palais de Justice</option>".
                        "<option value='SS'>Saint-Sever</option>".
                        "<option value='TA'>Théâtre des Arts</option>";
            break;
            case "SE":
                $res = "<option value='CC'>Champ de Courses</option>".
                        "<option value='ER'>Ernest Renan</option>".
                        "<option value='LP'>Le Parc</option>".
                        "<option value='MB'>Maryse Bastié</option>".
                        "<option value='TE'>Technopôle</option>";
                break;
            case "SO":
                $res = "<option value='JU'>14-Juillet</option>".
                        "<option value='GA'>Garibaldi</option>".
                        "<option value='HV'>Hôtel de Ville-Sotteville</option>".
                        "<option value='JZ'>Jean Zay</option>".
                        "<option value='TF'>Toit Familial</option>".
                        "<option value='VO'>Voltaire</option>";
                break;
            default:
                $res = "Pas de station";
                break;
        }
        echo $res;
    }
?>
