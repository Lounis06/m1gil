from Player import *
from Game import *
import pickle

# Initialisation
game = Game(15)
player1 = CPUPlayer("CPU1", "hard", game.nbSticks)
player2 = CPUPlayer("CPU2", "hard", game.nbSticks)
learnTimes = 10000

# Lancement du jeu
for i in range(learnTimes) :
    game.start(player1, player2, False)

# Affichage des résultats
print("Joueur 1 : ", player1.nbWin)
player1.getNeuronNetwork().printAllConnections()
print("--------")
print("Joueur 2 : ", player2.nbWin)
player2.getNeuronNetwork().printAllConnections()
print("--------")

# Sauvegarde du modèle -- Utilisé pour le Script 4
# with open("save.mdl","wb") as output: pickle.dump(player1.getNeuronNetwork(), output, pickle.HIGHEST_PROTOCOL)
# player1.getNeuronNetwork().printAllConnections()

