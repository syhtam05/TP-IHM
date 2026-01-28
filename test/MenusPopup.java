import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenusPopup extends JFrame {
    private JPopupMenu popup;
    private JLabel etiquette;

    public MenusPopup() {
        super("Exemple pop-up");

        // Configuration du label
        etiquette = new JLabel("Faites un clic droit sur moi !", SwingConstants.CENTER);
        etiquette.setPreferredSize(new Dimension(300, 200));
        
        // Création du menu contextuel
        popup = new JPopupMenu();

        JMenuItem quitter = new JMenuItem("Quitter");
        quitter.addActionListener(e -> System.exit(0));

        JMenuItem opaque = new JMenuItem("Opaque (Jaune)");
        opaque.addActionListener(e -> {
            etiquette.setBackground(Color.YELLOW);
            etiquette.setOpaque(true);
            etiquette.repaint(); // Force le rafraîchissement visuel
        });

        JMenuItem trans = new JMenuItem("Transparent");
        trans.addActionListener(e -> {
            etiquette.setOpaque(false);
            etiquette.repaint();
        });

        popup.add(opaque);
        popup.add(trans);
        popup.addSeparator(); // Petite ligne de séparation esthétique
        popup.add(quitter);

        // Liaison du menu au composant
        etiquette.addMouseListener(new EcouteurPopUp());

        add(etiquette);
        pack();
        setLocationRelativeTo(null); // Centre la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    class EcouteurPopUp extends MouseAdapter {
        private void gerePopup(MouseEvent me) {
            // isPopupTrigger() vérifie si le clic correspond à la plateforme (Droit sur Windows, etc.)
            if (me.isPopupTrigger()) {
                // Utiliser me.getX() et me.getY() pour que le menu apparaisse sous la souris
                popup.show(me.getComponent(), me.getX(), me.getY());
            }
        }

        public void mousePressed(MouseEvent me) { gerePopup(me); }
        public void mouseReleased(MouseEvent me) { gerePopup(me); }
    }

    public static void main(String[] args) {
        // Lancer l'interface dans le thread de gestion des événements (EDT)
        SwingUtilities.invokeLater(() -> new MenusPopup());
    }
}