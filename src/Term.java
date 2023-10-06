/*
Name of Student: Aragon, Danielle John P.
Date: October 06, 2023
*/
/**
 * The following class is a template for a term of an algebraic polynomial that
 * involves only one literal. For example, 3x^2 is an example of a term where 3
 * is the numerical coefficient, x is the literal coefficient, and 2 is the
 * degree.
 */
public class Term implements Comparable<Term> {
    private double coefficient;
    private int degree;
    private char literal;

    // Constructors
    public Term() {
        coefficient = 0;
        degree = 0;
        literal = 'x';
    }

    public Term(double coef, char literal, int degree) {
        this.coefficient = coef;
        this.literal = literal;
        this.degree = degree;
    }

    // Setter methods
    public void setCoefficient(double coef) {
        this.coefficient = coef;
    }

    public void setLiteral(char literal) {
        this.literal = literal;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    // Getter methods
    public double getCoefficient() {
        return this.coefficient;
    }

    public char getLiteral() {
        return this.literal;
    }

    public int getDegree() {
        return this.degree;
    }

    /**
     * Compares two terms based on their degrees.
     *
     * @param another The term to compare to.
     * @return 0 if degrees are equal, -1 if this term's degree is greater, 1 if
     *         this term's degree is lesser.
     */
    public int compareTo(Term another) {
        if (this.getDegree() == another.getDegree())
            return 0;
        else if (this.getDegree() > another.getDegree())
            return -1;
        else
            return 1;
    }

    /**
     * Returns a string representation of the term in the format like "3x^2".
     *
     * @return The string representation of the term.
     */
    public String toString() {
        if (coefficient == 0)
            return "";
        else if (coefficient == 1 && degree == 1)
            return "" + literal;
        else if (coefficient == 1 && degree != 1)
            return "" + literal + "^" + degree;
        else
            return (coefficient + literal + "^" + degree);
    }
}
