import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class MonVisualiseurImage2 extends JFrame {

    private JLabel labelImage;
    private BufferedImage imageOriginale = null;
    private File fichierActuel = null;
    
    // Nouvelle variable pour gérer le niveau de zoom
    private double facteurZoom = 1.0; 

    public MonVisualiseurImage2() {
        setTitle("Mon Visualiseur - Aucune image");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        labelImage = new JLabel();
        labelImage.setHorizontalAlignment(JLabel.CENTER);
        labelImage.setVerticalAlignment(JLabel.CENTER);
        
        add(new JScrollPane(labelImage), BorderLayout.CENTER);

        initMenuBar();
        initToolBar(); // Ajout d'une barre de boutons pour le zoom

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                appliquerAffichage();
            }
        });
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");

        JMenuItem itemOuvrir = new JMenuItem("Ouvrir une image...");
        itemOuvrir.addActionListener(e -> ouvrir());

        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemQuitter.addActionListener(e -> System.exit(0));

        menuFichier.add(itemOuvrir);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);
        menuBar.add(menuFichier);
        setJMenuBar(menuBar);
    }

    // Barre d'outils pour le Zoom
    private void initToolBar() {
        JToolBar toolBar = new JToolBar();
        
        JButton btnZoomIn = new JButton("Zoom +");
        btnZoomIn.addActionListener(e -> {
            facteurZoom *= 1.2; // Augmente de 20%
            appliquerAffichage();
        });

        JButton btnZoomOut = new JButton("Zoom -");
        btnZoomOut.addActionListener(e -> {
            facteurZoom /= 1.2; // Diminue de 20%
            appliquerAffichage();
        });

        JButton btnReset = new JButton("Taille Réelle");
        btnReset.addActionListener(e -> {
            facteurZoom = 1.0;
            appliquerAffichage();
        });

        toolBar.add(btnZoomIn);
        toolBar.add(btnZoomOut);
        toolBar.add(btnReset);
        add(toolBar, BorderLayout.NORTH);
    }

    private void ouvrir() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileNameExtensionFilter("Images (jpg, png, gif)", "jpg", "jpeg", "png", "gif"));
        
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fichierActuel = jfc.getSelectedFile();
            try {
                imageOriginale = ImageIO.read(fichierActuel);
                setTitle("Mon Visualiseur - " + fichierActuel.getName());
                facteurZoom = 1.0; // Reset du zoom à l'ouverture
                appliquerAffichage();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors du chargement.");
            }
        }
    }

    /**
     * Calcule la taille finale en fonction du zoom et de l'image originale
     */
    private void appliquerAffichage() {
        if (imageOriginale == null) return;

        // Calcul des nouvelles dimensions basées sur l'original et le zoom
        int nouvelleLargeur = (int) (imageOriginale.getWidth() * facteurZoom);
        int nouvelleHauteur = (int) (imageOriginale.getHeight() * facteurZoom);

        // Optionnel : On peut empêcher un zoom trop petit ou trop grand
        if (nouvelleLargeur > 0 && nouvelleHauteur > 0) {
            Image imgRedimensionnee = imageOriginale.getScaledInstance(nouvelleLargeur, nouvelleHauteur, Image.SCALE_SMOOTH);
            labelImage.setIcon(new ImageIcon(imgRedimensionnee));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MonVisualiseurImage().setVisible(true));
    }
}