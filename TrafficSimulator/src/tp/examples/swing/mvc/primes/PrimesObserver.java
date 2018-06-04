package tp.examples.swing.mvc.primes;

import java.math.BigInteger;

public interface PrimesObserver {
	public void onStart();
	public void onFinish(boolean halt);
	public void onGen(BigInteger n);	
}
