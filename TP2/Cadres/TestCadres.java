package Cadres;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TestCadres extends JFrame {

    public TestCadres() {
        setTitle("Test des Bordures JLabel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10)); // 5 lignes, 1 colonne

        // 1. Cadre simple (ligne noire, 1 pixel)
        JLabel label1 = createLabel("Cadre simple (1px noir)");
        label1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // 2. Cadre Marie-Louise (BevelBorder)
        JLabel label2 = createLabel("Cadre Marie-Louise (Effet relief)");
        label2.setBorder(BorderFactory.createLoweredBevelBorder());

        // 3. Cadre rouge (2px) avec titre bleu
        JLabel label3 = createLabel("Cadre avec titre");
        Border ligneRouge = BorderFactory.createLineBorder(Color.RED, 2);
        label3.setBorder(BorderFactory.createTitledBorder(ligneRouge, "Titre Bleu", 0, 0, null, Color.BLUE));

        // 4. Cadre simple décollé du JLabel de 5 pixels (CompoundBorder)
        JLabel label4 = createLabel("Cadre décollé (Margin)");
        Border cadreNoir = BorderFactory.createLineBorder(Color.BLACK, 1);
        Border marge = BorderFactory.createEmptyBorder(5, 5, 5, 5); // Haut, Gauche, Bas, Droite
        label4.setBorder(BorderFactory.createCompoundBorder(cadreNoir, marge));

        // 5. Cadre double (Bleu et Rouge) séparé de 2 pixels
        JLabel label5 = createLabel("Cadre double (Bleu/Rouge)");
        Border bleu = BorderFactory.createLineBorder(Color.BLUE, 1);
        Border rouge = BorderFactory.createLineBorder(Color.RED, 1);
        Border espace = BorderFactory.createEmptyBorder(2, 2, 2, 2);
        Border comboInterieur = BorderFactory.createCompoundBorder(espace, rouge);
        label5.setBorder(BorderFactory.createCompoundBorder(bleu, comboInterieur));

        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);

        pack();
        setLocationRelativeTo(null);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(300, 50));
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestCadres().setVisible(true));
    }
}