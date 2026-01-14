package Boutons;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class BoutonsRadio extends JFrame {
	
	public BoutonsRadio() {
		setTitle("Saisie de couleur");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- PARTIE HAUTE (Labels) ---
        JPanel panelHaut = new JPanel(new GridLayout(1, 2));
        JLabel lblGauche = new JLabel("gauche", SwingConstants.LEFT);
        JLabel lblDroite = new JLabel("droite", SwingConstants.RIGHT);
        panelHaut.add(lblGauche);
        panelHaut.add(lblDroite);
        add(panelHaut, BorderLayout.NORTH);

        // --- PARTIE CENTRALE (Boutons Radio avec Cadre) ---
        // On utilise un Box vertical pour empiler les boutons l'un sous l'autre
        JPanel panelCentral = new JPanel();
        Box boxCouleurs = Box.createVerticalBox();
        
        JRadioButton rbBleu = new JRadioButton("bleu");
        JRadioButton rbRouge = new JRadioButton("rouge");
        JRadioButton rbNoir = new JRadioButton("noir");

        // Groupe pour l'exclusivité des boutons radio
        ButtonGroup groupeCouleurs = new ButtonGroup();
        groupeCouleurs.add(rbBleu);
        groupeCouleurs.add(rbRouge);
        groupeCouleurs.add(rbNoir);

        boxCouleurs.add(rbBleu);
        boxCouleurs.add(rbRouge);
        boxCouleurs.add(rbNoir);

        // Création du cadre noir avec titre
        TitledBorder cadre = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Couleur");
        panelCentral.setBorder(cadre);
        panelCentral.add(boxCouleurs);
        
        add(panelCentral, BorderLayout.CENTER);

        // --- PARTIE BASSE (Boutons d'action) ---
        JPanel panelBas = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBas.add(new JButton("RAZ"));
        panelBas.add(new JButton("quitter"));
        add(panelBas, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            new BoutonsRadio().setVisible(true);
        });

	}

}
