import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Fenetre extends JFrame {
    private ActionQuitter quitter;
    private JLabel affichage_image;
    private JMenuItem item_sauver, item_diminuer, item_augmenter, item_reset;
    
    // État de l'image
    private ImageIcon image_initiale;
    private ImageIcon image_courante;
    private File fichier_source;
    private double taux_configuration = 0.10; // 10% par défaut
    private int compteur_sauvegarde = 1;

    public Fenetre() {
        this.setTitle("TP4 - Traitement d'images");
        init();
        pack();
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        init_composants();
        init_actions();
        init_menus();
        gerer_etat_actions(false);
    }

    private void init_actions() {
        quitter = new ActionQuitter();
    }

    private void init_menus() {
        JMenuBar barre = new JMenuBar();
        
        // --- Menu Fichier ---
        JMenu m_fichier = new JMenu("Fichier");
        m_fichier.setMnemonic('F');

        JMenuItem item_charger = new JMenuItem("Charger");
        item_charger.addActionListener(e -> charger_image());

        item_sauver = new JMenuItem("Sauver");
        item_sauver.addActionListener(e -> sauver_image());

        JMenuItem item_config = new JMenuItem("Configurer");
        item_config.addActionListener(e -> configurer_taux());

        m_fichier.add(item_charger);
        m_fichier.add(item_sauver);
        m_fichier.add(item_config);
        m_fichier.addSeparator();
        m_fichier.add(quitter);

        // --- Menu Image ---
        JMenu m_image = new JMenu("Image");
        item_diminuer = new JMenuItem("Diminuer");
        item_diminuer.addActionListener(e -> appliquer_redimensionnement(1.0 - taux_configuration));

        item_augmenter = new JMenuItem("Augmenter");
        item_augmenter.addActionListener(e -> {
            appliquer_redimensionnement(1.0 + taux_configuration);
            ecrire_log_ihm("augmenter");
        });

        item_reset = new JMenuItem("Reset");
        item_reset.addActionListener(e -> reset_image());

        m_image.add(item_diminuer);
        m_image.add(item_augmenter);
        m_image.add(item_reset);

        barre.add(m_fichier);
        barre.add(m_image);
        setJMenuBar(barre);
    }

    private void init_composants() {
        affichage_image = new JLabel();
        JScrollPane scroll = new JScrollPane(affichage_image);
        
        // Panel droit vide pour le JSplitPane
        JPanel panel_droit = new JPanel();
        
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, panel_droit);
        split.setDividerLocation(600);
        
        getContentPane().add(split, BorderLayout.CENTER);
    }

    // --- Méthodes Logique (Snake Case) ---

    private void charger_image() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fichier_source = chooser.getSelectedFile();
            image_initiale = UtilitaireFichierImage.chargerImage(fichier_source);
            image_courante = image_initiale;
            affichage_image.setIcon(image_courante);
            compteur_sauvegarde = 1;
            gerer_etat_actions(true);
        }
    }

    private void sauver_image() {
        String chemin = fichier_source.getAbsolutePath();
        String nom_base = UtilitaireFichierImage.getNomSansExtension(chemin);
        String extension = UtilitaireFichierImage.getExtensionAvecPoint(chemin);
        
        String nouveau_nom = nom_base + compteur_sauvegarde + extension;
        File destination = new File(fichier_source.getParent(), nouveau_nom);
        
        UtilitaireFichierImage.sauverImage(destination, image_courante);
        compteur_sauvegarde++;
        JOptionPane.showMessageDialog(this, "Fichier sauvegardé : " + nouveau_nom);
    }

    private void configurer_taux() {
        Object[] valeurs = {"10%", "20%", "30%", "40%", "500%"};
        // Valeur par défaut suggérée : actuelle + 5%
        String selection = (String) JOptionPane.showInputDialog(this, 
                "Taux actuel : " + (int)(taux_configuration * 100) + "%\nChoisissez un taux :", 
                "Configuration", JOptionPane.QUESTION_MESSAGE, null, valeurs, valeurs[0]);
        
        if (selection != null) {
            taux_configuration = Double.parseDouble(selection.replace("%", "")) / 100.0;
        }
    }

    private void appliquer_redimensionnement(double coeff) {
    image_courante = UtilitaireFichierImage.redimensionnerImage(image_courante, coeff);
    affichage_image.setIcon(image_courante);
    
    // On active le bouton de sauvegarde dès qu'une modif est faite
    item_sauver.setEnabled(true); 
}

    private void reset_image() {
        image_courante = image_initiale;
        affichage_image.setIcon(image_courante);
    }

    private void ecrire_log_ihm(String action) {
        try {
            String path = System.getProperty("user.home") + File.separator + ".logIHM.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(action);
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void gerer_etat_actions(boolean etat) {
        item_sauver.setEnabled(etat);
        item_diminuer.setEnabled(etat);
        item_augmenter.setEnabled(etat);
        item_reset.setEnabled(etat);
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      new Fenetre();
    });
  }
}