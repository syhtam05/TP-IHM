package BulleInfo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Cadres.TestCadres;

public class TestBulleInfo extends JFrame {
	
	public TestBulleInfo() {
		setTitle("Test des Bordures JLabel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

        // Création des 3 labels
        JLabel label1 = new JLabel("Label 1");
        JLabel label2 = new JLabel("Label 2");
        JLabel label3 = new JLabel("Label 3");

        // Ajout des bordures pour mieux les voir (optionnel)
        label1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label3.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // AJOUT DES BULLES D'AIDE (ToolTips)
        label1.setToolTipText("Ceci est l'aide pour le premier label");
        label2.setToolTipText("Vous survolez le deuxième label");
        label3.setToolTipText("Info-bulle du troisième label");

        // Ajout à la fenêtre
        add(label1);
        add(label2);
        add(label3);
        
        pack();
        setLocationRelativeTo(null);
	}

	private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(300, 50));
        return label;
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new TestBulleInfo().setVisible(true));
	}
}
