package com.example.data;

import com.example.entities.*;

import java.sql.*;
import java.util.ArrayList;

public class DataLayer {
    
    //instanvariable
    private Connection connection;

    // konstruktør
    public DataLayer() {
        loadJdbcDriver();
        openConnection("HandballDB"); /*her udfyldes navnet på den specifikke database */
    }

    //metode til at forbinde til DB - behøver kun benyttes første gang
    private boolean loadJdbcDriver() {
    try {
      System.out.println("Loading JDBC driver...");

      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

      System.out.println("JDBC driver loaded");

      return true;
    }
    catch (ClassNotFoundException e) {
      System.out.println("Could not load JDBC driver!");
      return false;
    }
  }

    private boolean openConnection(String databaseName) {
    String connectionString =
        "jdbc:sqlserver://localhost:1433;" +
        "databaseName=" + databaseName + ";" +
        "user=;" +  // Default SQL Server Docker username
        "password=;" +  // Your Docker SQL Server password
        "trustServerCertificate=true;";

    System.out.println(connectionString);
    
    // Rest of your code remains the same...

    connection = null;

    try {
      System.out.println("Connecting to database...");

      connection = DriverManager.getConnection(connectionString);

      System.out.println("Connected to database");

      return true;
    }
    catch (SQLException e) {
      System.out.println("Could not connect to database!");
      System.out.println(e.getMessage());

      return false;
    }
  }

    /*
   * Read operationer
   * To-do tilføj read af drikkevarer og Salg og brand
   */
  public ArrayList<Customer> getAllCustomers() {
    return getCustomersByWhereClause("0=0");
  }

  public ArrayList<Customer> getCustomersByFirstName(String firstName) {
    return getCustomersByWhereClause("first_name='" + firstName + "'");
  }

  public ArrayList<Customer> getCustomersByLastName(String lastName) {
    return getCustomersByWhereClause("last_name='" + lastName + "'");
  }

  public ArrayList<Customer> getCustomersByNickName(String nickName) {
    return getCustomersByWhereClause("nickname='" + nickName + "'");
  }

  private ArrayList<Customer> getCustomersByWhereClause(String whereClause) {
    ArrayList<Customer> customers = new ArrayList<Customer>();

    try {
      String sql = "SELECT * FROM customer WHERE " + whereClause;

      System.out.println(sql);

      Statement statement = connection.createStatement();

      ResultSet resultSet = statement.executeQuery(sql);

      // iteration starter 'before first'
      while (resultSet.next()) {
        // hent data fra denne række
        int id = resultSet.getInt("id");
        String lastName = resultSet.getString("last_name");
        String firstName = resultSet.getString("first_name");
        String nickName = resultSet.getString("nickname");

        Customer customer = new Customer(id, lastName, firstName, nickName);

        customers.add(customer);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return customers;
  }

  /*
   * Create operationer
   * To-do tilføj read af drikkevarer og Salg og brand
   */
  public void insertCustomer(Customer customer) {
    try {
      String sql = "INSERT INTO customer VALUES ('" +
        customer.getLastName() + "', '" +
        customer.getFirstName() + "', " +
        customer.getNickName() + ")";

      System.out.println(sql);

      // get statement object
      Statement statement = connection.createStatement();

      // execute sql statement
      int affectedRows = statement.executeUpdate(sql);

      // ToDo: check at affectedRows er 1

      /*
       * get (possible) auto-generated key if INSERT-statement
       */
      ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");

      if (resultSet.next())
        customer.setId(resultSet.getInt(1));
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /*public void insertBeverage(Beverage beverage) {
    try {
      String sql = "INSERT INTO beverage (brandID, name, deposit) VALUES ('" +
        beverage.getBrandID() + "', '" +
        beverage.getName() + "', " +
        beverage.getDeposit() + ")";

      System.out.println(sql);

      // get statement object
      Statement statement = connection.createStatement();

      // execute sql statement
      int affectedRows = statement.executeUpdate(sql);

      // ToDo: check at affectedRows er 1

      //get (possible) auto-generated key if INSERT-statement - tilfæjer id til java objekt
      ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");

      if (resultSet.next())
        beverage.setId(resultSet.getInt(1));
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }*/

  /*
   * Delete operationer
   * To-do tilføj read af drikkevarer og Salg og brand
   */
  public void deleteCustomer(Customer customer) {
    try {
      String sql = "DELETE FROM customer WHERE id=" + customer.getId();

      System.out.println(sql);

      // get statement object
      Statement statement = connection.createStatement();

      // execute sql statement
      int affectedRows = statement.executeUpdate(sql);

      // ToDo: check at affectedRows er 1
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /*
   * Update operationer
   * To-do tilføj read af drikkevarer og Salg og brand
   */
  public void updateCustomer(Customer customer) {
    try {
      String sql = "UPDATE customer SET lastname='" +
        customer.getLastName() + "', firstname='" +
        customer.getFirstName() + "', nickname=" +
        customer.getNickName() +
        " WHERE id=" + customer.getId();

      System.out.println(sql);

      // get statement object
      Statement statement = connection.createStatement();

      // execute sql statement
      int affectedRows = statement.executeUpdate(sql);

      // ToDo: check at affectedRows er 1
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }
}