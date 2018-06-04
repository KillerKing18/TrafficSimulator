package tp.examples.nestedclasses.misc.ex7;

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
}
class SomeOtherClass {
    void foo() {
       OuterClass.NestedClass1 x = new OuterClass.NestedClass1();
       OuterClass.NestedClass1.NestedClass2 y = 
            new OuterClass.NestedClass1.NestedClass2();
     }
}