from Player import *
from Game import *
import pickle

# Demande d'informations
name = input("What is your name ?\n")

difficulty = input("Which AI would you like to play with ?\n")
if not {"easy", "medium", "hard"}.__contains__(difficulty) :
    print("For giving bad informations, You'll be defeated by the hardest AI !")
    difficulty = "hard"


# Initialisation du jeu avec les parmètres saisis
game = Game(15)
player1 = HumanPlayer(name)
player2 = CPUPlayer("CPU", difficulty, game.nbSticks)

# -- Chargement du réseau de neurones (CPU hard)
# Note : Le modèle actuel a été constitué à l'aide d'un million de parties jouées.
# et a été sauvegardé dans le fichier save.mdl
if difficulty == "hard" :
    with open("save.mdl","rb") as inp :
        player2.netw = pickle.load(inp)


# Lancement du jeu
end = False
while not end :
    order = input("Starting the game as first player ? (y/n)\n") == "y"

    if order : game.start(player1, player2, True)
    else : game.start(player2, player1, True)

    end = input("Would you like to play again ? (y/n)\n") != "y"