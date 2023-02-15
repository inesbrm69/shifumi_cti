package application;

import java.sql.*;

public class premiereConnexion {

    public static void main(String[ ] args){

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Impossible de charger le pilote");
            System.out.println(e);
            System.exit(1);
        }
        System.out.println("Pilote chargé");
    }
}