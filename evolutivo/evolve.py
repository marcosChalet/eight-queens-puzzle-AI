from individuo import Individuo
from random import randint

class Evolution:
    
    def __init__(self,tam, it, tx):
        self.tam = 0
        self.tammax = tam
        self.it = it
        self.itatual = 1
        self.tx = tx * 100
        self.fitnesstotal = 0
        self.novapopulacao = []
        self.populacao = []
        self.id = 1
        self.melhorIndividuo = Individuo()
        
    def fitness_test(self, individual):
        fitness = 0
        
        for i in range(8):
            for j in range(i+1, 8):
                if individual.tabuleiro[i] == individual.tabuleiro[j]:continue
                else:
                    if individual.tabuleiro[i] == individual.tabuleiro[j]-j+i or individual.tabuleiro[i] == individual.tabuleiro[j]+j-i:continue
                fitness += 1
                
        individual.fitness = fitness
        self.fitnesstotal += fitness
        
        if individual.fitness > self.melhorIndividuo.fitness:
            self.melhorIndividuo = individual
    
    def mostrar_geracao(self, individual):
        print("||-------------------------------------")
        print("|| Id: {}\n|| Geracao: {}ª\n|| Tabuleiro: {}\n|| Fitness: {}".format(individual.id,individual.geracao,individual.tabuleiro, individual.fitness))
        print("||-------------------------------------")
    
    def first_Individuo(self):
        
        individual = Individuo()
        
        for cont in range(8):
            individual.tabuleiro.append(randint(0, 7))
        
        individual.geracao = self.itatual
        individual.id = self.id
        individual.geracao = self.itatual
        
        self.id += 1
        self.fitness_test(individual)
        self.populacao.append(individual)
        
    def get_id(self, filho1, filho2):
        filho1.id = self.id
        self.id += 1
        
        filho2.id = self.id
        self.id += 1
        
        filho1.geracao = self.itatual
        filho2.geracao = self.itatual
        
        self.fitness_test(filho1)
        self.fitness_test(filho2)
        
        self.novapopulacao.append(filho1)
        self.novapopulacao.append(filho2)
        
        self.tam += 2
        
    def crossover(self):
        cut = randint(1,6)

        filho1 = Individuo()
        filho2 = Individuo()
        
        pai1 = self.selecionarpai()
        pai2 = self.selecionarpai()
        
        for i in range(0, cut):
            filho1.tabuleiro.append(pai1.tabuleiro[i])
            filho2.tabuleiro.append(pai2.tabuleiro[i])
            
        for i in range(cut, 8):
            filho1.tabuleiro.append(pai2.tabuleiro[i])
            filho2.tabuleiro.append(pai1.tabuleiro[i])
        
        self.mutacao(filho1, filho2)
        
        self.get_id(filho1, filho2)
        
    def mutacao(self, filho1, filho2):
        if randint(0, 100) <= self.tx:
            filho1.tabuleiro[randint(0,7)] = randint(0,7)
            
        if randint(0, 100) <= self.tx:
            filho2.tabuleiro[randint(0,7)] = randint(0,7)

    def selecionarpai(self):
        escolhido = randint(0, self.fitnesstotal)
        fitness = 0
        j = 0
        
        for i in self.populacao:
            fitness += i.fitness
            if fitness > escolhido:
                break
            j += 1
            
        pai = self.populacao[j-1]
        
        return pai
    
    def adicionarpopulacao(self):
        for i in self.novapopulacao:
            for j in range(0, self.tammax):
                if i.fitness > self.populacao[j].fitness:
                    self.populacao[j] = i
                    break
                
        self.tam = 0
        self.novapopulacao = []
        self.fitnesstotal = 0
    
    def run(self):
        for i in range(self.tammax):
            self.first_Individuo()
            
        self.itatual += 1
        
        while self.itatual <= self.it and self.melhorIndividuo.fitness != 28:
            while self.tam < self.tammax:
                self.crossover()
                
            self.adicionarpopulacao()
            self.itatual += 1
            self.fitnesstotal = 0
            #print("::\n::\n::             Geração {}ª             \n::\n::".format(self.itatual))
            #self.mostrar_geracao(self.melhorIndividuo)
        print("::\n::\n::           Melhor Individuo            \n::\n::")
        self.mostrar_geracao(self.melhorIndividuo)