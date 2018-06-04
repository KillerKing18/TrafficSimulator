package tp.examples.generics.ex5;

import tp.examples.collections.iter.MyList;

public class FunEx {

	public static void transform(MyList<Integer> l, MyList<Integer> d ) {
		for(int i=0; i<l.size(); i++) {
			d.addElem(l.getElem(i)+1);
		}
	}

	public static <A,B> void transform(MyList<A> l, MyList<B> d, Fun<A,B> f ) {
		for(int i=0; i<l.size(); i++) {
			d.addElem( f.apply(l.getElem(i)));
		}
	}

	public static void test1() {
		MyList<Integer> a = new MyList<Integer>();
		MyList<Integer> b = new MyList<Integer>();

		a.addElem(1);
		a.addElem(-3);
		a.addElem(5);
		a.addElem(-1);
		
		transform(a,b);

		System.out.println(a);
		System.out.println(b);

	}
	
	public static void test2() {
		MyList<Integer> a = new MyList<Integer>();
		MyList<Integer> b = new MyList<Integer>();

		a.addElem(1);
		a.addElem(-3);
		a.addElem(5);
		a.addElem(-1);
		
		transform(a,b, new Fun<Integer, Integer>() {

			@Override
			public Integer apply(Integer x) {
				return x+1;
			}
		
		});

		System.out.println(a);
		System.out.println(b);

	}
	
	public static void test3() {
		MyList<Person> a = new MyList<Person>();
		a.addElem( new Person("John", 4));
		a.addElem( new Person("Mike", 1));
		a.addElem( new Person("Andres", 10));

		MyList<Integer> b = new MyList<Integer>();

		transform(a,b, new Fun<Person,Integer>() {

			@Override
			public Integer apply(Person x) {
				
				return x.getId();
			}
			
		});
		
		System.out.println(a);
		System.out.println(b);

	}

	public static void main(String[] args) {
		test3();
	}
}
