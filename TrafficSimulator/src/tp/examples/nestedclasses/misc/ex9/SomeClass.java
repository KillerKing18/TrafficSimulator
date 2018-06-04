package tp.examples.nestedclasses.misc.ex9;

import tp.examples.nestedclasses.misc.ex9.OuterClass.InnerClass;

public class SomeClass {

	public static void m1() {
		 //  InnerClass a =  new InnerClass(); // ERROR		
	}
	
	public static void m2() {
		   
		   OuterClass x = new OuterClass(1); 
		   OuterClass.InnerClass a = x.foo();
	}		   
	
	public static void m3() {
		   OuterClass x = new OuterClass(1); 
		   OuterClass.InnerClass a =  x. new InnerClass();
	}
}

