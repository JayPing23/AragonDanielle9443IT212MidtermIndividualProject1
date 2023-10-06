/*
Name of Student: Aragon, Danielle John P.
Date: October 06, 2023
*/
import java.util.*;

public class PolynomialArithmetic {
    Scanner keyboard = new Scanner(System.in);

    /**
     * Main method to run the polynomial arithmetic program.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        PolynomialArithmetic program;
        try {
            program = new PolynomialArithmetic();
            program.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the polynomial arithmetic program.
     * @throws Exception if an error occurs.
     */
    public void run() throws Exception {
        byte choice = 0;
        while (choice != 6) {
            showMenu();
            choice = readChoice((byte) 1, (byte) 6);
            switch (choice) {
                case 1:
                    evaluatePolynomial();
                    break;
                case 2:
                    addPolynomials();
                    break;
                case 3:
                    subtractPolynomials();
                    break;
                case 4:
                    multiplyPolynomials();
                    break;
                case 5:
                    dividePolynomials();
                    break;
                case 6:
                    System.out.println("Thank you for using this program.");
            } // end of switch
        } // end of while
    } // end of run

    /**
     * Reads a byte choice within a specified range.
     * @param low The lower bound of the range.
     * @param high The upper bound of the range.
     * @return The user's choice.
     * @throws Exception if an error occurs.
     */
    private byte readChoice(byte low, byte high) throws Exception {
        byte choice = 0;
        System.out.print("Enter your choice<" + low + "... " + high + ">: ");
        choice = (byte) readInteger(low, high);
        return choice;
    }

    /**
     * Displays the main menu.
     */
    public void showMenu() {
        System.out.println("-----------------------MENU--------------------------");
        System.out.println("1. Evaluate a polynomial");
        System.out.println("2. Add two polynomials");
        System.out.println("3. Subtract a polynomial from another polynomial");
        System.out.println("4. Multiply two polynomials");
        System.out.println("5. Divide a polynomial by another polynomial");
        System.out.println("6. Quit");
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Evaluates a polynomial.
     * @throws Exception if an error occurs.
     */
    public void evaluatePolynomial() throws Exception {
        System.out.println("You want to evaluate a polynomial.");
        Polynomial p = readPolynomial();
        System.out.println("The polynomial entered is " + p.toString());
        System.out.print("What is the value to be assigned to the variable of the polynomial? ");
        double value = readDouble();

        System.out.println("The polynomial evaluates to : " + p.evaluate(value));
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Reads an integer within a specified range.
     * @param low The lower bound of the range.
     * @param high The upper bound of the range.
     * @return The user's input integer.
     */
    private int readInteger(int low, int high) {
        boolean validInput = false;
        int value = 0;
        while (!validInput) {
            try {
                value = Integer.parseInt(keyboard.nextLine());
                if (value < low) {
                    System.out.print("The number must not be lower than " + low + ". ");
                } else if (value > high) {
                    System.out.print("The number must not be greater than " + high + ". ");
                } else {
                    validInput = true;
                }
            } catch (Exception x) {
                System.out.println("You have to enter an integer from " + low + " to " + high + ".");
            }
        }
        return value;
    }

    /**
     * Reads a double value.
     * @return The user's input double.
     */
    private double readDouble() {
        boolean validInput = false;
        double value = 0;
        while (!validInput) {
            try {
                value = Double.parseDouble(keyboard.nextLine());
                validInput = true;
            } catch (Exception x) {
                System.out.println("You have to enter a number.");
            }
        }
        return value;
    }

    public Polynomial readPolynomial() throws Exception {
        Polynomial p = new Polynomial();
        int degree = -1;
        boolean validDegree = false;
        char literalCoefficient = 'x';

        System.out.println("The polynomial should involve one variable/literal only.");
        do {
            System.out.print("What is the literal coefficient of the polynomial in one variable? ");
            String input = keyboard.nextLine().trim();
            if (input.length() == 1 && Character.isAlphabetic(input.charAt(0))) {
                literalCoefficient = input.charAt(0);
            } else {
                System.out.println("Invalid input. Please enter a single alphabetic character.");
            }
        } while (!Character.isAlphabetic(literalCoefficient));

        do {
            System.out.print("What is the degree of the polynomial? ");
            degree = readInteger(Integer.MIN_VALUE, Integer.MAX_VALUE);
            validDegree = true;
        } while (!validDegree);

        for (int x = degree; x >= 0; x = x - 1) {
            Term term = readTerm(literalCoefficient, x);
            p.addTerm(term);
        }
        return p;
    }


    /**
     * Reads a term of a polynomial.
     * @param literal The literal coefficient of the term.
     * @param degree The degree of the term.
     * @return The user's input term.
     * @throws Exception if an error occurs.
     */
    public Term readTerm(char literal, int degree) throws Exception {
        double nCoeff = 0;
        System.out.print("Enter the numerical coefficient of the term with degree " + degree + ": ");
        nCoeff = readDouble();
        Term term = new Term(nCoeff, literal, degree);
        return term;
    }

    /**
     * Adds two polynomials and prints the result.
     * @throws Exception if an error occurs.
     */
    public void addPolynomials() throws Exception {
        System.out.println("You want to add two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second polynomial should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());

        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Sum of the polynomials : " + p1.add(p2));
        } else {
            System.out.println("The two polynomials cannot be added because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Subtracts two polynomials and prints the result.
     * @throws Exception if an error occurs.
     */
    public void subtractPolynomials() throws Exception {
        System.out.println("You want to subtract one polynomial from another.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second polynomial should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());

        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Result of subtraction : " + p1.subtract(p2));
        } else {
            System.out.println("The two polynomials cannot be subtracted because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Multiplies two polynomials and prints the result.
     * @throws Exception if an error occurs.
     */
    public void multiplyPolynomials() throws Exception {
        System.out.println("You want to multiply two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second polynomial should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());

        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Result of multiplication : " + p1.multiply(p2));
        } else {
            System.out.println("The two polynomials cannot be multiplied because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Divides two polynomials and prints the result.
     * @throws Exception if an error occurs.
     */
    public void dividePolynomials() throws Exception {
        System.out.println("You want to divide one polynomial by another.");
        System.out.println("Enter the dividend polynomial.");
        Polynomial dividend = readPolynomial();
        System.out.println("Enter the divisor polynomial.");
        System.out.println("Note that the divisor polynomial should have the same variable/literal as the dividend polynomial.");
        Polynomial divisor = readPolynomial();
        System.out.println("Dividend polynomial : " + dividend.toString());
        System.out.println("Divisor polynomial : " + divisor.toString());

        if (dividend.getTerms().get(0).getLiteral() == divisor.getTerms().get(0).getLiteral()) {
            Quotient result = dividend.divide(divisor);
            System.out.println("Quotient : " + result.getQuotientP());
            System.out.println("Remainder : " + result.getRemainderP());
        } else {
            System.out.println("The two polynomials cannot be divided because they have different literals.");
        }
        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }
} // end of PolynomialArithmetic class
