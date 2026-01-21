package I;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelecteurCouleur extends JFrame {

    private JLabel lblAncien, lblActuel;
    private JRadioButton rbBleu, rbRouge, rbNoir;
    private ButtonGroup groupeCouleurs;
    private Color couleurActuelle = Color.BLACK;

    public SelecteurCouleur() {
        // Configuration de la fenêtre
        setTitle("Sélecteur de Couleurs");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- PARTIE HAUTE : Les Textes ---
        JPanel panHaut = new JPanel();
        lblAncien = new JLabel("ancien");
        lblActuel = new JLabel("actuel");
        // Style des polices pour plus de visibilité
        lblAncien.setFont(new Font("Arial", Font.BOLD, 20));
        lblActuel.setFont(new Font("Arial", Font.BOLD, 20));
        panHaut.add(lblAncien);
        panHaut.add(Box.createHorizontalStrut(50)); // Espace entre les labels
        panHaut.add(lblActuel);
        add(panHaut, BorderLayout.NORTH);

        // --- PARTIE MILIEU : Boutons Radio ---
        JPanel panMilieu = new JPanel();
        rbBleu = new JRadioButton("bleu");
        rbRouge = new JRadioButton("rouge");
        rbNoir = new JRadioButton("noir");

        groupeCouleurs = new ButtonGroup();
        groupeCouleurs.add(rbBleu);
        groupeCouleurs.add(rbRouge);
        groupeCouleurs.add(rbNoir);

        panMilieu.add(rbBleu);
        panMilieu.add(rbRouge);
        panMilieu.add(rbNoir);
        add(panMilieu, BorderLayout.CENTER);

        // --- PARTIE BASSE : Actions ---
        JPanel panBas = new JPanel();
        JButton btnRaz = new JButton("RAZ");
        JButton btnQuitter = new JButton("Quitter");
        panBas.add(btnRaz);
        panBas.add(btnQuitter);
        add(panBas, BorderLayout.SOUTH);

        // --- LOGIQUE DES ÉVÉNEMENTS ---

        // Gestionnaire commun pour les boutons radio
        ActionListener ecouteurCouleur = e -> {
            Color nouvelleCouleur = Color.BLACK;
            if (rbBleu.isSelected()) nouvelleCouleur = Color.BLUE;
            else if (rbRouge.isSelected()) nouvelleCouleur = Color.RED;
            else if (rbNoir.isSelected()) nouvelleCouleur = Color.BLACK;

            // Mise à jour des labels
            lblAncien.setForeground(couleurActuelle);
            lblActuel.setForeground(nouvelleCouleur);
            
            // On mémorise la nouvelle couleur pour le prochain changement
            couleurActuelle = nouvelleCouleur;
        };

        rbBleu.addActionListener(ecouteurCouleur);
        rbRouge.addActionListener(ecouteurCouleur);
        rbNoir.addActionListener(ecouteurCouleur);

        // Bouton RAZ
        btnRaz.addActionListener(e -> {
            groupeCouleurs.clearSelection();
            couleurActuelle = Color.BLACK;
            lblAncien.setForeground(Color.BLACK);
            lblActuel.setForeground(Color.BLACK);
        });

        // Bouton Quitter
        btnQuitter.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        // Lancement de l'interface dans le thread Event Dispatch Thread
        SwingUtilities.invokeLater(SelecteurCouleur::new);
    }
}