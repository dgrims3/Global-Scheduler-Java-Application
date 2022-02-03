package model;

public class Country {
    int country_ID;
    String country;

    public Country(int country_ID, String country) {
        this.country_ID = country_ID;
        this.country = country;
    }

    public int getCountry_ID() { return country_ID; }

    public void setCountry_ID(int country_ID) { this.country_ID = country_ID; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    @Override public String toString(){
        return country;
    }
}
