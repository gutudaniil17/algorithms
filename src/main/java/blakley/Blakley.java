package blakley;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class Blakley {


    private static final int BIT_LENGTH = 32;

    public static void main(String[] args) {

        final int[] m = {2, 11, 3, 13, 5, 19, 7};
        System.out.println("Key sharing:\nInitial array m = " + Arrays.toString(m));

        final int S = getIntBetweenProductsOfTwoPartsOfArray(m);
        System.out.println("Secret S between two products = " + S);

        final int M = Arrays.stream(m).reduce(1, (a, b) -> a * b);
        System.out.println("Product M of all elements of array m = " + M);

        final int[] S_FOOTPRINTS = Arrays
                .stream(m)
                .map(mi -> S % mi)
                .toArray();
        System.out.println("Footprints S[i] (S mod m[i]) = " + Arrays.toString(S_FOOTPRINTS));

        System.out.println("Parts of key per person: ");
        for (int i = 0; i < m.length; i++) {
            System.out.printf("P%d = (%d, %d, %d)\n", i, S_FOOTPRINTS[i], m[i], M);
        }


        final int[] Mi = Arrays
                .stream(m)
                .map(mi -> M / mi)
                .toArray();
        System.out.println("\nKey recovery:\nMi (M / m[i]) = " + Arrays.toString(Mi));

        final int[] Ni = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            Ni[i] = BigInteger
                    .valueOf(Mi[i])
                    .modInverse(BigInteger.valueOf(m[i]))
                    .intValue();
        }
        System.out.println("Ni (Mi^(-1) / mi) = " + Arrays.toString(Ni));

        final int[] Ii = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            Ii[i] = S_FOOTPRINTS[i] * Mi[i] * Ni[i];
        }
        System.out.println("Ii (Si * Mi * Ni) = " + Arrays.toString(Ii));

        final int I_SUM = Arrays.stream(Ii).sum();
        int tempProduct = 1;
        for (int i = 0; i < m.length; i++) {
            if (Ii[i] != 0) {
                tempProduct *= m[i];
            }
        }
        final int S_RECOVERED = I_SUM % tempProduct;
        System.out.println("Recovered Secret S = " + S_RECOVERED);
    }

    public static int getIntBetweenProductsOfTwoPartsOfArray(final int[] m) {
        if (m.length <= 1) {
            throw new IllegalArgumentException("Invalid vector M size: <= 1");
        }

        final int half = getHalf(m);

        final int firstPartProduct = Arrays.stream(Arrays.copyOfRange(m, 0, half))
                .reduce(1, (a, b) -> a * b);

        final int secondPartProduct = Arrays.stream(Arrays.copyOfRange(m, half, m.length))
                .reduce(1, (a, b) -> a * b);


        return (firstPartProduct < secondPartProduct) ?
                new Random().nextInt(firstPartProduct + 1, secondPartProduct) :
                new Random().nextInt(secondPartProduct + 1, firstPartProduct);
    }

    public static int getHalf(final int[] m) {
        return ((m.length & 2) == 0) ?
                m.length / 2 :
                m.length / 2 + 1;
    }

}

