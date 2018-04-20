import random
from Neuron import *

# Modélise le comportement d'un joueur
class Player:
    # CONSTRUCTEUR
    # - name : Le nom du nouveau joueur
    def __init__(self,name):
        self.name = name
        self.nbWin = 0

    # REQUETES
    # Renvoie le nom du joueur
    def getName(self):
        return self.name

    # Renvoie le nombre de victoires obtenues par ce joueur
    def getNbWin(self):
        return self.nbWin

    # COMMANDES
    # Ajoute un nouvelle victoire au palmarès de ce joueur.
    def addWin(self):
        self.nbWin+=1

    # Ajoute une nouvelle défaite à l'historique de ce joueur.
    def addLoss(self):
        pass

# Modélise le comportement d'un joueur humain
class HumanPlayer(Player):
    # COMMANDE
    # Permet au joueur humain de jouer à son tour.
    # - sticks : Le nombre de bâtons lors du tour à jouer
    def play(self,sticks):
        if sticks==1: return 1
        else:
            correct = False
            while not correct:
                nb = input('Sticks?\n')
                try:
                    nb=int(nb)
                    if nb>=1 and nb<=3 and sticks-nb>=0:
                        correct=True
                except: pass
            return nb

# Simule le comportement d'un joueur en suivant un algorithme particulier : L'ordinateur.
class CPUPlayer(Player):
    # CONSTRUCTEUR
    # Définit un nouveau joueur CPU avec le nom name, et commençant à nbSticks.
    # - name : Le nom du joueur
    # - mode : La difficulté choisie pour le joueur CPU ("easy", "medium", "hard")
    # - nbSticks : Le nombre de bâtons manipulés lors du jeu
    def __init__(self,name,mode,nbSticks):
        super().__init__(name)
        self.mode = mode
        self.netw = NeuronNetwork(3,nbSticks)
        self.previousNeuron = None

    # REQUETE
    # Renvoie le réseau de neurones associé à cet ordinateur
    def getNeuronNetwork(self): return self.netw

    # COMMANDE
    # Permet à l'ordinateur de jouer à son tour.
    # - sticks : Le nombre de bâtons lors du tour à jouer
    def play(self,sticks):
        if self.mode=='easy': return self.playEasy(sticks)
        elif self.mode=='hard': return self.playHard(sticks)
        else: return self.playMedium(sticks)

    # Sélectionne une décision de jeu, digne d'un ordinateur de difficulté normale
    # Sélection d'un nombre de batons de façon aléatoire, mais sans actions à risque.
    # - sticks : Le nombre de bâtons lors du tour à jouer
    def playMedium(self,sticks):
        # TODO modifier ici avec les quelques conditions pour éviter de faire une grosse erreur aux derniers tours
        if 2 <= sticks and sticks <= 4 : return sticks - 1

        return self.playRandom(sticks)

    # Sélectionne une décision de jeu, digne d'un ordinateur de difficulté facile :
    # Sélection d'un nombre de batons de façon aléatoire et naïve
    # - sticks : Le nombre de bâtons lors du tour à jouer
    def playEasy(self,sticks):
        return self.playRandom(sticks)

    # Sélectionne une décision de jeu de façon aléatoire.
    # - sticks : Le nombre de bâtons lors du tour à jouer
    def playRandom(self,sticks):
        return random.randint(1, (sticks%3)+1)

    # Sélectionne une décision de jeu, digne d'un ordinateur de difficulté difficile :
    # On fait appel à ses capacités d'apprentissage pour prendre la bonne décision
    # - sticks : Le nombre de bâtons lors du tour à jouer
    def playHard(self,sticks):
        # TODO utiliser le réseau neuronal pour choisir le nombre de bâtons à jouer
        # utiliser l'attribut self.previousNeuron pour avoir le neuron précédemment sollicité dans la partie
        # calculer un 'shift' qui correspond à la différence entre la valeur du précédent neurone et le nombre de bâtons encore en jeu
        # utiliser la méthode 'chooseConnectedNeuron' du self.previousNeuron puis retourner le nombre de bâtons à jouer
        # bien activer le réseau de neurones avec la méthode 'activateNeuronPath' après avoir choisi un neurone cible
        # attention à gérer les cas particuliers (premier tour ou sticks==1)
        # Gestion du dernier tour :
        if sticks == 1 : return 1

        if self.previousNeuron == None: self.previousNeuron = self.netw.getNeuron(sticks)
        shift = self.previousNeuron.index - sticks
        selectedNeuron = self.previousNeuron.chooseConnectedNeuron(shift)
        self.getNeuronNetwork().activateNeuronPath(self.previousNeuron, selectedNeuron)
        self.previousNeuron = selectedNeuron
        return sticks - selectedNeuron.index

    # Ajoute une victoire au palmarès de ce joueur
    def addWin(self):
        super().addWin()
        self.netw.recompenseConnections()
        self.previousNeuron=None

    # Ajoute une défaite à l'historique de ce joueur
    def addLoss(self):
        super().addLoss()
        self.previousNeuron=None




        


