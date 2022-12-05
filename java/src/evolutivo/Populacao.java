package evolutivo;

import java.util.ArrayList;
import java.util.List;

public class Populacao {
	private List<Individuo> individuos;
	
	public Populacao() {
		individuos = new ArrayList<Individuo>();
	}
	
	private double sumFitness() {
		double sum = 0;
		for (Individuo i : individuos) {
			sum += i.getFitness();
		}
		
		return sum;
	}
	
	public void atribuiProbabilidades() {
		double total = sumFitness();
		for (Individuo i : individuos) {
			i.setProbabilidade(i.getFitness() * 100 / total);
		}
	}
	
	public void mostrar() {
		for (Individuo i : individuos) {
			System.out.println("p: " + i.getProbabilidade());
		}
	}
	
	public void setConjuntoIndividuos(List<Individuo> conjunto) {
		individuos = conjunto;
	}
	
	public void setIndividuo(Individuo i) {
		individuos.add(i);
	}
	
	public List<Individuo> getIndividuos() {
		return individuos;
	}
}
