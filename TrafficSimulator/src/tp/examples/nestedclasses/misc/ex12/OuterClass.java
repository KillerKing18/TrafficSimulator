package tp.examples.nestedclasses.misc.ex12;

public class OuterClass {
	private int f = 1;

	
	public class InnerClass1 {
		private int f = 2;

		public class InnerClass2 {
			private int f = 3;

			public void test() {
				System.out.println(this.f + " " + InnerClass1.this.f); 
			}
		}

		public void test() {
			System.out.println(this.f + " " + OuterClass.this.f); 
		}
	}
	
	static public void main(String[] args) {
		OuterClass x = new OuterClass(); 
		OuterClass.InnerClass1 a = x.new InnerClass1();
		OuterClass.InnerClass1.InnerClass2 b = a.new InnerClass2();
		a.test();
		b.test();
	}
}