

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Cette classe fournit des méthodes utilitaires
 * pour la gestion des fichiers image.
 * 
 * Elle permet de charger et sauvegarder des images,
 * de redimensionner des images, et de manipuler
 * les noms de fichiers (récupération du nom sans
 * extension, récupération de l'extension avec point).
 * 
 */
public class UtilitaireFichierImage {

  /**
   * Permet, à partir d'un chemin d'accès complet à un fichier,
   * de récupérer le chemin sans l'extension du fichier
   * 
   * <i>Exemple</i> : si le chemin est "/home/toto/photo.jpg",
   * la méthode retourne "/home/toto/photo"
   * @param cheminImage le chemin complet de l'image
   * @return le chemin sans l'extension
   */
  public static String getNomSansExtension(String cheminImage) {
    return cheminImage.substring(0, cheminImage.lastIndexOf('.'));
  }

  /**
   * Permet, à partir d'un chemin d'accès complet à un fichier,
   * de récupérer l'extension du fichier, point compris.
   * 
   * <i>Exemple</i> : si le chemin est "/home/toto/photo.jpg",
   * la méthode retourne ".jpg"
   * @param cheminImage le chemin complet de l'image
   * @return l'extension avec le point
   */
  public static String getExtensionAvecPoint(String cheminImage) {
    return cheminImage.substring(cheminImage.lastIndexOf('.'));
  }

  /**
   * Permet de charger une image à partir d'un fichier.
   * @param fichier : le fichier image à charger
   * @return l'image chargée
   */
  public static ImageIcon chargerImage(File fichier) {
    ImageIcon image = new ImageIcon(fichier.getAbsolutePath());
    return image;
  }

  /**
   * Permet de sauvegarder une image dans un fichier.
   * 
   * @param fichier le fichier de destination
   * @param icone l'image à sauvegarder
   */
  public static void sauverImage(File fichier, ImageIcon icone) {
    BufferedImage bfi = new BufferedImage(icone.getIconWidth(), icone.getIconHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = bfi.createGraphics();
    g2.drawImage(icone.getImage(), 0, 0, null);
    g2.dispose();
    try {
      ImageIO.write(bfi, "jpg", fichier);
    } catch (IOException e1) {
      System.out.println("Problème lors de la sauvegarde de l'image");
    }
  }

  /**
   * Permet de sauvegarder une image dans un fichier.
   * 
   * @param cheminFichier le chemin du fichier de destination
   * @param icone l'image à sauvegarder
   */
  public static void sauverImage(String cheminFichier, ImageIcon icone) {
    sauverImage(new File(cheminFichier), icone);
  }

  /**
   * Permet de redimensionner une image en fonction d'un coefficient..
   * 
   * <i>Exemple</i> : si le coefficient est 0.9, l'image sera réduite
   * de 10%.
   * @param icone l'image à redimensionner
   * @param coefficient le coefficient de redimensionnement
   * @return l'image redimensionnée
   */
  public static ImageIcon redimensionnerImage(ImageIcon icone, double coefficient) {
    int nouvelleLargeur = (int) (icone.getIconWidth() * coefficient);
    int nouvelleHauteur = (int) (icone.getIconHeight() * coefficient);
    ImageIcon imageRedimensionnee = new ImageIcon(
        icone.getImage().getScaledInstance(nouvelleLargeur, nouvelleHauteur, java.awt.Image.SCALE_FAST));
    return imageRedimensionnee;
  }
}
