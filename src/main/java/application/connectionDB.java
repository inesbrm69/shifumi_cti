package application;

import common.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


        String queryDb = MessageFormat.format("SELECT * FROM \"UTILISATEUR\" WHERE USERNAME = \"{0}\" ", _username);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.prepareStatement(queryDb);
            result = statement.executeQuery();

            if(result != null){
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                int score = result.getInt("score");
                int perso = result.getInt("perso");

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
        String queryDb = MessageFormat.format("INSERT INTO UTILISATEUR (username, password, score, perso) VALUES (''{0}'', ''{1}'', 0, {2})", player.getUsername(), player.getPassword(), player.getPerso());

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
}
