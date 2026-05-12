package Vue;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Accueil {
    private JPanel mainPanel;
    private JButton button1;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addGestionStockListener(ActionListener listener) {
        button1.addActionListener(listener);
    }
}
