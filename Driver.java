public class Driver {
    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3)); // Expected output: 0.0

        double[] c1 = {6, 0, 0, 5}; // Polynomial 6 + 5x^3
        Polynomial p1 = new Polynomial(c1);

        double[] c2 = {0, -2, 0, 0, -9}; // Polynomial -2x - 9x^4
        Polynomial p2 = new Polynomial(c2);

        Polynomial s = p1.add(p2); // Adding polynomials
        System.out.println("s(0.1) = " + s.evaluate(0.1)); // Expected output: 5.8041

        if (s.hasRoot(1)) {
            System.out.println("1 is a root of s");
        } else {
            System.out.println("1 is not a root of s"); // Expected output
        }
    }
}