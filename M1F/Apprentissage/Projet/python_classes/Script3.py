from Player import *
from Game import *

# Initialisation
game = Game(15)
nbGames = 10000

# Lancement du jeu
for cpu1 in {"easy", "medium", "hard"}:
    for cpu2 in {"easy", "medium", "hard"}:
        # Définition des joueurs
        player1 = CPUPlayer("CPU1", cpu1, game.nbSticks)
        player2 = CPUPlayer("CPU2", cpu2, game.nbSticks)

        # Jeu
        for i in range(nbGames) :
            game.start(player1, player2, False)

        # Affichage des résultats
        print("Joueur 1 (", cpu1, ") : ", player1.nbWin)
        print("Joueur 2 (", cpu2, ") : ", player2.nbWin)
        print("--------")