---- Construction des exécutables ----
Vous pouvez consturire l'intégralité des executables, en
exécutant la commande make depuis ce répertoire.

Si vous ne souhaitez utiliser qu'un seul programme à la fois, vous
pouvez ordonner la construction d'un seul executable, en spécifiant
son nom après la commande make.

Ex : make genere-mots, ne construira que le générateur de mots.

Pour supprimer les fichiers objets, et exécutables générés, vous pourrez
utiliser la commande make clean.


---- Utilisation des exécutables ----
> genere-mots / Produit une liste de mots sur la sortie standard.

Commande :  ./genere-mots <arg1> <arg2> <arg3> <arg4>
Arguments :
    - arg1 : Le nombre de mots à générer
    - arg2 : La taille minimale de tous les mots générés
    - arg3 : La taille maximale de tous les mots générés
    - arg4 : La taille de l'alphabet utilisé pour générer ces mots.


> genere-texte / Produit un texte sur la sortie standard.

Commande :  ./genere-texte <arg1> <arg2>
Arguments :
    - arg1 : Le nombre de caractères contenus dans le texte
    - arg2 : La taille de l'alphabet utilisé pour générer ces mots.


> ac-[matrice, liste, mixte] / Lance l'algorithme d'Aho-Corasick,
et renvoie le nombre de mots trouvés sur la sortie standard.

Commande :  ./ac-[matrice, liste, mixte] <arg1> <arg2>
Arguments :
    - arg1 : Le fichier contenant les mots à rechercher
    - arg2 : Le fichier définissant le texte dans lequel rechercher ces mots.



