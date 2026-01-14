package Boutons;

import javax.swing.*;
import java.awt.*;

public class Boutons extends JFrame{
	
	public Boutons() {
		setTitle("Mon Application");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Partie haute : JLabel centré
        JLabel label = new JLabel("Que voulez-vous faire ?", SwingConstants.CENTER);
        // On ajoute une marge pour l'esthétique
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(label, BorderLayout.NORTH);

        // 2. Partie basse : Panneau pour les boutons
        // FlowLayout(alignement, hgap, vgap) -> hgap de 10 pixels
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        panelBoutons.add(new JButton("Valider"));
        panelBoutons.add(new JButton("Annuler"));
        panelBoutons.add(new JButton("Quitter"));

        add(panelBoutons, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            new Boutons().setVisible(true);
        });
	}

}
