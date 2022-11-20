class Individuo:
    
    def __init__(self):
        self.id = None
        self.tabuleiro = []
        self.fitness = None
        self.geracao = None

    def mostrar_tabuleiro(self):
        black = '⬛'
        white = '⬜'
        for i in reversed(range(8)):
            if i%2 == 1:
                tab = [white, black]
            else:
                tab = [black, white]
            
            print("||", end="      ")
            
            for j in (range(8)):
                if self.tabuleiro[j] == i:
                    print('👑', end="")
                else:
                    print(tab[j%2], end="")
            print('')
                    
if __name__ == "__main__":    
    ind = Individuo()
    ind.tabuleiro = [1,1,1,1,1,1,1,1]
    ind.mostrar_tabuleiro()
    