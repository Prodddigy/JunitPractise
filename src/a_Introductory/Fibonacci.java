package a_Introductory;

public class Fibonacci {
	public int fib(int n) {
		switch (n) {
			case 0: return 0;
			case 1: return 1;//2: 0 1 1 2
			default: return (fib(n - 1) + fib(n - 2));
		}
	}
}
