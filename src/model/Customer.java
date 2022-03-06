package model;

import DAO.JDBC;

import java.sql.*;

/**
 * This class creates Customer Objects. This class uses information from the mySQL database.
 */
public class Customer {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private int Division_ID;

    /**
     * Constructor for the Customer class.
     * @param customer_ID
     * @param customer_Name
     * @param address
     * @param postal_Code
     * @param phone
     * @param division_ID
     */
    public Customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, int division_ID) {
        Customer_ID = customer_ID;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Division_ID = division_ID;
    }

    /**
     * gets customer ID.
     * @return int
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * gets customer name.
     * @return String
     */

 public String getCustomer_Name() {
        return Customer_Name;
    }

    /**
     * gets customer address.
     * @return String
     */
    public String getAddress() {
        return Address;
    }

    /**
     * gets customer postal code.
     * @return String
     */
 public String getPostal_Code() {
        return Postal_Code;
    }

    /**
     * gets customer phone.
     * @return String
     */
 public String getPhone() {
        return Phone;
    }

    /**
     * gets customer division information.
     * @return int
     */
 public int getDivision_ID() {
        return Division_ID;
    }


}
