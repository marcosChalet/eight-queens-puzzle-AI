from individuo import Individuo
from random import randint
from copy import deepcopy

class Subir():
    
    def __init__(self):
        self.novizinho = Individuo()
        self.noatual = Individuo()
        self.melhorfitness = 100
        self.sus = []
    
    def First_Individuo(self):
        
        
        self.noatual.fitness = 100
        
        for cont in range(8):
            self.noatual.tabuleiro.append(randint(0, 7))
            
        self.noatual.fitness = 100
        self.Fitness_test(self.noatual)
        self.novizinho.fitness = 100
        
        self.Escolher_sussesor()
    
    def Fitness_test(self, individual):
        fitness = 0
        
        for i in range(8):
            for j in range(i+1, 8):
                if individual.tabuleiro[i] == individual.tabuleiro[j]:fitness += 1
                else:
                    if individual.tabuleiro[i] == individual.tabuleiro[j]-j+i or individual.tabuleiro[i] == individual.tabuleiro[j]+j-i:fitness += 1
                
        individual.fitness = fitness
        
        if individual.fitness < self.melhorfitness:
            self.melhorfitness = fitness
            self.sus.append(deepcopy(individual))
    
    def Escolher_sussesor(self):
        possiveis = []
        self.sus = []
        
        for i in range(8):

            self.novizinho = deepcopy(self.noatual)
            
            for j in range(8):
                if j != self.noatual.tabuleiro[i]:
                    self.novizinho.tabuleiro[i] = j
                    self.Fitness_test(deepcopy(self.novizinho))
                    
        for i in self.sus:
            if i.fitness <= self.melhorfitness:
                possiveis.append(i)
                
  
        if (len(possiveis)-1) > 0:
            self.novizinho = possiveis[randint(0, len(possiveis)-1)]
        elif (len(possiveis)-1) == 0:
            self.novizinho = possiveis[0]
        else:
            self.novizinho = deepcopy(self.noatual)
        
    def Mostrar_geracao(self, individual):
        print("||-------------------------------------")
        print("|| Tabuleiro: {}\n|| Fitness: {}".format(individual.tabuleiro, individual.fitness))
        print("||-------------------------------------")
            
    def Run(self):
        self.First_Individuo()
        self.Mostrar_geracao(self.noatual)
        
        while self.novizinho.fitness < self.noatual.fitness:
            self.noatual = deepcopy(self.novizinho)
            self.Mostrar_geracao(self.noatual)
            self.Escolher_sussesor()
            
            
        self.Mostrar_geracao(self.noatual)
        self.noatual.mostrar_tabuleiro()

subir = Subir()
subir.Run()
