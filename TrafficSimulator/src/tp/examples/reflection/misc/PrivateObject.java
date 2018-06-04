package tp.examples.reflection.misc;

public class PrivateObject {
	private String f = null;
	public String g = null;

	public PrivateObject(String f) {
		this.f = f;
		this.g = f;
	}

	private String getF() {
		return this.f;
	}

	public void h() {
		System.out.println("123");
	}
	
}
