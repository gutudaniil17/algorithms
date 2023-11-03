package bloom;

public class Bloom {
    private Integer z;
    private Integer[][] D;

    private Integer[][] g1 = new Integer[3][1];
    private Integer[][] g2 = new Integer[3][1];

    private Integer k1 = 0;
    private Integer k2 = 0;

    public Bloom(Integer z, Integer[][] d) {
        this.z = z;
        D = d;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                g1[i][j] = 0;
                g2[i][j] = 0;

            }
        }

    }

    public Integer[][] calculateMatrixG1(Integer[][] l1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 3; k++)
                    g1[i][j] += D[i][k]
                            * l1[k][j];
            }
        }

        return g1;
    }

    public Integer[][] calculateMatrixG2(Integer[][] l2) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 3; k++)
                    g2[i][j] += D[i][k]
                            * l2[k][j];
            }
        }
        return g2;
    }

    public Integer calculateK1(Integer[][] l2) {
        for (int i = 0; i < g1.length; i++) {
            k1 += g1[i][0] * l2[i][0];
        }
        System.out.println("\nk1 " + k1 + " mod " + z + " = " + (k1 % z));
        return k1;
    }

    public Integer calculateK2(Integer[][] l1) {
        for (int i = 0; i < g1.length; i++) {
            k2 += g1[i][0] * l1[i][0];
        }
        System.out.println("k2 " + k2 + " mod " + z + " = " + (k1 % z));
        return k1;

    }

    public void printD() {
        System.out.println("\nmatrix D:");
        for (int i = 0; i < D.length; i++) {
            for (int j = 0; j < D[0].length; j++) {
                System.out.print(D[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void printG1() {
        System.out.println("\nmatrix G1:");
        for (int i = 0; i < g1.length; i++) {
            for (int j = 0; j < g1[0].length; j++) {
                System.out.print(g1[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void pringG2() {
        System.out.println("\nmatrix G2:");
        for (int i = 0; i < g2.length; i++) {
            for (int j = 0; j < g2[0].length; j++) {
                System.out.print(g2[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Main {

    public static void main(String[] args) {
        Integer[][] d = {
                {1,1, 1},
                {3, 3, 8},
                {2, 7, 16}};
        Integer[][] l1 = {{20}, {12}, {1}};
        Integer[][] l2 = {{13}, {6}, {81}};

        Bloom bloom = new Bloom(89, d);

        bloom.printD();

        bloom.calculateMatrixG1(l1);
        bloom.printG1();

        bloom.calculateMatrixG2(l2);
        bloom.pringG2();

        bloom.calculateK1(l2);
        bloom.calculateK2(l2);

    }
}

