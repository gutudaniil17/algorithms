
import java.math.BigInteger;
import java.util.*;


public class Shamir {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the secret key : ");
        int secret = sc.nextInt();
        Map<Integer, Integer> shares = generateShares(secret);
        secret = combineShare(shares);
        System.out.println("The recovery key : " + secret);
    }

    public static Map<Integer, Integer> generateShares(int secret) {
        ArrayList<Integer> poly = new ArrayList<>();
        poly.add(secret);
        for (int i = 0; i < 4; i++) {
            poly.add(generateRandomPrime(BigInteger.valueOf(secret), BigInteger.valueOf(999)).intValue());
        }

        Map<Integer, Integer> shares = new HashMap<>();
        int share;
        for (int i = 1; i < 8; i++) {
            share = (int) (poly.get(0) + poly.get(1) * i + poly.get(2) * Math.pow(i, 2) + poly.get(3) * Math.pow(i, 3));
            shares.put(i, share);
        }

        System.out.println("Shares : ");
        for (int i = 1; i < 8; i++) {
            if (i != 7) {
                System.out.print("[" + i + ", " + shares.get(i) + "], ");
            } else {
                System.out.print("[" + i + ", " + shares.get(i) + "]");
            }
        }
        System.out.println();
        return shares;
    }

    public static BigInteger generateRandomPrime(BigInteger min, BigInteger max) {
        Random rand = new Random();
        BigInteger randomPrime;

        do {
            randomPrime = new BigInteger(max.bitLength(), rand);
        } while (randomPrime.compareTo(min) < 0 || !isPrime(randomPrime));

        return randomPrime;
    }

    public static boolean isPrime(BigInteger num) {
        if (num.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }
        BigInteger sqrtNum = num.sqrt();
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(sqrtNum) <= 0; i = i.add(BigInteger.ONE)) {
            if (num.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }
    public static int combineShare(Map<Integer, Integer> shares) {
        double secret = 0;
        int xi = 0;

        int[] x = new int[4];
        Set<Integer> set = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < 4; ) {
            int num = random.nextInt(7) + 1;
            if (set.add(num)) {
                x[i++] = num;
            }
        }

        int[] yi = new int[4];
        for (int i = 0; i < 4; i++) {
            yi[i] = shares.get(x[i]);
        }
        for (int i = 0; i < 4; i++) {
            double term = yi[i];
            for (int j = 0; j < 4; j++) {
                if (j != i) {
                    term *= (double) (xi - x[j]) / (x[i] - x[j]);
                }
            }
            secret += term;
        }
        return (int) secret;
    }

}