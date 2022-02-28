package model;

/**
 * This is a class for Country Objects. Takes country information from database and creates a country Object
 */
public class Country {
    int country_ID;
    String country;

    /**
     * Constructor for the country class.
      * @param country_ID
     * @param country
     */
    public Country(int country_ID, String country) {
        this.country_ID = country_ID;
        this.country = country;
    }

    /**
     * Override method for returning a string
     * @return String
     */
    @Override public String toString(){
        return country;
    }
}
