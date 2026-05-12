package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {

    private Connection con;

    public Connection getConnection() {
        try {
            // Driver moderne
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connexion
            String mdp = System.getenv("DB_PASSWORD_AXIOM");

            con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/axiom?useSSL=false&serverTimezone=UTC",
                    "devuser",
                    mdp
            );

            System.out.println("Connexion réussie");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}