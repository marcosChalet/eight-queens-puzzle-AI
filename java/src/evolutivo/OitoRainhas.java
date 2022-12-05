package evolutivo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OitoRainhas {
	//private List<Individuo> populacao;
	private final int tamPopulacao;
	private final int maxIteracoes;
	private final int melhorFitness;
	private final double txMut;
	
	private int geracao;
	private Populacao populacao;
	private Individuo melhorIndividuo = null;
	private Random geraValor = new Random();
	
	List<Individuo> pais;
	List<Individuo> filhos;
	
	public OitoRainhas(int tamPop, int maxIteracoes, double txMut) {
		populacao = new Populacao();
		tamPopulacao = tamPop;
		this.maxIteracoes = maxIteracoes;
		this.txMut = txMut;
		melhorFitness = 28;
	}
	
	private void geraPopulacao() {
		Individuo novoIndividuo;
		for (int i = 0; i < tamPopulacao; i++) {
			novoIndividuo = new Individuo();
			populacao.setIndividuo(novoIndividuo);
		}

		populacao.atribuiProbabilidades();
	}
	
	private boolean melhorFitnessNaoEncontrado() {
		for ( Individuo i : populacao.getIndividuos() ) {
			if (i.getFitness() >= melhorFitness) {
				melhorIndividuo = i;
				return false;
			}
		}
		return true;
	}
	
	private List<Individuo> selecionarPais() {
		
		List<Individuo> pais = new ArrayList<Individuo>();
		
		int sumFitness = 0, item = 0;
		for ( Individuo i : populacao.getIndividuos() ) {
			sumFitness += i.getFitness();
		}
		
		int posicaoPai1 = geraValor.nextInt(sumFitness);
		int posicaoPai2 = geraValor.nextInt(sumFitness);
		
		boolean p1 = true, p2 = true;
		for ( Individuo i : populacao.getIndividuos() ) {
			
			item += i.getFitness();
			
			if ( posicaoPai1 <= item && p1==true ) {
				pais.add(i);
				p1 = false;
			}
			
			if ( posicaoPai2 <= item && p2==true ) {
				pais.add(i);
				p2 = false;
			}
			
			if (p1 == false && p2 == false) {
				break;
			}		
		}
		
		return pais;
	}
	
	private List<Individuo> crossover(List<Individuo> pais) {
		
		int pontoCrossover = geraValor.nextInt(8);
		List<Individuo> filhos = new ArrayList<Individuo>();
		Individuo filho1 = new Individuo();
		Individuo filho2 = new Individuo();
		
		int[] tabuleiro1 = new int[8];
		int[] tabuleiro2 = new int[8];
		
		for (int i = 0; i < 8; i++) {
			if (i < pontoCrossover) {
				tabuleiro1[i] = pais.get(0).getTabuleiro()[i];
			} else {
				tabuleiro1[i] = pais.get(1).getTabuleiro()[i];
			}
		}
		
		for (int i = 0; i < 8; i++) {
			if (i < pontoCrossover) {
				tabuleiro2[i] = pais.get(1).getTabuleiro()[i];
			} else {
				tabuleiro2[i] = pais.get(0).getTabuleiro()[i];
			}
		}
		
		filho1.setTabuleiro(tabuleiro1);
		filho2.setTabuleiro(tabuleiro2);
		
		filho1.setFitness();
		filho2.setFitness();
		
		filhos.add(filho1);
		filhos.add(filho2);
		
		return filhos;
	}
	
	private void mutar(List<Individuo> filhos, double txMut) {
		int probMut = geraValor.nextInt(100);
		int[] tabuleiro;
		int pos, val;

		if (probMut < txMut * 100) {
			for (Individuo f : filhos) {
				tabuleiro = f.getTabuleiro();
				pos = geraValor.nextInt(8);
				val = geraValor.nextInt(1, 9);
				tabuleiro[pos] = val;
				f.setTabuleiro(tabuleiro);
				f.setFitness();
			}
		}
		
	}
	
	private List<Individuo> melhoresIndividuos(Populacao populacao, int tamPopulacao) {
		
		List<Individuo> sortIndividuos = populacao.getIndividuos();
		List<Individuo> melhoresIndividuos = populacao.getIndividuos();
		
		Collections.sort(sortIndividuos, new Compare());
		
		for (int i = 0; i < tamPopulacao; i++) {
			melhoresIndividuos.get(i).setTabuleiro(sortIndividuos.get(i).getTabuleiro());
		}
		
		return melhoresIndividuos;
	}
	
	public void geneticAlgorithm() {
		geraPopulacao();
		geracao = 1;
		Populacao novaPopulacao;
		
		 
		while( geracao < maxIteracoes && melhorFitnessNaoEncontrado() ) {
			
			novaPopulacao = new Populacao();
			pais = new ArrayList<Individuo>();
			filhos = new ArrayList<Individuo>();
			
			for (int i = 0; i < tamPopulacao; i++) {
				pais = selecionarPais();				
				filhos = crossover(pais);
				mutar(filhos, txMut);
				for (Individuo f : filhos)
					novaPopulacao.setIndividuo(f);
			}
			
			for (Individuo f : novaPopulacao.getIndividuos())
				populacao.setIndividuo(f);

			populacao.setConjuntoIndividuos( melhoresIndividuos(populacao, tamPopulacao) );
			
			if (geracao % 10 == 0) {
			System.out.println("**********************************");
			populacao.getIndividuos().get(0).mostraTabuleiro();
			System.out.println(populacao.getIndividuos().get(0).getFitness());
			populacao.getIndividuos().get(1).mostraTabuleiro();
			System.out.println(populacao.getIndividuos().get(1).getFitness());
			populacao.getIndividuos().get(2).mostraTabuleiro();
			System.out.println(populacao.getIndividuos().get(2).getFitness());
			System.out.println("!Geração: " + (geracao+1) + ", M: " + maxIteracoes);
			}
			geracao++;
		}
		
		if (melhorIndividuo != null) {
			System.out.println("========== Achei! ==========");
			melhorIndividuo.mostraTabuleiro();
			System.out.println("Fitness: " + melhorIndividuo.getFitness());
			System.out.println("Geração: " + geracao);
		} else {
			System.out.println("Tabuleiro não encontrado");
		}
	}

}
