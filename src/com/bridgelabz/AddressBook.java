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
    public static void edit() {
        /**
         * edit the contact details from address book table
         */
        try {
            Connection connection = DriverManager.getConnection(jdbc, userName, password);
            PreparedStatement prepare = connection.prepareStatement("update addressBook " + "set firstName = ?,address = ?,lastName=?,city  =?,state = ?,zip=?,PhoneNumber  =?,email=? " + "where firstName = ?");
            System.out.print("Enter Exiting name : ");
            String name = input.next();
            System.out.print("Enter first name : ");
            details.setFirstName(input.next());
            System.out.print("Enter last name : ");
            details.setLastName(input.next());
            System.out.print("Enter address : ");
            details.setAddress(input.next());
            System.out.print("Enter city : ");
            details.setCity(input.next());
            System.out.print("Enter State : ");
            details.setState(input.next());
            System.out.print("Enter zip : ");
            details.setZip(input.nextInt());
            System.out.print("Enter phone number : ");
            details.setPhoneNumber(input.nextInt());
            System.out.print("Enter email : ");
            details.setEmail(input.next());
            prepare.setString(1, details.getFirstName());
            prepare.setString(2, details.getLastName());
            prepare.setString(3, details.getAddress());
            prepare.setString(4, details.getCity());
            prepare.setString(5, details.getState());
            prepare.setInt(6, details.getZip());
            prepare.setInt(7, details.getPhoneNumber());
            prepare.setString(8, details.getEmail());
            prepare.setString(9, name);
            prepare.executeLargeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void dateInRange() {
        /**
         * read the joining date of employee payroll service database
         */
        try {
            Connection connection = DriverManager.getConnection(jdbc, userName, password);
            String query = "SELECT * FROM AddressBook WHERE startDate BETWEEN '2023-01-01' AND '2023-12-31'";
            PreparedStatement prepare = connection.prepareStatement(query);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                System.out.print("first name : ");
                System.out.print(result.getString("firstName"));
                System.out.print(" , last name : ");
                System.out.print(result.getString("lastName"));
                System.out.print(" , joining date : ");
                System.out.print(result.getDate("startDate"));
                System.out.println();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void main(String[] args) {
        dateInRange();
    }
}