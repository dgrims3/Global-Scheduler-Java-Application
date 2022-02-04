package model;

public class Division {
    int Division_ID;
    String Division;

    public Division(int division_ID, String division) {
        Division_ID = division_ID;
        Division = division;
    }

    public int getDivision_ID() { return Division_ID; }

    public void setDivision_ID(int division_ID) { Division_ID = division_ID; }

    public String getDivision() { return Division; }

    public void setDivision(String division) { Division = division; }

    @Override public String toString(){ return Division;}
}
