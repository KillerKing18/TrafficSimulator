package tp.examples.generics.ex5;

import java.util.Arrays;

public class SortEx {
	
	public static void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int x = a[i];
			int j = i - 1;
			while (j >= 0 && a[j] > x) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = x;
		}
	}

	public static <T extends Comparable<T>> void sort(T[] a) {
		for (int i = 1; i < a.length; i++) {
			T x = a[i];
			int j = i - 1;
			while (j >= 0 && a[j].compareTo(x) > 0) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = x;
		}
	}
	
	public static void test1() {
		int[] a = new int[] {5,2,8,5,3,10};
		sort(a);		
		System.out.println( Arrays.toString(a));
	}

	public static void test2() {
		Person[] a = new Person[] { new Person("John",4), new Person("Mike",1), new Person("Andres",10) };
		sort(a);		
		System.out.println( Arrays.toString(a));
	}

	public static void test3() {
		Integer[] a = new Integer[] { 6,3,10,1,15 };
		sort(a);		
		System.out.println( Arrays.toString(a));
	}


	public static void main(String []args) {
		test2();

	}

}
