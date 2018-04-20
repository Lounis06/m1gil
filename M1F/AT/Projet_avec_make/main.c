#include <stdio.h>
#include <stdlib.h>
#include "trie.h"

// Définition des paramètres d'utilisation
#define MAX_WORD_COUNT 100  // Le nombre de mots par fichier au maximum.
#define MAX_WORD_SIZE 64    // La taille maximale d'un mot.

int main(int argc, char **argv) {
	// Vérification des arguments
	if (argc != 3) {
		perror("Nombre d'arguments invalide\n");
		exit(EXIT_FAILURE);
	}
	
	// Ouverture des fichiers requis
	// -- Dictionnaire
	FILE *dict = fopen(argv[1], "r");
	if (dict == NULL) {
		perror("Chargement du fichier dictionnaire impossible\n");
		exit(EXIT_FAILURE);
	}
	
	// -- Texte
	FILE *text = fopen(argv[2], "r");
	if (text == NULL) {
		perror("Chargement du texte impossible\n");
		exit(EXIT_FAILURE);
	}
	
	// Création et remplissage du trie dictionnaire
	Trie trie = createTrie(MAX_WORD_COUNT * MAX_WORD_SIZE);
	char buffer[MAX_WORD_SIZE + 1];
	
	// Lecture des mots
	int k = 0;
	char c = fgetc(dict);
	while (c != EOF) {
		// Ajout du mot (correspondant à une ligne de fichier) dans le trie
		if (c == '\n') {
			buffer[k] = '\0';
			insertInTrie(trie, (unsigned char *) buffer);
			k = 0;
		} else {
			buffer[k] = c;
			++k;
		}
		
		c = fgetc(dict);
	}
	
	// Recherche des occurences dans le texte
	// -- Initialisation
	int q = 0;
	int count = 0;
	c = fgetc(text);
	
	// -- Préparation du trie
	prepareAC(trie);
	
	// -- Traitement
	while (c != EOF && c != '\n') {
		count += executeAC(trie, &q, (unsigned char) c);
		c = fgetc(text);
	}
	
	// -- Affichage du nombre d'occurences
	printf("%d\n", count);
	
	// Fin de programme
	fclose(dict);
	fclose(text);
	return EXIT_SUCCESS;
}
