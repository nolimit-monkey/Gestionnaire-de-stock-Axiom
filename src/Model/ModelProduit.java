package Model;

public class ModelProduit {
    private String nom;
    private Integer id;
    private String description;
    private Double prix;
    private Integer categorieId;
    private String categorieNom;
    private Integer stock;
    private String image;

    public ModelProduit() {

    }
    public ModelProduit(String nom, Integer id, String description, Double prix, Integer categorieId,
                        String categorieNom, Integer stock, String image) {
        this.nom = nom;
        this.id = id;
        this.description = description;
        this.prix = prix;
        this.categorieId = categorieId;
        this.categorieNom = categorieNom;
        this.stock = stock;
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
