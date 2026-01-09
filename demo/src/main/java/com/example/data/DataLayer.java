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
                        "instanceName=SQLEXPRESS;" +
                        "databaseName=" + databaseName + ";" +
                        "integratedSecurity=true;" +
                        "trustServerCertificate=true";

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
     * Create operationer
     */
    public boolean insertTeam(Team team) {
        try {
            String sql = "INSERT INTO team VALUES (?,?)";
                    /*'"
                    + student.getLastName() + "', '"
                    + student.getFirstName() + "', '"
                    + student.getSemesterNo()+"')";*/

            System.out.println(sql);

            // get statement object
            //Statement statement = connection.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, team.getTeamName());
            statement.setInt(2, team.getTeamPoint());


            // execute sql statement
            int affectedRows = statement.executeUpdate();

            if (affectedRows != 1)
                return false;

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                team.setTeamID(resultSet.getInt(1));
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    } //Testet og virker

        public boolean insertMatch(Match match) {
            try {
                String sql = "INSERT INTO match VALUES (?,?)";
                    /*'"
                    + student.getLastName() + "', '"
                    + student.getFirstName() + "', '"
                    + student.getSemesterNo()+"')";*/

                System.out.println(sql);

                // get statement object
                //Statement statement = connection.createStatement();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setInt(1, match.getTeam1ID());
                statement.setInt(2, match.getTeam2ID());


                // execute sql statement
                int affectedRows = statement.executeUpdate();

                if (affectedRows != 1)
                    return false;

                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    match.setMatchID(resultSet.getInt(1));
                }

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

    /*
   * Read operationer
   */

    public ArrayList<Team> getTeamByWhereClause(String whereClause) {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            String sql = "SELECT * FROM team WHERE " + whereClause;

            System.out.println(sql);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            // iteration starter 'before first'
            while (resultSet.next()) {
                // hent data fra denne række
                int id = resultSet.getInt("id");
                String teamName = resultSet.getString("team");
                int point = resultSet.getInt("point");

                Team team = new Team(id, teamName, point);

                teams.add(team);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    //********************************* dont need **********************************
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
   * Update operationer
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

    /*
     * Delete operationer
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
}