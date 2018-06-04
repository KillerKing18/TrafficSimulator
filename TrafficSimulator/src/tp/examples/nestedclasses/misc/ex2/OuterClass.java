package tp.examples.nestedclasses.misc.ex2;

class OuterClass {
    public static int f1;
    int f3;
    static class NestedClass1 {
       private static int f2;
       private static class NestedClass2 {
          void moo() {
             f1 = 1;  // OK
             f2 = 1; // OK
            // f3 = 1; // ERROR
          }
          // ...
       }
       // ...
     }
}