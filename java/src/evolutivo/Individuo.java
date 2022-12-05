package evolutivo;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Individuo {
	private final int dimTabuleiro = 8;
	private final int id;
	private int fitness=0;
	private int geracao;
	private double probabilidade;
	private int[] tabuleiro = {-1, -1, -1, -1,
							   -1, -1, -1,- 1};
	
	private static final AtomicInteger count = new AtomicInteger(0); 
	Random geraValor = new Random();
	
	public Individuo() {
		setEstado();
		setFitness();
		geracao = 1;
		id = count.incrementAndGet();
	}
	
	private void setEstado() {
		for (int i = 0; i < dimTabuleiro; i++) {
			tabuleiro[i] = geraValor.nextInt(1, dimTabuleiro+1);
		}
	}
	
	private boolean linhaDiferente(int i, int j) {
		if ( tabuleiro[i] == tabuleiro[j] )
			return false;
		return true;
	}
	
	public boolean diagonalSuperiorDiferente(int i, int j) {
		if ( (tabuleiro[j] - tabuleiro[i]) - (j - i) == 0 )
			return false;
		return true;
	}
	
	public boolean diagonalInferiorDiferente(int i, int j) {
		if ( (tabuleiro[i] - tabuleiro[j]) - (j - i) == 0 )
			return false;
		return true;
	}
	
	public void setFitness() {
		fitness = 0;
		for (int i = 0; i < dimTabuleiro; i++) {
			for (int j = i+1; j < dimTabuleiro; j++) {
				if ( linhaDiferente(i, j) && 
					 diagonalSuperiorDiferente(i, j) &&
					 diagonalInferiorDiferente(i, j)
				) {
					fitness++;
				}
			}
		}
	}
	
	public void mostraTabuleiro() {
		for (int i = 0; i < dimTabuleiro-1; i++) {
			System.out.print(tabuleiro[i] + ", ");
		}
		System.out.println(tabuleiro[dimTabuleiro-1]);
	}
	
	/* Métodos get/set */
	
	public void setTabuleiro(int[] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	public void setGeracao(int geracao) {
		this.geracao = geracao;
	}
	
	public void setProbabilidade(double probabilidade) {
		this.probabilidade = probabilidade;
	}
	
	public int getFitness() {
		return fitness;
	}
	
	public int[] getTabuleiro() {
		return tabuleiro;
	}
	
	public double getProbabilidade() {
		return probabilidade;
	}
	
	public int getId() {
		return id;
	}
	
	public int getGeracao() {
		return geracao;
	}
	
}
