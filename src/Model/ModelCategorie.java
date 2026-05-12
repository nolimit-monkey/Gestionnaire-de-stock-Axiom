package Model;

public class ModelCategorie {
    private Integer id;
    private String nom;

    public ModelCategorie() {
    }

    public ModelCategorie(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom == null ? "" : nom;
    }
}
