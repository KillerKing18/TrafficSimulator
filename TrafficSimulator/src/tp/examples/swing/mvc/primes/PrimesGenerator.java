package tp.examples.swing.mvc.primes;

import java.math.BigInteger;
import java.util.ArrayList;

import tp.other.year_13_14.examples14.threads.primes.ver1.Primes;

public class PrimesGenerator implements Observable<PrimesObserver> {

	private ArrayList<PrimesObserver> observers;

	private BigInteger n;
	private volatile boolean halt;

	public PrimesGenerator() {
		observers = new ArrayList<PrimesObserver>();
		n = new BigInteger("1");
	}

	public void genPrimes() {

		for (PrimesObserver o : observers)
			o.onStart();

		halt = false;

		while (!halt) {
			n = Primes.nextPrime(n);
			for (PrimesObserver o : observers)
				o.onGen(n);
			sleepabit();
		}

		for (PrimesObserver o : observers)
			o.onFinish(halt);

		halt = false;

	}

	public void stop() {
		if (!halt)
			halt = true;
	}

	private void sleepabit() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public void addObserver(PrimesObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(PrimesObserver o) {
		observers.remove(o);
	}

}
