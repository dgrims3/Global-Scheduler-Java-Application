package model;

/**
 * This class creates division objects. First level divisions are states, provinces, etc.
 */
public class Division {
    int Division_ID;
    String Division;

    /**
     * Constructor for division Object.
     * @param division_ID
     * @param division
     */
    public Division(int division_ID, String division) {
        Division_ID = division_ID;
        Division = division;
    }

    /**
     * gets division object ID.
     * @return int
     */
    public int getDivision_ID() { return Division_ID; }

    /**
     * gets name of country division.
     * @return String
     */
    public String getDivision() { return Division; }

    /**
     * Override method for printing Strings.
     * @return String
     */
    @Override public String toString(){ return Division;}
}
