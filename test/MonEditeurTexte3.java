import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.io.*;

public class MonEditeurTexte3 extends JFrame {

    private JTextArea zoneTexte;
    private File fichierActuel = null;
    private UndoManager undoManager = new UndoManager(); // Gestionnaire d'annulation

    public MonEditeurTexte3() {
        setTitle("Mon Éditeur - Nouveau document");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        zoneTexte = new JTextArea();
        zoneTexte.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        // On demande à la zone de texte de prévenir l'undoManager à chaque changement
        zoneTexte.getDocument().addUndoableEditListener(e -> {
            undoManager.addEdit(e.getEdit());
        });

        add(new JScrollPane(zoneTexte), BorderLayout.CENTER);

        initMenuBar();
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // --- MENU FICHIER ---
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem itemOuvrir = new JMenuItem("Ouvrir...");
        itemOuvrir.addActionListener(e -> ouvrir());
        
        JMenuItem itemSauver = new JMenuItem("Enregistrer");
        itemSauver.addActionListener(e -> enregistrer());

        menuFichier.add(itemOuvrir);
        menuFichier.add(itemSauver);
        menuBar.add(menuFichier);

        // --- MENU ÉDITION (Undo) ---
        JMenu menuEdition = new JMenu("Édition");
        JMenuItem itemUndo = new JMenuItem("Annuler (Undo)");
        
        // Raccourci Ctrl + Z
        itemUndo.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        itemUndo.addActionListener(e -> {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        });

        menuEdition.add(itemUndo);
        menuBar.add(menuEdition);

        setJMenuBar(menuBar);
    }

    private void ouvrir() {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileNameExtensionFilter("Fichiers texte", "txt"));
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fichierActuel = jfc.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(fichierActuel))) {
                zoneTexte.read(reader, null);
                undoManager.discardAllEdits(); // On vide l'historique car c'est un nouveau fichier
                setTitle("Mon Éditeur - " + fichierActuel.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur de lecture");
            }
        }
    }

    private void enregistrer() {
        if (fichierActuel == null) {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                fichierActuel = jfc.getSelectedFile();
                if (!fichierActuel.getName().endsWith(".txt")) {
                    fichierActuel = new File(fichierActuel.getAbsolutePath() + ".txt");
                }
            } else return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierActuel))) {
            zoneTexte.write(writer);
            setTitle("Mon Éditeur - " + fichierActuel.getName());
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