package tp.examples.nestedclasses.misc.ex1;

class OuterClass {
    public static int f1;
    private static int f2;
    int f3;
    public static class NestedClass {
       void moo() {
         f1 = 1;  // OK
         f2 = 1; // OK
         // f3 = 1; // ERROR
       }
    }
}