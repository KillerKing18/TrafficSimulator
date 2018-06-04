package tp.examples.nestedclasses.misc.ex5;

class OuterClass {
    static class NestedClass {
       public static int f1;
       private static int f2;
       int f3;
       private void moo() {
    	     // ...
       }
       // ...
    }
    void foo() {
        NestedClass x = new NestedClass();
        NestedClass.f1 = 1; // OK
        NestedClass.f2 = 1; // OK
        x.f3 = 1; // OK
    }
}