import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        // Create polynomial 6 - 2x + 5x^3
        double[] coeffs1 = {6, -2, 5};
        int[] exps1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(coeffs1, exps1);
        
        // Create polynomial 3 + 4x^2
        double[] coeffs2 = {3, 4};
        int[] exps2 = {0, 2};
        Polynomial p2 = new Polynomial(coeffs2, exps2);

        // Multiply the two polynomials
        Polynomial result = p1.multiply(p2);
        System.out.println("Result of multiplication: " + result.evaluate(2)); // Test multiplication

        // Test file-based constructor and saveToFile method
        Polynomial fromFile = new Polynomial(new File("polynomial.txt"));
        fromFile.saveToFile("output.txt");
    }
}
