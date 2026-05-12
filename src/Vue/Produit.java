package Vue;

import Model.ModelCategorie;
import Model.ModelProduit;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Produit {
    private static final String[] TABLE_COLUMNS = {
            "Id", "Nom", "Categorie", "Description", "Prix", "Stock", "Url image"
    };

    private JPanel mainPanel;
    private JTable table;
    private JLabel lbNomProduit;
    private JTextField tfNomProduit;
    private JLabel lbCategorie;
    private JTextField tfCategorie;
    private JLabel lbDescription;
    private JTextField tfDescription;
    private JLabel lbPrix;
    private JTextField tfPrix;
    private JTextField tfStock;
    private JLabel lbStock;
    private JLabel lbUrlImage;
    private JButton btenregistrer;
    private JComboBox<ModelCategorie> cbCategorie;
    private JButton btsupprimer;
    private JButton btVider;
    private JComboBox cbImageUrl;

    private DefaultTableModel tableModelProduit;
    private List<ModelProduit> produitsAffiches = new ArrayList<>();

    public Produit() {
        initialiserTable();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addEnregistrerListener(ActionListener listener) {
        btenregistrer.addActionListener(listener);
    }

    public void addSupprimerListener(ActionListener listener) {
        btsupprimer.addActionListener(listener);
    }

    public void addViderListener(ActionListener listener) {
        btVider.addActionListener(listener);
    }

    public void addProduitSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    public void setCategories(List<ModelCategorie> categories) {
        DefaultComboBoxModel<ModelCategorie> comboBoxModel = new DefaultComboBoxModel<>();
        for (ModelCategorie categorie : categories) {
            comboBoxModel.addElement(categorie);
        }
        cbCategorie.setModel(comboBoxModel);
        cbCategorie.setSelectedIndex(-1);
    }

    // Peuple cbImageUrl avec les URLs récupérées depuis GitHub
    public void setImageUrls(List<String> urls) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String url : urls) {
            model.addElement(url);
        }
        cbImageUrl.setModel(model);
        cbImageUrl.setSelectedIndex(-1);
    }

    public void setProduits(List<ModelProduit> produits) {
        produitsAffiches = new ArrayList<>(produits);
        tableModelProduit.setRowCount(0);

        for (ModelProduit produit : produitsAffiches) {
            tableModelProduit.addRow(new Object[]{
                    produit.getId(),
                    produit.getNom(),
                    produit.getCategorieNom(),
                    produit.getDescription(),
                    produit.getPrix(),
                    produit.getStock(),
                    produit.getImage()
            });
        }
    }

    public ModelCategorie getSelectedCategorie() {
        return (ModelCategorie) cbCategorie.getSelectedItem();
    }

    public ModelProduit getProduitFromForm() {
        ModelCategorie categorieSelectionnee = getSelectedCategorie();

        ModelProduit produit = new ModelProduit();
        ModelProduit selectionne = getSelectedProduit();
        if (selectionne != null) produit.setId(selectionne.getId());
        produit.setNom(tfNomProduit.getText().trim());
        produit.setDescription(tfDescription.getText().trim());
        produit.setPrix(Double.parseDouble(tfPrix.getText().trim()));
        produit.setStock(Integer.parseInt(tfStock.getText().trim()));
        Object imageSelection = cbImageUrl.getSelectedItem();
        produit.setImage(imageSelection != null ? imageSelection.toString().trim() : "");

        if (categorieSelectionnee != null) {
            produit.setCategorieId(categorieSelectionnee.getId());
            produit.setCategorieNom(categorieSelectionnee.getNom());
        }

        return produit;
    }

    public ModelProduit getSelectedProduit() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0 || selectedRow >= produitsAffiches.size()) {
            return null;
        }

        return produitsAffiches.get(selectedRow);
    }

    public void afficherProduit(ModelProduit produit) {
        if (produit == null) {
            return;
        }

        tfNomProduit.setText(produit.getNom());
        tfDescription.setText(produit.getDescription());
        tfPrix.setText(String.valueOf(produit.getPrix()));
        tfStock.setText(String.valueOf(produit.getStock()));
        // Normaliser en nom de fichier au cas où la BD stocke une URL complète
        String image = produit.getImage();
        if (image != null && image.contains("/")) {
            image = image.substring(image.lastIndexOf('/') + 1);
        }
        cbImageUrl.setSelectedItem(image);
        setSelectedCategorieById(produit.getCategorieId());
    }

    public void viderFormulaire() {
        tfNomProduit.setText("");
        tfDescription.setText("");
        tfPrix.setText("");
        tfStock.setText("");
        cbImageUrl.setSelectedIndex(-1);
        cbCategorie.setSelectedIndex(-1);
        table.clearSelection();
    }

    public void afficherErreur(String message) {
        JOptionPane.showMessageDialog(mainPanel, message);
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(mainPanel, message);
    }

    private void initialiserTable() {
        tableModelProduit = new DefaultTableModel(TABLE_COLUMNS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(tableModelProduit);
    }

    private void setSelectedCategorieById(Integer categorieId) {
        ComboBoxModel<ModelCategorie> model = cbCategorie.getModel();

        for (int i = 0; i < model.getSize(); i++) {
            ModelCategorie categorie = model.getElementAt(i);
            if (categorieId != null && categorieId.equals(categorie.getId())) {
                cbCategorie.setSelectedIndex(i);
                return;
            }
        }

        cbCategorie.setSelectedIndex(-1);
    }
}
