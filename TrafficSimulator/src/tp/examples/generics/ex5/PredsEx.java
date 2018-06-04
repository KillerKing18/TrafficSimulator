package tp.examples.generics.ex5;

import tp.examples.collections.iter.MyList;

public class PredsEx {

	public static void filter(MyList<Integer> l, MyList<Integer> d ) {
		for(int i=0; i<l.size(); i++) {
			if ( l.getElem(i) > 0) {
				d.addElem(l.getElem(i));
			}
		}
	}

	public static <T> void filter(MyList<T> l, MyList<T> d, Pred<T> p ) {
		for(int i=0; i<l.size(); i++) {
			if ( p.test( l.getElem(i) ) ) {
				d.addElem(l.getElem(i));
			}
		}
	}

	public static void test1() {
		MyList<Integer> a = new MyList<Integer>();
		MyList<Integer> b = new MyList<Integer>();

		a.addElem(1);
		a.addElem(-3);
		a.addElem(5);
		a.addElem(-1);
		
		filter(a,b);

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
		
		filter(a,b, new Pred<Integer>() {

			@Override
			public boolean test(Integer x) {
				return x>0;
			}
			
		});

		System.out.println(a);
		System.out.println(b);

	}
	
	public static void test3() {
		MyList<Person> a = new MyList<Person>();
		MyList<Person> b = new MyList<Person>();
		a.addElem( new Person("John", 4));
		a.addElem( new Person("Mike", 1));
		a.addElem( new Person("Andres", 10));
		
		filter(a,b, new Pred<Person>() {

			@Override
			public boolean test(Person x) {
				return x.getId() > 3;
			}
		});
		
		System.out.println(a);
		System.out.println(b);

	}
	
	public static void main(String[] args) {
		test3();
	}
}
