# Inutile de modifier cette classe normalement

# Modélise une partie du jeu de bâtons
class Game:
    # CONSTRUCTEUR
    def __init__(self, nbSticks):
        self.nbSticks = nbSticks

    # COMMANDES
    # Lance une partie entre 2 joueurs l'argument supplémentaire verbose
    # sert à ordonner l'affichage ou non, d'instructions à l'écran.
    # - player1 : Le joueur démarrant la partie
    # - player2 : Le joueur adverse
    # - verbose : Affiche des détails sur la partie en cours à l'écran
    def start(self,player1,player2,verbose):
        if verbose: print("New game")
        sticks = self.nbSticks
        currp = player1
        while sticks>0:
            if verbose: print("Remaining sticks:",sticks)
            n = currp.play(sticks)
            if n<1 or n>3: print("Error")
            if verbose: print(currp.getName(),"takes",n)
            sticks-=n
            if currp==player1: currp = player2
            else: currp = player1
        if verbose: print(currp.getName(),"wins!")
        if player1==currp:
            player1.addWin()
            player2.addLoss()
        else:
            player1.addLoss()
            player2.addWin()
