import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class MonVisualiseurImage extends JFrame {

    private JLabel labelImage; // Remplace la zone de texte
    private BufferedImage imageOriginale = null;
    private File fichierActuel = null;

    public MonVisualiseurImage() {
        setTitle("Mon Visualiseur - Aucune image");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuration de l'affichage
        labelImage = new JLabel();
        labelImage.setHorizontalAlignment(JLabel.CENTER);
        labelImage.setVerticalAlignment(JLabel.CENTER);
        
        // On place le label dans un JScrollPane pour gérer les très grandes images
        add(new JScrollPane(labelImage), BorderLayout.CENTER);

        initMenuBar();

        // Écouteur pour redimensionner l'image quand la fenêtre change de taille
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                redimensionnerImage();
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

    private void ouvrir() {
        JFileChooser jfc = new JFileChooser();
        // Filtre pour les formats d'images courants
        jfc.setFileFilter(new FileNameExtensionFilter("Images (jpg, png, gif)", "jpg", "jpeg", "png", "gif"));
        
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fichierActuel = jfc.getSelectedFile();
            try {
                // Lecture de l'image
                imageOriginale = ImageIO.read(fichierActuel);
                setTitle("Mon Visualiseur - " + fichierActuel.getName());
                redimensionnerImage(); // Affichage initial
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors du chargement de l'image.");
            }
        }
    }

    /**
     * Calcule la taille optimale pour que l'image tienne dans la fenêtre
     * tout en gardant ses proportions.
     */
    private void redimensionnerImage() {
        if (imageOriginale == null) return;

        // Récupérer les dimensions de la zone d'affichage (le ScrollPane)
        int labelLargeur = labelImage.getParent().getWidth();
        int labelHauteur = labelImage.getParent().getHeight();

        if (labelLargeur > 0 && labelHauteur > 0) {
            // Calcul du ratio pour ne pas déformer l'image
            double ratioImg = (double) imageOriginale.getWidth() / imageOriginale.getHeight();
            double ratioFenetre = (double) labelLargeur / labelHauteur;

            int nouvelleLargeur = labelLargeur;
            int nouvelleHauteur = labelHauteur;

            if (ratioImg > ratioFenetre) {
                nouvelleHauteur = (int) (labelLargeur / ratioImg);
            } else {
                nouvelleLargeur = (int) (labelHauteur * ratioImg);
            }

            // Création de l'image redimensionnée
            Image imgRedimensionnee = imageOriginale.getScaledInstance(nouvelleLargeur, nouvelleHauteur, Image.SCALE_SMOOTH);
            labelImage.setIcon(new ImageIcon(imgRedimensionnee));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MonVisualiseurImage().setVisible(true);
        });
    }
}