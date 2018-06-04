package tp.examples.nestedclasses.misc.ex6;

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
       NestedClass1 x = new NestedClass1();
       NestedClass1.NestedClass2 y = new NestedClass1.NestedClass2();
       x.f4 = 1; // OK
       y.f3 = 2; // OK       
     }
}