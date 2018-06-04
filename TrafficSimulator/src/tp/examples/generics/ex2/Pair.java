package tp.examples.generics.ex2;

public class Pair<A, B> {
	A first;
	B second;

	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	static public void main(String[] args) {
		Pair<Number,String> x = new Pair<Number,String>(1,"This is one!");
		Pair<Number,String> y = new Pair<Number,String>(1,"This is one!");
		boolean z = Utils.<Number,String>compare(x,y);
		boolean w = Utils.compare(x,y);
		System.out.println("("+x.getFirst()+","+x.getSecond()+")");
	}
}
