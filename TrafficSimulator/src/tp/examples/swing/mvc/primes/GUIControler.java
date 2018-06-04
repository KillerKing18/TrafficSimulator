package tp.examples.swing.mvc.primes;

public class GUIControler {

	private PrimesGenerator model;
	
	public GUIControler(PrimesGenerator model) {
		this.model = model;
	}
	
	public void generatePrimes() {
		model.genPrimes();
	}

	public void stop() {
		model.stop();
	}

	public void quit() {
		
	}

}
