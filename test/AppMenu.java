import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppMenu extends JFrame {

    private JLabel actionLabel;
    private JLabel fichierLabel;

    public AppMenu() {
        setTitle("Application avec Menu");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Pour centrer les labels

        // 1. Création de la barre de menu
        JMenuBar menuBar = new JMenuBar();

        // 2. Création du menu "Fichier"
        JMenu menuFichier = new JMenu("Fichier");

        // 3. Création de l'élément "Importer"
        JMenuItem itemImporter = new JMenuItem("Importer...");
        
        // --- Intégration de ton code dans l'écouteur du menu ---
        itemImporter.addActionListener(e -> ouvrirSelecteur());

        // Assemblage du menu
        menuFichier.add(itemImporter);
        menuFichier.addSeparator(); // Petite ligne de séparation
        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemQuitter.addActionListener(e -> System.exit(0));
        menuFichier.add(itemQuitter);

        menuBar.add(menuFichier);
        setJMenuBar(menuBar); // On définit la barre de menu de la fenêtre

        // Composants d'affichage pour voir le résultat
        actionLabel = new JLabel("En attente...");
        fichierLabel = new JLabel(" ");
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(actionLabel);
        panel.add(fichierLabel);
        add(panel);
    }

    // Méthode contenant ta logique JFileChooser
    private void ouvrirSelecteur() {
        JFileChooser jfc = new JFileChooser(
                FileSystemView.getFileSystemView().getHomeDirectory());
        
        int retour = jfc.showDialog(this, "Importer");

        switch(retour) {
            case JFileChooser.APPROVE_OPTION:
                actionLabel.setText("Statut : Importation réussie");
                fichierLabel.setText("Fichier : " + jfc.getSelectedFile().getName());
                break;
            case JFileChooser.CANCEL_OPTION:
                actionLabel.setText("Statut : Annulé par l'utilisateur");
                break;
            default:
                actionLabel.setText("Statut : Erreur");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppMenu().setVisible(true));
    }
}