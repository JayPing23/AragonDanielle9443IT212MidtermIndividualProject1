/*
Name of Student: Aragon, Danielle John P.
Date: October 06, 2023
*/
import java.util.LinkedList;

public class Polynomial {
    private LinkedList<Term> terms;

    // Constructor
    public Polynomial() {
        terms = new LinkedList<Term>();
    }

    /**
     * Adds a term to the polynomial such that terms are arranged following a
     * decreasing order of degrees.
     *
     * @param newTerm The term to be added.
     */
    public void addTerm(Term newTerm) {
        int ctr;
        boolean found = false;
        Term currTerm = null;
        for (ctr = 0; ctr < terms.size(); ctr++) {
            currTerm = terms.get(ctr);
            if (currTerm.getDegree() <= newTerm.getDegree()) {
                found = true;
                break;
            }
        }
        if (!found) {
            terms.add(newTerm);
        } else {
            if (currTerm.getDegree() < newTerm.getDegree()) {
                terms.add(ctr, newTerm);
            } else {
                currTerm.setCoefficient(currTerm.getCoefficient() + newTerm.getCoefficient());
                if (currTerm.getCoefficient() == 0) {
                    terms.remove(ctr);
                }
            }
        }
    }


    /**
     * Returns a string representation of the polynomial.
     *
     * @return The string representation of the polynomial.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (terms == null)
            return " ";
        for (int ctr = 0; ctr < terms.size(); ctr++) {
            Term currTerm = terms.get(ctr);

            if (currTerm.getCoefficient() > 0) {
                if (ctr != 0) {
                    s.append(" +");
                }
            } else {
                s.append(" -");
            }
            if (currTerm.getCoefficient() != 1 || currTerm.getDegree() == 0) {
                s.append(" ").append(Math.abs(currTerm.getCoefficient()));
            }
            switch (currTerm.getDegree()) {
                case 0:
                    break;
                case 1:
                    s.append(currTerm.getLiteral());
                    break;
                default:
                    s.append(currTerm.getLiteral()).append("^").append(currTerm.getDegree());
            }
        }
        return s.toString();
    }

    /**
     * Evaluates the polynomial for a given value of the variable.
     *
     * @param value The value of the variable.
     * @return The result of evaluating the polynomial.
     */
    public double evaluate(double value) {
        double sum = 0;
        for (int ctr = 0; ctr < terms.size(); ctr++) {
            Term currTerm = terms.get(ctr);
            sum += currTerm.getCoefficient() * Math.pow(value, currTerm.getDegree());
        }
        return sum;
    }

    /**
     * Sets the terms of the polynomial to the given list of terms.
     *
     * @param t The list of terms to set.
     */
    public void setTerms(LinkedList<Term> t) {
        terms = t;
    }

    /**
     * Returns the list of terms in the polynomial.
     *
     * @return The list of terms.
     */
    public LinkedList<Term> getTerms() {
        return terms;
    }

