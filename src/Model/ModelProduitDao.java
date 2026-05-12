package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModelProduitDao {
    Connection con;
    Connexion cnx = new Connexion();
    PreparedStatement pst;
    ResultSet rs;

    public boolean Enregistrer(ModelProduit mp) {
        String sql = "insert into produits(categorie_id, nom, description, prix, stock, image_url)" +
                "values(?,?,?,?,?,?)";
        try {
            con  = cnx.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, mp.getCategorieId());
            pst.setString(2, mp.getNom());
            pst.setString(3, mp.getDescription());
            pst.setDouble(4, mp.getPrix());
            pst.setInt(5, mp.getStock());
            pst.setString(6, mp.getImage());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean Modifier(ModelProduit mp) {
        String sql = "update produits set categorie_id=?, nom=?, description=?, prix=?, stock=?, image_url=? where id=?";
        try {
            con = cnx.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, mp.getCategorieId());
            pst.setString(2, mp.getNom());
            pst.setString(3, mp.getDescription());
            pst.setDouble(4, mp.getPrix());
            pst.setInt(5, mp.getStock());
            pst.setString(6, mp.getImage());
            pst.setInt(7, mp.getId());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try { con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public boolean Supprimer(int id) {
        String sql = "delete from produits where id=?";
        try {
            con = cnx.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try { con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public List<ModelProduit> Liste(){
        List<ModelProduit> modelListe = new ArrayList<>();

        String sql = "select p.id, p.categorie_id, p.nom, p.description, p.prix, p.stock, p.image_url, " +
                "c.nom as categorie_nom " +
                "from produits p " +
                "left join categories c on c.id = p.categorie_id";
        try {
            con  = cnx.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                ModelProduit mp = new ModelProduit();
                mp.setId(rs.getInt("id"));
                mp.setCategorieId(rs.getInt("categorie_id"));
                mp.setCategorieNom(rs.getString("categorie_nom"));
                mp.setNom(rs.getString("nom"));
                mp.setDescription(rs.getString("description"));
                mp.setPrix(rs.getDouble("prix"));
                mp.setStock(rs.getInt("stock"));
                mp.setImage(rs.getString("image_url"));
                modelListe.add(mp);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return modelListe;
    }
}
