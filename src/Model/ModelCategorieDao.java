package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModelCategorieDao {
    Connection con;
    Connexion cnx = new Connexion();
    PreparedStatement pst;
    ResultSet rs;

    public List<ModelCategorie> findAll() {
        List<ModelCategorie> categories = new ArrayList<>();
        String sql = "select id, nom from categories order by nom";

        try {
            con = cnx.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                ModelCategorie categorie = new ModelCategorie();
                categorie.setId(rs.getInt("id"));
                categorie.setNom(rs.getString("nom"));
                categories.add(categorie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return categories;
    }
}