    /**
     * Adds another polynomial to this polynomial.
     *
     * @param otherPolynomial The polynomial to add.
     * @return The result of adding the two polynomials.
     * @throws Exception If there is an error during the addition.
     */
    public Polynomial add(Polynomial otherPolynomial) throws Exception {
        // Step 1: Declare result as a Polynomial that will eventually represent the sum polynomial
        Polynomial result = new Polynomial();

        // Step 2: Construct a new LinkedList of Term (resultTerms) that will hold the Terms of the sum Polynomial
        LinkedList<Term> resultTerms = new LinkedList<Term>();

        // Step 3: Construct a copy of each term of this (first) Polynomial and add the constructed Term to resultTerms
        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            resultTerms.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(),
                    currentTerm.getDegree()));
        }

        // Step 4: Let resultTerms be the terms of result (i.e., the sum polynomial)
        result.setTerms(resultTerms);

        // Step 5: For each term of the other polynomial, construct a copy and assign it to sTerm
        for (int ctr2 = 0; ctr2 < otherPolynomial.getTerms().size(); ctr2++) {
            Term currentTerm = otherPolynomial.getTerms().get(ctr2);
            Term sTerm = new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(),
                    currentTerm.getDegree());

            // Step 6: Add sTerm to the sum polynomial (result)
            result.addTerm(sTerm);
        }

        // Step 7: If the result polynomial has no term, let result have the term 0x^0
        if (result.getTerms().size() == 0)
            result.addTerm(new Term(0, 'x', 0));

        // Step 8: Return the result
        return result;
    }
    /**
     * Subtracts another polynomial from this polynomial.
     *
     * @param otherPolynomial The polynomial to subtract.
     * @return The result of subtracting the two polynomials.
     * @throws Exception If there is an error during subtraction.
     */
    public Polynomial subtract(Polynomial otherPolynomial) throws Exception {
        // Step 1: Declare result as a Polynomial that will eventually represent the difference polynomial
        Polynomial result = new Polynomial();

        // Step 2: Construct a new LinkedList of Term (resultTerms) that will hold the Terms of the difference Polynomial
        LinkedList<Term> resultTerms = new LinkedList<Term>();

        // Step 3: Construct a copy of each term of this (first) Polynomial and add the constructed Term to resultTerms
        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            resultTerms.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(),
                    currentTerm.getDegree()));
        }

        // Step 4: Let resultTerms be the terms of result (i.e., the difference polynomial)
        result.setTerms(resultTerms);

        // Step 5: For each term of the other polynomial, construct a copy of the term and assign it to sTerm
        for (int ctr2 = 0; ctr2 < otherPolynomial.getTerms().size(); ctr2++) {
            Term currentTerm = otherPolynomial.getTerms().get(ctr2);
            Term sTerm = new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(),
                    currentTerm.getDegree());

            // Step 6: Multiply the numerical coefficient field of sTerm by -1
            sTerm.setCoefficient(-sTerm.getCoefficient());

            // Step 7: Add sTerm to the difference polynomial (result)
            result.addTerm(sTerm);
        }

        // Step 8: If the result polynomial has no term, let result have the term 0x^0
        if (result.getTerms().size() == 0)
            result.addTerm(new Term(0, 'x', 0));

        // Step 9: Return the result
        return result;
    }
    /**
     * Multiplies this polynomial by another polynomial.
     * The method assumes that the polynomials have the same literals and
     * it follows the prescription of the Term class.
     *
     * @param otherPolynomial The polynomial to multiply by.
     * @return The result of multiplying the two polynomials.
     * @throws Exception If there is an error during multiplication.
     */
    public Polynomial multiply(Polynomial otherPolynomial) throws Exception {
        // Step 1: Create a new Polynomial to store the result of multiplication
        Polynomial result = new Polynomial();

        // Step 2: Iterate through the terms of the first polynomial (this)
        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm1 = this.getTerms().get(ctr);

            // Step 3: Iterate through the terms of the second polynomial (otherPolynomial)
            for (int ctr2 = 0; ctr2 < otherPolynomial.getTerms().size(); ctr2++) {
                Term currentTerm2 = otherPolynomial.getTerms().get(ctr2);

                // Step 4: Calculate the coefficient and degree of the product term
                double productCoefficient = currentTerm2.getCoefficient() * currentTerm1.getCoefficient();
                int productDegree = currentTerm2.getDegree() + currentTerm1.getDegree();

                // Step 5: Create a new Term representing the product term and add it to the result polynomial
                result.addTerm(new Term(productCoefficient, currentTerm1.getLiteral(), productDegree));
            } // End of second for loop (ctr2)
        } // End of first for loop (ctr)

        // Step 6: If the result polynomial has no term, add a term representing 0x^0
        if (result.getTerms().size() == 0)
            result.addTerm(new Term(0, 'x', 0));

        // Step 7: Return the result polynomial
        return result;
    }
    /**
     * Divides this polynomial by the divisor polynomial.
     *
     * @param divisor The polynomial to divide by.
     * @return The quotient and remainder of the division.
     * @throws Exception If there is an error during division.
     */
    public Quotient divide(Polynomial divisor) throws Exception {
        // Step 1: Create a Quotient object to store the result
        Quotient result = new Quotient();

        // Step 2: Initialize variables for quotient, remainder, and dividend
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();
        LinkedList<Term> dividend = new LinkedList<Term>();

        Term qTerm;
        Polynomial subtrahend = new Polynomial();

        // Step 3: Copy the terms of this polynomial into the dividend
        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            dividend.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree()));
        }

        // Step 4: Set the remainder polynomial to the dividend
        remainder.setTerms(dividend);

        // Step 5: Perform long division
        while (remainder != null && remainder.getTerms().get(0).getDegree() >= divisor.getTerms().get(0).getDegree()) {
            Term numTerm = remainder.getTerms().get(0);
            Term divTerm = divisor.getTerms().get(0);

            // Calculate the term for the quotient
            qTerm = new Term(numTerm.getCoefficient() / divTerm.getCoefficient(), numTerm.getLiteral(),
                    numTerm.getDegree() - divTerm.getDegree());

            // Add the quotient term to the quotient polynomial
            quotient.addTerm(qTerm);

            LinkedList<Term> pQTerm = new LinkedList<Term>();
            pQTerm.add(qTerm);

            // Create a multiplier polynomial for subtracting
            Polynomial multiplier = new Polynomial();
            multiplier.setTerms(pQTerm);

            // Subtract the product of the divisor and quotient term from the remainder
            subtrahend = multiplier.multiply(divisor);
            remainder = remainder.subtract(subtrahend);
        }

        // Step 6: If the quotient has no terms, add a term representing 0x^0
        if (quotient.getTerms().size() == 0)
            quotient.addTerm(new Term(0, 'x', 0));

        // Step 7: Set the quotient and remainder in the result Quotient object
        result.setQuotientP(quotient);

        // Step 8: If the remainder has no terms, add a term representing 0x^0
        if (remainder.getTerms().size() == 0)
            remainder.addTerm(new Term(0, 'x', 0));

        result.setRemainderP(remainder);

        // Step 9: Return the quotient and remainder
        return result;
    }

}

