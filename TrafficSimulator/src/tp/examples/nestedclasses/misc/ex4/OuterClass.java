package tp.examples.nestedclasses.misc.ex4;

class OuterClass {
    static class NestedClass1 {
       public static int f1;
       private int f4;

       static class NestedClass2 {
         private static int f2;
         public int f3;
         //...
        }
    }
    void foo() {
       NestedClass1.f1 = 1;  // OK
       NestedClass1.NestedClass2.f2 = 1; // OK
       // NestedClass1.NestedClass2.f3 = 1; // ERROR
       // NestedClass1.f4 = 1; // ERROR

     }
}