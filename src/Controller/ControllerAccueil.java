package Controller;

import Vue.Accueil;
import Vue.Produit;

import javax.swing.*;

public class ControllerAccueil {
    private final Accueil vue;
    private final JFrame frame;

    public ControllerAccueil(Accueil vue, JFrame frame) {
        this.vue = vue;
        this.frame = frame;
        bindEvents();
    }

    private void bindEvents() {
        vue.addGestionStockListener(e -> ouvrirGestionStock());
    }

    private void ouvrirGestionStock() {
        Produit vueProduit = new Produit();
        new ControllerProduit(vueProduit);
        frame.setContentPane(vueProduit.getMainPanel());
        frame.setSize(1061, 903);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }
}
