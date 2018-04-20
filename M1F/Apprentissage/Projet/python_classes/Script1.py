from Player import *
from Game import *

# Initialisation
game = Game(15)
difficulty = "hard"
player1 = HumanPlayer("J1")
player2 =  CPUPlayer("CPU", difficulty, game.nbSticks)

# Lancement du jeu
game.start(player1, player2, True)