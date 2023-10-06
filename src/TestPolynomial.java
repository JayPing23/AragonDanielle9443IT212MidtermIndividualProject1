/*
Name of Student: Aragon, Danielle John P.
Date: October 06, 2023
*/
/**
 * TestPolynomial class to demonstrate polynomial operations.
 */
public class TestPolynomial {
    public static void main(String[] args) {
        try {
            // Create a new Polynomial instance 'p'
            Polynomial p = new Polynomial();

            // Add terms to the polynomial 'p'
            p.addTerm(new Term(1,'x',2));
            p.addTerm(new Term(4, 'x', 3));
            p.addTerm(new Term(-3, 'x', 1));
            p.addTerm(new Term(1, 'x', 0));

            // Create another Polynomial instance 'other'
            Polynomial other = new Polynomial();

            // Add terms to the polynomial 'other'
            other.addTerm(new Term(2,'x',1));
            other.addTerm(new Term(-1,'x',2));

            // Print the sample polynomial and evaluate it at x=2
            System.out.println("Sample Polynomial: " + p.toString());
            System.out.println("Value of the sample polynomial at x=2: " + p.evaluate(2));

            // Example of addition of polynomials
            Polynomial sP = p.add(other);
            System.out.println("\nExample of addition of polynomials");
            System.out.println("(" + p.toString() + ") + (" + other.toString() + ") = " + sP.toString());

            // Example of subtraction of polynomials
            Polynomial dP = p.subtract(other);
            System.out.println("\nExample of subtraction of polynomials");
            System.out.println("(" + p.toString() + ") - (" + other.toString() + ") = " + dP.toString());

            // Example of multiplication of polynomials
            Polynomial pP = p.multiply(other);
            System.out.println("\nExample of multiplication of polynomials");
            System.out.println("(" + p.toString() + ") * (" + other.toString() + ") = " + pP.toString());

            // Example of division of polynomials
            Quotient qP = p.divide(other);
            System.out.println("\nExample of division of polynomials");
            System.out.println("(" + p.toString() + ") / (" + other.toString() + ") = " + qP.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } // end of catch block
    } // end of main
} // end of TestPolynomial class
