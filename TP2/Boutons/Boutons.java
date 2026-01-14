package Boutons;

import javax.swing.*;
import java.awt.*;

public class Boutons extends JFrame{
	
	public Boutons() {
		setTitle("Mon Application");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Que voulez-vous faire ?", SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(label, BorderLayout.NORTH);

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
