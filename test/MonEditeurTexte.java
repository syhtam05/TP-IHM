import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class MonEditeurTexte extends JFrame {

    private JTextArea zoneTexte;
    private File fichierActuel = null; // Pour savoir quel fichier on édite

    public MonEditeurTexte() {
        setTitle("Mon Éditeur - Nouveau document");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        zoneTexte = new JTextArea();
        zoneTexte.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(zoneTexte), BorderLayout.CENTER);

        initMenuBar();
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");

        // Ouvrir
        JMenuItem itemOuvrir = new JMenuItem("Ouvrir...");
        itemOuvrir.addActionListener(e -> ouvrir());

        // Enregistrer
        JMenuItem itemSauver = new JMenuItem("Enregistrer");
        itemSauver.addActionListener(e -> enregistrer());

        // Quitter
        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemQuitter.addActionListener(e -> System.exit(0));

        menuFichier.add(itemOuvrir);
        menuFichier.add(itemSauver);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);
        menuBar.add(menuFichier);
        setJMenuBar(menuBar);
    }

    private void ouvrir() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileNameExtensionFilter("Fichiers texte", "txt"));
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fichierActuel = jfc.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(fichierActuel))) {
                zoneTexte.read(reader, null);
                setTitle("Mon Éditeur - " + fichierActuel.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur de lecture");
            }
        }
    }

    private void enregistrer() {
        // Si aucun fichier n'est ouvert, on demande où enregistrer (Save As)
        if (fichierActuel == null) {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                fichierActuel = jfc.getSelectedFile();
                // Ajoute .txt si l'utilisateur l'a oublié
                if (!fichierActuel.getName().endsWith(".txt")) {
                    fichierActuel = new File(fichierActuel.getAbsolutePath() + ".txt");
                }
            } else {
                return; // Annulé
            }
        }

        // Ecriture du contenu
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierActuel))) {
            zoneTexte.write(writer);
            setTitle("Mon Éditeur - " + fichierActuel.getName());
            JOptionPane.showMessageDialog(this, "Fichier enregistré !");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erreur d'écriture");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MonEditeurTexte().setVisible(true);
        });
    }
}