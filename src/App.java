import javax.swing.*;

import Controller.ControllerAccueil;
import Vue.Accueil;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Accueil");
            Accueil vue = new Accueil();
            new ControllerAccueil(vue, frame);
            frame.setContentPane(vue.getMainPanel());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
