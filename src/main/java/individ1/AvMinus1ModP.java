package individ1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AvMinus1ModP {
    public static void main(String[] args) {
        int b = 0, n = 0, n0 = 0, b0 = 0, q = 0, r = 1, t0 = 0, t = 0, temp = 0;

        System.out.println(" b^-1 mod n ");
        System.out.print("Introduce b (-1): ");
        BufferedReader b1 = new BufferedReader(new InputStreamReader(System.in));
        String inp = "";
        try {
            inp = b1.readLine();
        } catch (IOException e) {

        }

        if (inp.equals("")) {
            System.out.println("Nu era introdus nimic.");
            System.exit(0);
        }

        b = Integer.parseInt(inp);

        System.out.print("Introduce n: ");
        try {
            inp = b1.readLine();
        } catch (IOException e) {

        }

        if (inp.equals("")) {
            System.out.println("Nu era introdus nimic.");
            System.exit(0);
        }

        n = Integer.parseInt(inp);

        if (b <= 1 || n <= 0) {
            System.out.println("b si n trebuie sa fie mai mari decit 0.");
            System.exit(0);
        }

        // ################################################

        System.out.println("b = " + Integer.toString(b) + ", n = "
                + Integer.toString(n) + "\n");

        n0 = n;
        b0 = b;
        t0 = 0;
        t = 1;
        q = n0 / b0;
        r = n0 - (q * b0);

        System.out.println("n0\tb0\tq\tr\tt0\tt\ttemp\n");

        while (r > 0) {
            temp = t0 - q * t;
            if (temp >= 0)
                temp = temp % n;
            else
                temp = n - ((-temp) % n);

            System.out.println(n0 + "\t" + b0 + "\t" + q + "\t" + r + "\t" + t0
                    + "\t" + t + "\t" + temp);

            n0 = b0;
            b0 = r;
            t0 = t;
            t = temp;
            q = n0 / b0;
            r = n0 - (q * b0);
        }
        System.out.println(n0 + "\t" + b0 + "\t" + q + "\t" + r + "\t" + t0
                + "\t" + t);
        if (b0 != 1)
            System.out.println("b nu are mod n");
        else
            System.out.println("Raspuns: " + t);
    }
}

