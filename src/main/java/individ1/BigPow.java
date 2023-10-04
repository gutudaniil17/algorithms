package individ1;

import java.util.Scanner;


public class BigPow {

    public static void main(String[] args) {
        System.out.println("Grad mare cu modulo");
        System.out.println("a ^ k mod n (unde k - numar mare)");
        System.out.println("Introduce a k n: ");

        long a = readLong();
        long k = readLong();
        long n = readLong();

        System.out.println("Resultat: " + pow_mod(a, k, n));
    }

    /** a ^ k mod n */
    public static long pow_mod(long a, long k, long n) {
        long b = 1;
        while (k > 0) {
            if (k % 2 == 0) {
                k /= 2;
                a = (a * a) % n;
            } else {
                k--;
                b = (b * a) % n;
            }
            System.out.println(b);
        }
        return b;
    }

    public static long readLong() {
        return new Scanner(System.in).nextLong();
    }
}

