package tp.examples.nestedclasses.misc.ex9;

public class OuterClass {
	private int f;

	public OuterClass(int n) { this.f = n; }
	public class InnerClass {
		public int getF() { return f; }
		public void setF(int n) { f = n; }
	}
	InnerClass foo() { return new InnerClass(); }
	
	
}
