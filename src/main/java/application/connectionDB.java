package application;

import common.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.Scanner;

public class connectionDB {
    private static String url;
    private static String username;
    private static String password;

    //public static void main(String[] args) throws Exception {
       // executeDBQuery("SELECT * FROM \"UTILISATEUR\"");
    //}
    public static String[] getDBProperties(){

        try {
            Properties prop = new Properties();
            FileInputStream input = null;
            input = new FileInputStream("src/main/resources/application/config.properties");
            prop.load(input);
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            String[] properties = new String[3];

            properties[0] = url;
            properties[1] = username;
            properties[2] = password;

            return properties;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Player getPlayerByUsername(String _username) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        String[] properties = getDBProperties();

        String queryDb = "SELECT * FROM \"UTILISATEUR\" WHERE USERNAME = '"+_username + "'";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(properties[0], properties[1], properties[2]);
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

        String[] properties = getDBProperties();

        //password à modifier pour hashage
        String queryDb = "INSERT INTO UTILISATEUR (username, password, perso) VALUES ('"+ player.getUsername()+ "', '"+ player.getPassword() +"', "+ player.getPerso() +")";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(properties[0], properties[1], properties[2]);
            statement = conn.prepareStatement(queryDb);
            result = statement.executeQuery();

            if(result != null){
                return true;
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

        String[] properties = getDBProperties();

        String queryDb = "UPDATE UTILISATEUR SET perso = " + persoId + " WHERE id = "+ player.getId();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(properties[0], properties[1], properties[2]);
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
