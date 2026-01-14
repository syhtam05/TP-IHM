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

        JPanel panelHaut = new JPanel(new GridLayout(1, 2));
        JLabel lblGauche = new JLabel("gauche", SwingConstants.LEFT);
        JLabel lblDroite = new JLabel("droite", SwingConstants.RIGHT);
        panelHaut.add(lblGauche);
        panelHaut.add(lblDroite);
        add(panelHaut, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        Box boxCouleurs = Box.createVerticalBox();
        
        JRadioButton rbBleu = new JRadioButton("bleu");
        JRadioButton rbRouge = new JRadioButton("rouge");
        JRadioButton rbNoir = new JRadioButton("noir");

        ButtonGroup groupeCouleurs = new ButtonGroup();
        groupeCouleurs.add(rbBleu);
        groupeCouleurs.add(rbRouge);
        groupeCouleurs.add(rbNoir);

        boxCouleurs.add(rbBleu);
        boxCouleurs.add(rbRouge);
        boxCouleurs.add(rbNoir);

        TitledBorder cadre = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), "Couleur");
        panelCentral.setBorder(cadre);
        panelCentral.add(boxCouleurs);
        
        add(panelCentral, BorderLayout.CENTER);

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
