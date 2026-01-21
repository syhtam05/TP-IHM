package II;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ApplicationTextSync {

    // Fenêtre principale
    static class FenetrePrincipale extends JFrame {
        JLabel labelStatique; // Le champ (4)

        public FenetrePrincipale() {
            setTitle("Fenêtre d'affichage");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            labelStatique = new JLabel("Texte initial", SwingConstants.CENTER);
            labelStatique.setFont(new Font("Arial", Font.BOLD, 24));
            labelStatique.setOpaque(true); // Nécessaire pour voir la couleur de fond
            add(labelStatique, BorderLayout.CENTER);

            JButton btnQuitter = new JButton("Quitter");
            btnQuitter.addActionListener(e -> System.exit(0));
            add(btnQuitter, BorderLayout.SOUTH);

            setLocation(100, 100);
            setVisible(true);
        }

        // Méthodes pour mettre à jour l'affichage depuis la boîte de dialogue
        public void mettreAJourTexte(String texte) { labelStatique.setText(texte); }
        public void mettreAJourCouleur(Color c) { labelStatique.setForeground(c); }
        public void mettreAJourFond(boolean estJaune) {
            labelStatique.setBackground(estJaune ? Color.YELLOW : null);
        }
    }

    // Boîte de dialogue non modale
    static class ControleDialogue extends JDialog {
        public ControleDialogue(FenetrePrincipale parent) {
            super(parent, "Contrôles", false); // false = non modale
            setSize(300, 250);
            setLayout(new GridLayout(5, 1));

            // 1. Champ de saisie
            JTextField champSaisie = new JTextField();
            add(new JLabel("Saisissez votre texte :"));
            add(champSaisie);

            // 2. Boutons radio pour la couleur
            JPanel panRadios = new JPanel();
            JRadioButton rbRouge = new JRadioButton("Rouge");
            JRadioButton rbBleu = new JRadioButton("Bleu");
            JRadioButton rbNoir = new JRadioButton("Noir", true);
            ButtonGroup groupe = new ButtonGroup();
            groupe.add(rbRouge); groupe.add(rbBleu); groupe.add(rbNoir);
            panRadios.add(rbRouge); panRadios.add(rbBleu); panRadios.add(rbNoir);
            add(panRadios);

            // 3. Case à cocher pour le fond jaune
            JCheckBox cbJaune = new JCheckBox("Fond jaune");
            add(cbJaune);

            // --- LOGIQUE DE SYNCHRONISATION ---

            // Écouteur pour le texte (1) -> (4)
            champSaisie.getDocument().addDocumentListener(new DocumentListener() {
                public void update() { parent.mettreAJourTexte(champSaisie.getText()); }
                public void insertUpdate(DocumentEvent e) { update(); }
                public void removeUpdate(DocumentEvent e) { update(); }
                public void changedUpdate(DocumentEvent e) { update(); }
            });

            // Écouteur pour les couleurs (2) -> (4)
            rbRouge.addActionListener(e -> parent.mettreAJourCouleur(Color.RED));
            rbBleu.addActionListener(e -> parent.mettreAJourCouleur(Color.BLUE));
            rbNoir.addActionListener(e -> parent.mettreAJourCouleur(Color.BLACK));

            // Écouteur pour le fond (3) -> (4)
            cbJaune.addActionListener(e -> parent.mettreAJourFond(cbJaune.isSelected()));

            setLocation(550, 100);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FenetrePrincipale fp = new FenetrePrincipale();
            new ControleDialogue(fp);
        });
    }
}