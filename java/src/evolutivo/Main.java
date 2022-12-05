package evolutivo;

public class Main {

	public static void main(String[] args) {
		OitoRainhas app = new OitoRainhas(150, 100, 0.2);
		app.geneticAlgorithm();
		System.out.println("Fim da execução!");
	}
}
