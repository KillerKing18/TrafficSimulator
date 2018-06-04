package tp.examples.nestedclasses.misc.ex15;

public class B {
	public void q() {
		System.out.println("Hola Hola Hola!");
	}

	public A h() {
		return new A() {
			@Override
			public void g() {
				q();
			}
		};
	}
	
	public static void main(String[] args) {
		B x = new B();
		A y = x.h();
		y.f();
	}

}
