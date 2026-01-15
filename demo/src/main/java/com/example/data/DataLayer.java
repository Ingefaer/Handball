package com.example.data;

import com.example.entities.*;
import com.example.entities.Timestamp;


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
        } //Testet og virker!
    public boolean insertGoalOrPenalty(String goalOrPenalty, int matchID, int teamID, ArrayList<Timestamp> goalTeam) {
        for (Timestamp timestamp : goalTeam) {
            try {
                String sql = "INSERT INTO " + goalOrPenalty + " VALUES (?,?,?)";
                //teamID, timestamp, matchID
                System.out.println(sql);

                // get statement object
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setInt(1, teamID);
                statement.setInt(2, timestamp.getTotalSeconds());
                statement.setInt(3, matchID);


                // execute sql statement
                int affectedRows = statement.executeUpdate();

                if (affectedRows != 1)
                    return false;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
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

    public ArrayList<Team> getLeague() {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            String sql = "SELECT team, point FROM team ORDER BY point DESC";

            System.out.println(sql);
            //TODO: skal det være et prepared statement?
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            // iteration starter 'before first'
            while (resultSet.next()) {
                // hent data fra denne række
                String teamName = resultSet.getString("team");
                int point = resultSet.getInt("point");

                Team team = new Team(teamName, point);
                System.out.println(team);
                teams.add(team);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    private ArrayList<Match> getMatchByWhereClause(String whereClause) {
        ArrayList<Match> matches = new ArrayList<>();
        try {
            String sql = "SELECT * FROM match WHERE " + whereClause + "ORDER BY id DESC";

            System.out.println(sql);
            //TODO: skal det være et prepared statement?
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            // iteration starter 'before first'
            while (resultSet.next()) {
                // hent data fra denne række
                int matchID = resultSet.getInt("id");
                int team1ID = resultSet.getInt("teamID1");
                int team2ID = resultSet.getInt("teamID2");

                Team team1 = getTeamByID(team1ID).get(0);
                Team team2 = getTeamByID(team2ID).get(0);
                Match match = new Match(matchID, team1, team2);
                System.out.println(match);
                matches.add(match);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return matches;
    }

    public ArrayList<Match> getAllMatches() {
        return getMatchByWhereClause("0=0");
    }

    public ArrayList<Timestamp> getGoals(int matchID, int teamID) {
        ArrayList<Timestamp> goals = new ArrayList<>();
        try {
            String sql = "SELECT * FROM goal WHERE teamID = " + teamID + " AND matchID = " + matchID + " ORDER BY id ASC";

            System.out.println(sql);
            //TODO: skal det være et prepared statement?
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            // iteration starter 'before first'
            while (resultSet.next()) {
                // hent data fra denne række
                //int matchID = resultSet.getInt("id");
                //int team1ID = resultSet.getInt("teamID1");
                //int team2ID = resultSet.getInt("teamID2");
                int timestamp = resultSet.getInt("timestamp");
                Timestamp goal = new Timestamp(timestamp);
                goals.add(goal);
                System.out.println(goal);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return goals;
    }

    public ArrayList<Timestamp> getPenalties(int matchID, int teamID) {
        ArrayList<Timestamp> penalties = new ArrayList<>();
        try {
            String sql = "SELECT * FROM penalty WHERE teamID = " + teamID + " AND matchID = " + matchID + " ORDER BY id ASC";

            System.out.println(sql);
            //TODO: skal det være et prepared statement?
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            // iteration starter 'before first'
            while (resultSet.next()) {
                // hent data fra denne række
                //int matchID = resultSet.getInt("id");
                //int team1ID = resultSet.getInt("teamID1");
                //int team2ID = resultSet.getInt("teamID2");
                int timestamp = resultSet.getInt("timestamp");
                Timestamp penalty = new Timestamp(timestamp);
                penalties.add(penalty);
                System.out.println(penalty);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return penalties;
    }
  /*
   * Update operationer
   */

  public boolean updateTeam(Team team) {
      try {
          String sql = "UPDATE team SET team = ?, point = ? WHERE id = ? ";

          System.out.println(sql);

          //Get statement object
          //Statement statement = connection.createStatement();
          PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

          statement.setString(1, team.getTeamName());
          statement.setInt(2, team.getTeamPoint());
          statement.setInt(3, team.getTeamID());


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