package evolutivo;

import java.util.Comparator;

public class Compare implements Comparator<Individuo> {
	@Override
	public int compare(Individuo a, Individuo b){
		return b.getFitness() - a.getFitness();
	}
}
