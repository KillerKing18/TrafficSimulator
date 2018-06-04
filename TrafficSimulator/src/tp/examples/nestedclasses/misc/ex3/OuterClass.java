package tp.examples.nestedclasses.misc.ex3;

class OuterClass {
    static class NestedClass {
       public static int f1;
       private static int f2;
       public int f3;
       private int f4;

       void moo() {
    	     // ...
       }
    }
    void foo() {
       NestedClass.f1 = 1;  // OK
       NestedClass.f2 = 1; // OK
     //  NestedClass.f3 = 1; // ERROR
     //  NestedClass.f4 = 1; // ERROR

     }
}