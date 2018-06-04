package tp.examples.nestedclasses.misc.ex10;

public class OuterClass {
	private int f;
	
	public class InnerClass {
		private int f;

		InnerClass(int m) { 
			this.f = m; 
		}
		public void test() {
			System.out.println(this.f); 
		}
	}

	OuterClass(int m) { 
		this.f = m;
	}

	static public void main(String[] args) {
		OuterClass x = new OuterClass(1); 
		InnerClass a = x.new InnerClass(4);
		a.test();		
	}
}