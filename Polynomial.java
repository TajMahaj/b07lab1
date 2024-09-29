import java.io.*;
import java.util.*;

public class Polynomial {
    private double[] coefficients; 
    private int[] exponents;       

    public Polynomial() {
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }

    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    public Polynomial multiply(Polynomial other) {
        List<Double> resultCoeffs = new ArrayList<>();
        List<Integer> resultExponents = new ArrayList<>();

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                double newCoeff = this.coefficients[i] * other.coefficients[j];
                int newExp = this.exponents[i] + other.exponents[j];

                int index = resultExponents.indexOf(newExp);
                if (index != -1) {
                    resultCoeffs.set(index, resultCoeffs.get(index) + newCoeff);
                } else {
                    resultCoeffs.add(newCoeff);
                    resultExponents.add(newExp);
                }
            }
        }

        double[] finalCoeffs = resultCoeffs.stream().mapToDouble(d -> d).toArray();
        int[] finalExponents = resultExponents.stream().mapToInt(i -> i).toArray();

        return new Polynomial(finalCoeffs, finalExponents);
    }

    public Polynomial(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();

        String[] terms = line.split("(?=[+-])"); 
        List<Double> coeffList = new ArrayList<>();
        List<Integer> expList = new ArrayList<>();

        for (String term : terms) {
            if (term.contains("x")) {
                String[] parts = term.split("x\\^?");
                double coeff = Double.parseDouble(parts[0].isEmpty() || parts[0].equals("+") ? "1" : parts[0]);
                int exp = parts.length > 1 ? Integer.parseInt(parts[1]) : 1;
                coeffList.add(coeff);
                expList.add(exp);
            } else {
                coeffList.add(Double.parseDouble(term));
                expList.add(0); 
            }
        }

        this.coefficients = coeffList.stream().mapToDouble(d -> d).toArray();
        this.exponents = expList.stream().mapToInt(i -> i).toArray();
    }

    public void saveToFile(String filename) throws IOException {
        StringBuilder polynomialString = new StringBuilder();
        for (int i = 0; i < coefficients.length; i++) {
            if (i != 0 && coefficients[i] > 0) polynomialString.append("+");
            polynomialString.append(coefficients[i]);
            if (exponents[i] != 0) polynomialString.append("x^").append(exponents[i]);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(polynomialString.toString());
        writer.close();
    }
}
