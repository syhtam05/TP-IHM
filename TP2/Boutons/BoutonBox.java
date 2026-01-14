package Boutons;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BoutonBox extends JFrame {
	
	public BoutonBox() {
		setTitle("Formulaire de profil");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- PARTIE HAUTE (Cases à cocher homogènes) ---
        // GridLayout(1, 2) assure que chaque cellule occupe 50% de la largeur
        JPanel panelHaut = new JPanel(new GridLayout(1, 2));
        JCheckBox cbGaucher = new JCheckBox("gaucher", false);
        JCheckBox cbDroitier = new JCheckBox("droitier", false);
        
        // Centrage horizontal du contenu à l'intérieur de chaque cellule
        cbGaucher.setHorizontalAlignment(SwingConstants.CENTER);
        cbDroitier.setHorizontalAlignment(SwingConstants.CENTER);
        
        panelHaut.add(cbGaucher);
        panelHaut.add(cbDroitier);
        add(panelHaut, BorderLayout.NORTH);

        // --- PARTIE CENTRALE (Combo et Liste) ---
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // JComboBox
        String[] niveaux = {"École maternelle", "École élémentaire", "Collège", "Lycée", "Enseignement Supérieur"};
        JComboBox<String> comboNiveaux = new JComboBox<>(niveaux);
        comboNiveaux.setMaximumSize(new Dimension(300, 30));

        // JList (avec scroll car la liste peut être longue)
        String[] matieres = {"Mathématiques", "Français", "Anglais", "Physique-Chimie", "SVT", "EPS"};
        JList<String> listeMatieres = new JList<>(matieres);
        listeMatieres.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollListe = new JScrollPane(listeMatieres);
        scrollListe.setPreferredSize(new Dimension(300, 100));

        panelCentral.add(new JLabel("Niveau d'étude :"));
        panelCentral.add(comboNiveaux);
        panelCentral.add(Box.createVerticalStrut(20)); // Espace entre les deux
        panelCentral.add(new JLabel("Matières préférées :"));
        panelCentral.add(scrollListe);
        
        add(panelCentral, BorderLayout.CENTER);

        // --- PARTIE BASSE (Boutons aux extrémités) ---
        JPanel panelBas = new JPanel(new BorderLayout());
        // Ajout d'une marge de 5 pixels tout autour du panel
        panelBas.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JButton btnAnnuler = new JButton("Annuler");
        JButton btnQuitter = new JButton("Quitter");
        
        panelBas.add(btnAnnuler, BorderLayout.WEST);
        panelBas.add(btnQuitter, BorderLayout.EAST);
        
        add(panelBas, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            new BoutonBox().setVisible(true);
        });

	}

}
