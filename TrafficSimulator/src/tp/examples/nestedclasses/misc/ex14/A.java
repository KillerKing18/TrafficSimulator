package tp.examples.nestedclasses.misc.ex14;

public class A {
	public void f() {
		g();
	}

	public void g() {
		System.out.println("Hola!");
	}

	public static void main(String[] args) {
		A x = new A() {
			@Override
			public void g() {
				System.out.println("Hola Hola!");
			}
		};

		x.f();
	}
}
