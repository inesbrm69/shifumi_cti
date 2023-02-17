package application;

import common.Player;

import java.sql.*;
import java.text.MessageFormat;
import java.util.Scanner;

public class connectionDB {

    private static String url = "jdbc:oracle:thin:@iutdoua-ora.univ-lyon1.fr:1521:cdb1";
    private static String username = "p1906861";
    private static String password = "445030";

    //public static void main(String[] args) throws Exception {
       // executeDBQuery("SELECT * FROM \"UTILISATEUR\"");
    //}

    public static Player getPlayerByUsername(String _username) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;


        //String queryDb = MessageFormat.format("SELECT * FROM \"UTILISATEUR\" WHERE USERNAME = '{0}' ", _username);
        String queryDb = "SELECT * FROM \"UTILISATEUR\" WHERE USERNAME = '"+_username + "'";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.prepareStatement(queryDb);
            result = statement.executeQuery();

            if(result != null){
                int id = 0;
                String username = "";
                String password = "";
                int score = 0;
                int perso = 0;

                while (result.next()) {
                    id = result.getInt("id");
                    username = result.getString("username");
                    password = result.getString("password");
                    score = result.getInt("score");
                    perso = result.getInt("perso");
                }
                Player player = new Player(id, username, password, score, perso);
                return player;
            }
            else{
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (result != null) { try { result.close(); } catch (SQLException e) {} result = null; }
            if (statement != null) { try { statement.close(); } catch (SQLException e) {} statement = null; }
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null; }
        }

        return null;
    }

    public static boolean addNewPlayer(Player player) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        //score à supprimer -> when newPlayer, score default 0 in DB
        //password à modifier pour hashage
        String queryDb = "INSERT INTO UTILISATEUR (username, password, score, perso) VALUES ('"+ player.getUsername()+ "', '"+ player.getPassword() +"', 0, "+ player.getPerso() +")";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.prepareStatement(queryDb);
            result = statement.executeQuery();

            // à tester :
            // getStatement()
            // Retrieves the Statement object that produced this ResultSet object.

            if(result != null){
                return true;
                //Player player = new Player(id, username, password, score, perso);
            }
            else{
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (result != null) { try { result.close(); } catch (SQLException e) {} result = null; }
            if (statement != null) { try { statement.close(); } catch (SQLException e) {} statement = null; }
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null; }
        }
        return false;
    }

    public static boolean changePerso(Player player, int persoId){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        String queryDb = "UPDATE UTILISATEUR SET perso = " + persoId + " WHERE id = "+ player.getId();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.prepareStatement(queryDb);
            result = statement.executeQuery();

            if(result.getWarnings() != null ){
                return false;
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (result != null) { try { result.close(); } catch (SQLException e) {} result = null; }
            if (statement != null) { try { statement.close(); } catch (SQLException e) {} statement = null; }
            if (conn != null) { try { conn.close(); } catch (SQLException e) {} conn = null; }
        }
        return false;
    }
}
