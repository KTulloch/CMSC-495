/*
UMUC CMSC 495 6380
Forget Me Not
Group 8
Members: Robert Hunter Robinson
Jered Russell
Keith Tulloch

Due Date: Sunday October 7th, 2018
Revision History:
October 7th - Hunter - Initial Creation
 */
package forgetmenot;

import java.util.Arrays;
import java.util.Date;

public class ForgetDB {
    
    // These are the strings to identify the database to use and the login info
    private static final String DBURL = "jdbc:derby://localhost:1527/ForgetMeNot";
    private static final String DBUSER = "ForgetMeUser";
    private static final String DBPASS = "password";
    
    // The Connect Object manages interactions between
    // The Java program and the database.
    private static java.sql.Connection conn = null;
    
    /*
        Here we initialize the Connection Object   
    */
    private static void connectToDatabase(){
        try {
            conn = java.sql.DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            System.out.println("Connection Object Created");
        } catch (java.sql.SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*
        Close all connections to avoid memory leaks
    */
    private static void disconnectFromDatabase(){
        try {
            if (conn != null){
                conn.close();
            }
            
            if (rs != null){
                rs.close();
            }
            
            if (stmnt != null){
                stmnt.close();
            }
        } catch (java.sql.SQLException ex) {
            System.out.println(ex.getMessage());
        }        
    }
    
    /*
        Main Method for testing DB connection
    */
    public static void main(String... args){
        connectToDatabase();
        disconnectFromDatabase();
    }
    
    // The Statement Object allows us to make SQL queries
    // We initialize the statement using a static method of the
    // Connection object.  Each Statement object is tailored to
    // work with the correct database
    private static java.sql.Statement stmnt;
    
    // We will need a ResultSet Object to hand database responses
    // ResultSets returned from Statements
    private static java.sql.ResultSet rs = null;
    
    /*
        Insert new rows
    */
    private static void addRowToDatabase(String fName, String occasion, Date eventDate, Date reminderDate, String[] wishlist){
        try {            
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("INSERT INTO FORGETNOT (fName, occasion, event_date, reminder_date, item1, item2, item3, item4) " +
	 "VALUES ("+fName+", "+occasion+", TO_DATE("+eventDate+", ‘MM/DD/YYYY’), TO_DATE("+reminderDate+",’MM/DD/YYYY’), "+Arrays.toString(wishlist)+")");
            rs.close();
            stmnt.close();
        } catch (java.sql.SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /* 
        Search for an event
    */
    private static void searchDatabase(String searchField) {
        try {
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("Select * FROM FORGETNOT f WHERE f."+searchField+"=x."+searchField);
            while (rs.next()) {
                // insert code here to feed search back to UI
            }
            rs.close();
            stmnt.close();
        } catch (java.sql.SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*
        Date check
    */
    private static void dateCheck() {
        try {
            stmnt = conn.createStatement();
            rs = stmnt.executeQuery("SELECT * FROM FORGETNOT WHERE DATEDIFF(day, reminder_date,	GETDATE()) = 0");
            while (rs.next()) {
                // insert code here to feed reminder check hit back to UI
            }
            rs.close();
            stmnt.close();
        } catch (java.sql.SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }   
}