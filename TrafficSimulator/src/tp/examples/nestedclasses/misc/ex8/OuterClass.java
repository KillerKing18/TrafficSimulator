package tp.examples.nestedclasses.misc.ex8;

public class OuterClass {
	private int f;

	OuterClass(int n) { this.f = n; }
	public class InnerClass {
		public int getF() { return f; }
		public void setF(int n) { f = n; }
	}
	InnerClass foo() { return new InnerClass(); }
	public static void main(String[] args) {
		OuterClass x = new OuterClass(1); 
		OuterClass y = new OuterClass(1); 
		InnerClass a = x.foo();
		InnerClass b = y.foo();
		InnerClass c = x.foo();
		

		a.setF(3);
		System.out.println( a.getF() + " " + b.getF() + " " + c.getF());
		b.setF(5);
		System.out.println( a.getF() + " " + b.getF() + " " + c.getF());
		a.setF(10);
		System.out.println( a.getF() + " " + b.getF() + " " + c.getF());
	}
}