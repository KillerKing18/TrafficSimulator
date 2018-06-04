package tp.examples.threads.ex3;



public class F extends Function {

		private long n;
		
		F(long n) {
			this.n = n;
		}

		protected void apply() throws InterruptedException { 
			Function f = new Fact(n);
			Function g = new Fib(n);
			f.start();
			g.start();
			g.join();
			f.join();
			this.result = f.getRes() + g.getRes();
		}
		

}
