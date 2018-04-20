<?php

    if(empty($_GET['commune'])) {
        echo "<station>Pas de commune</station>";
    }
    else {
        $c = trim($_GET['commune']);
        switch($c) {
            case "GQ":
                $res = '[{ "code" : "GB", "nom" : "Georges Braque" },'.
                        '{ "code" : "JK", "nom" : "J. F. Kennedy" },'.
                        '{ "code" : "LP", "nom" : "Léon Blum" },'.
                        '{ "code" : "PC", "nom" : "Paul Cézanne" },'.
                        '{ "code" : "PR", "nom" : "Provinces" }]';
                break;
            case "PQ":
                $res = '[{ "code" : "CG", "nom" : "Charles de Gaulle" },'.
                        '{ "code" : "FT", "nom" : "François Truffaut" },'.
                        '{ "code" : "JJ", "nom" : "Jean Jaurès" },'.
                        '{ "code" : "PM", "nom" : "Place du 8 Mai" },'.
                        '{ "code" : "SJ", "nom" : "Saint-Julien" }]';
                break;
            case "RO":
                $res = '[{ "code" : "AC", "nom" : "Avenue de Caen" },'.
                        '{ "code" : "BE", "nom" : "Beauvoisine" },'.
                        '{ "code" : "BO", "nom" : "Boulingrin" },'.
                        '{ "code" : "EU", "nom" : "Europe" },'.
                        '{ "code" : "GR", "nom" : "Gare-Rue Verte" },'.
                        '{ "code" : "HB", "nom" : "Honoré de Balzac" },'.
                        '{ "code" : "JM", "nom" : "Joffre-Mutualité" },'.
                        '{ "code" : "PJ", "nom" : "Palais de Justice" },'.
                        '{ "code" : "SS", "nom" : "Saint-Sever" },'.
                        '{ "code" : "TA", "nom" : "Théâtre des Arts" }]';
            break;
            case "SE":
                $res = '[{ "code" : "CC", "nom" : "Champ de Courses" },'.
                        '{ "code" : "ER", "nom" : "Ernest Renan" },'.
                        '{ "code" : "LP", "nom" : "Le Parc" },'.
                        '{ "code" : "MB", "nom" : "Maryse Bastié" },'.
                        '{ "code" : "TE", "nom" : "Technopôle" }]';
                break;
            case "SO":
                $res = '[{ "code" : "JU", "nom" : "14-Juillet" },'.
                        '{ "code" : "GA", "nom" : "Garibaldi" },'.
                        '{ "code" : "HV", "nom" : "Hôtel de Ville-Sotteville" },'.
                        '{ "code" : "JZ", "nom" : "Jean Zay" },'.
                        '{ "code" : "TF", "nom" : "Toit Familial" },'.
                        '{ "code" : "VO", "nom" : "Voltaire" }]';
                break;
            default:
                $res = '[ { "code" : "", "nom" : "Pas de station" } ]';
                break;
        }
        echo $res;
    }
?>
