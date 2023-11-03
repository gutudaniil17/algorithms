package adler;

import java.math.BigInteger;
import java.util.Scanner;

public class Adler {
    public static final int MODULO_CONSTANT = 65521;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter message to encrypt");
        String plainText = scanner.nextLine();

        System.out.println("Message is : " + plainText + " Hash: " + createAdlerHash(plainText));
        plainText = "The quick brown fox jumps over the lazy dog";

        System.out.println("Message is : " + plainText + " Hash: " + createAdlerHash(plainText));

        scanner.close();
    }

    public static String createAdlerHash(String plainText) {
        BigInteger a = BigInteger.ONE, b = BigInteger.ZERO;

        for (int i = 0; i < plainText.length(); i++) {
            a = a.add(BigInteger.valueOf(plainText.charAt(i)));
            b = b.add(a);
        }
        b = b.mod(BigInteger.valueOf(MODULO_CONSTANT));
        a = a.mod(BigInteger.valueOf(MODULO_CONSTANT));
        return convertToMyHex(b) + convertToMyHex(a);

    }

    public static String convertToMyHex(BigInteger num) {
        return String.format("%4s", num.toString(16)).replace(" ", "0");
    }
}