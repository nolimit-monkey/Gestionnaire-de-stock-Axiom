package Controller;

import Model.ModelCategorieDao;
import Model.ModelGithubDao;
import Model.ModelProduit;
import Model.ModelProduitDao;
import Vue.Produit;

public class ControllerProduit {
    private final Produit vue;
    private final ModelProduitDao produitDao;
    private final ModelCategorieDao categorieDao;
    private final ModelGithubDao githubDao;

    public ControllerProduit(Produit vue) {
        this.vue = vue;
        this.produitDao = new ModelProduitDao();
        this.categorieDao = new ModelCategorieDao();
        this.githubDao = new ModelGithubDao();

        bindEvents();
        chargerDonneesInitiales();
    }

    private void bindEvents() {
        vue.addEnregistrerListener(event -> enregistrerProduit());
        vue.addSupprimerListener(event -> supprimerProduit());
        vue.addViderListener(event -> vue.viderFormulaire());
        vue.addProduitSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                afficherProduitSelectionne();
            }
        });
    }

    private void chargerDonneesInitiales() {
        vue.setCategories(categorieDao.findAll());
        // Chargement des images disponibles depuis le dépôt GitHub
        vue.setImageUrls(githubDao.getImageUrls());
        rafraichirProduits();
    }

    private void rafraichirProduits() {
        vue.setProduits(produitDao.Liste());
    }

    private void afficherProduitSelectionne() {
        ModelProduit produitSelectionne = vue.getSelectedProduit();
        if (produitSelectionne != null) {
            vue.afficherProduit(produitSelectionne);
        }
    }

    private void enregistrerProduit() {
        if (vue.getSelectedCategorie() == null) {
            vue.afficherErreur("Selectionnez une categorie.");
            return;
        }

        try {
            ModelProduit produit = vue.getProduitFromForm();
            boolean succes = (produit.getId() == null) ? produitDao.Enregistrer(produit) : produitDao.Modifier(produit);

            if (succes) {
                rafraichirProduits();
                vue.viderFormulaire();
                vue.afficherMessage(produit.getId() == null ? "Produit ajoute." : "Produit modifie.");
            } else {
                vue.afficherErreur("Echec de l'enregistrement du produit.");
            }
        } catch (NumberFormatException e) {
            vue.afficherErreur("Prix et stock doivent etre numeriques.");
        }
    }

    private void supprimerProduit() {
        ModelProduit produit = vue.getSelectedProduit();
        if (produit == null) {
            vue.afficherErreur("Selectionnez un produit dans la liste.");
            return;
        }

        if (produitDao.Supprimer(produit.getId())) {
            rafraichirProduits();
            vue.viderFormulaire();
            vue.afficherMessage("Produit supprime.");
        } else {
            vue.afficherErreur("Echec de la suppression du produit.");
        }
    }
}
