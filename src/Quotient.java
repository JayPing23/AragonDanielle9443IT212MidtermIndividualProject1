/*
Name of Student: Aragon, Danielle John P.
Date: October 06, 2023
*/
public class Quotient {
    Polynomial quotientP;
    Polynomial remainderP;

    public Quotient() {
        quotientP = null;
        remainderP = null;
    }

    // Getter and Setter methods
    public void setQuotientP(Polynomial q) {
        quotientP = q;
    }

    public void setRemainderP(Polynomial p) {
        remainderP = p;
    }

    public Polynomial getQuotientP() {
        return quotientP;
    }

    public Polynomial getRemainderP() {
        return remainderP;
    }

    /**
     * Returns a string representation of the quotient and remainder polynomials.
     *
     * @return The string representation of the quotient and remainder polynomials.
     */
    public String toString() {
        return (" Quotient: " + quotientP.toString() + " Remainder: " + remainderP.toString());
    }
}

