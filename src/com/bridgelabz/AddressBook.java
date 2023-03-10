package com.bridgelabz;
import java.sql.*;
import java.util.Scanner;
public class AddressBook {
    /**
     * here we perform on address book service database
     */
    static String jdbc = "jdbc:mysql://localhost:3306/AddressBookService";
    static String userName = "root";
    static String password = "M@dhuri3";
    static ContactDetails details = new ContactDetails();
    static Scanner input = new Scanner(System.in);
    public static void read(){
        try {
            Connection connection = DriverManager.getConnection(jdbc,userName, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM addressBook");
            System.out.println("firstName   lastName  address     city    state    zip     phoneNumber     email");
            System.out.println("********************************************************************************");
            while (result.next()) {
                ContactDetails details = new ContactDetails();
                details.setFirstName(result.getString("firstName"));
                details.setLastName(result.getString("lastName"));
                details.setAddress(result.getString("address"));
                details.setCity(result.getString("city"));
                details.setState(result.getString("state"));
                details.setZip(result.getInt("zip"));
                details.setPhoneNumber(result.getInt("phoneNumber"));
                details.setEmail(result.getString("email"));
                System.out.println(details.getFirstName()+"     "+details.getLastName()+"     "+details.getAddress()+"     "+details.getCity()+"     "+details.getState()+"     "+details.getZip()+"     "+details.getPhoneNumber()+"     "+details.getEmail());
            }
        } catch(Exception e) {
            System.out.println("exception occurred" + e);
        }
    }
    public static void main(String[] args) {
        read();
    }
}