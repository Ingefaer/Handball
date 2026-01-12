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

    private ArrayList<Team> getTeamByWhereClause(String whereClause) {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            String sql = "SELECT * FROM team WHERE " + whereClause;

            System.out.println(sql);
            //TODO: skal det være et prepared statement?
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

    public ArrayList<Team> getTeamByID(int teamID) {
       return getTeamByWhereClause("id=" + teamID);
    }

    public ArrayList<Team> getAllTeams() {
    return getTeamByWhereClause("0=0");
  }

  /*
   * Update operationer
   */

  public boolean updateTeam(Team team) {
      try {
          String sql = "UPDATE team SET team = ? WHERE id = ? ";

          System.out.println(sql);

          //Get statement object
          //Statement statement = connection.createStatement();
          PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

          statement.setString(1, team.getTeamName());
          statement.setInt(2, team.getTeamID());


          // execute sql statement
          int affectedRows = statement.executeUpdate();

          if (affectedRows != 1)
              return false;

          return true;
      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }
  }

    /*
     * Delete operationer
     */
    public boolean deleteTeam(Team team) {
        try {
            String sql = "DELETE FROM team WHERE id=" + team.getTeamID();

            // get statement object
            Statement statement = connection.createStatement();

            // execute sql statement
            int affectedRows = statement.executeUpdate(sql);

            if (affectedRows != 1)
                return false;

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}