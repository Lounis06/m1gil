import random

BASE_WEIGHT = 10
RECOMPENSE = 8

# Définit le comportement d'un réseau de neurones.
# Permet d'identifier et de mettre en avant des stratégies gagnantes sous forme
# d'enchaînements de tours.
class NeuronNetwork:
    # CONSTRUCTEUR
    # - maxDist : La distance maximale réalisable en un tour (Ici, le nombre de bâtons max. joués en un tour)
    # - nbSticks : La taille du réseau en nombre de neurones (Ici, le nombre de bâtons du jeu)
    def __init__(self,maxDist,nbSticks):
        self.neurons = []
        for i in range(1,nbSticks+1):
            self.neurons.append(Neuron(self,i))
        for neuron in self.neurons:
            neuron.makeConnections(maxDist,nbSticks,BASE_WEIGHT)
        self.initPath()

    # Initialise la table des chemins neuronaux
    def initPath(self):
        self.path = {}

    # Renvoie le neurone associé à l'indice donné
    # index : L'indice du neurone associé au réseau
    def getNeuron(self,index):
        if index-1>=0 and index<=len(self.neurons): return self.neurons[index-1]
        else: return None

    # Renvoie le chemin neuronal associé aux deux neurones donnés
    # - neuron1 : Le premier neurone du chemin
    # - neuron2 : Le second neurone du chemin
    def activateNeuronPath(self,neuron1,neuron2):
        self.path[neuron1]=neuron2

    # Récompense l'ensemble des connections neuronales, qui constituent
    # le chemin victorieux de la dernière partie.
    def recompenseConnections(self):
        for neuron1,neuron2 in self.path.items():
            neuron1.recompenseConnection(neuron2)
        self.initPath()
    def printAllConnections(self):
        for neuron in self.neurons: neuron.printConnections()
    def printScores(self):
        scores = {}
        for neuron in self.neurons:
            for n,s in neuron.connections.items():
                if n not in scores: scores[n]=s
                else: scores[n] = scores[n] + s
        for neuron,score in scores.items():
            print(neuron.asString(),score)

# Définit le comportement d'un neurone.
# Permet d'identifier le meilleur coup pour un nombre de bâtons donné.
class Neuron:
    # CONSTRUCTEUR
    # - network : Le réseau auquel appartiendra ce neurone
    # - index : L'indice de ce neurone
    def __init__(self,network,index):
        self.network = network
        self.index = index
        self.connections = {}

    # COMMANDES
    # Réalise les connections nécessaires à ce neurone.
    #  - maxDist : Nombre de batons jouables par tour
    #  - nbSticks : Nombre de batons à jouer
    #  - baseWeight : Le poids initial de toute connection neurale
    def makeConnections(self,maxDist,nbSticks,baseWeight):
        if self.index!=nbSticks: nb=maxDist*2 +1
        else: nb=maxDist +1
        for i in range(1,nb):
            neuron = self.network.getNeuron(self.index-i)
            if neuron!=None: self.connections[neuron]=baseWeight

    # Sélectionne un neurone à jouer, en fonction du neurone associé à la position actuelle
    # - shift : Le décalage entre le dernier neurone manipulé et le nombre de bâtons restants
    def chooseConnectedNeuron(self,shift):
        # TODO méthode qui retourne un neurone connecté au neurone actuel en fonction du 'shift' (cf. CPUPlayer).
        # On devra utiliser la méthode self.weighted_choice pour choisir au hasard dans une liste de connexions disponibles en fonction de leurs poids
        availableConnections = self.connections.copy()
        neuron = self.weighted_choice(availableConnections)

        while not neuron.testNeuron(self.index - shift) :
            availableConnections.pop(neuron)
            neuron = self.weighted_choice(availableConnections)

        return neuron

    # Teste si le neuron est atteignable depuis un certain nombre de batons
    # - inValue : Le nombre de bâtons restants
    def testNeuron(self,inValue):
        # TODO renvoie un booléen : True si la différence entre la 'inValue' et la valeur du neurone actuel est comprise entre 1 et 3 inclus
        return 1 <= inValue - self.index <= 3

    # Récompense la liaison entre ce neurone et celui spécifié
    # - neuron : Le neurone présent sur la connection neuronale avec celui-ci
    def recompenseConnection(self,neuron):
        # TODO récompenser la connexion entre le neurone actuel et 'neuron'
        self.connections[neuron] += RECOMPENSE

    # Affiche les connections neurales de ce neurone
    def printConnections(self):
        print("Connections of",self.asString()+":")
        for neuron in self.connections:
            print(neuron.asString(), self.connections[neuron])

    # Affiche ce neurone sous forme textuelle
    def asString(self):
        return "N"+str(self.index)

    # Sélectionne un neurone à jouer en fonction du poids des connections neurales
    # présentes sur le réseau de neurones fourni.
    # - connections : L'ensemble des connections neuronales disponibles.
    def weighted_choice(self,connections):
       total = sum(w for c, w in connections.items())
       r = random.uniform(0, total)
       upto = 0
       for c, w in connections.items():
          if upto + w >= r: return c
          upto += w
        


        


