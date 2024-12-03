package PrimeJunkies;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// This program uses the Sieve of Eratosthenes to find all primes up to a given number.
public class FastPrimeFinder {
    public static void main(String[] args) {
        int n = takeInput();
        Queue<Integer> numbers = new LinkedList<>();
        Queue<Integer> primes = new LinkedList<>();

        for (int i = 2; i <= n; i++) {
            numbers.add(i);
        }

    while (!numbers.isEmpty()) {
        int prime = numbers.remove();
            primes.add(prime);
            numbers.removeIf(num -> num % prime == 0);
        }

        System.out.println("Primes up to " + n + ": " + primes);
    }

    public static int takeInput() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = s.nextInt();
        s.close();
        return n;
    }
}
