package edu.mermet.tp5;

import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.*;
import java.util.Properties;
import javax.swing.*;

import edu.mermet.tp5.actions.*;
import edu.mermet.tp5.fenetres.*;

/**
 * Classe principale de l'application gérant les fenêtres, les menus 
 * et les préférences utilisateur.
 */
public class Application extends JFrame {
    private AbstractFenetreInterne conversion;
    private AbstractFenetreInterne texte;
    private AbstractFenetreInterne diaporama;
    private AbstractFenetreInterne boutons;
    
    private Action actionQuitter, actionAfficherConversion, actionAfficherTexte, actionAfficherDiaporama, actionAfficherBoutons;
    
    private JMenuItem itemConversion, itemTexte, itemDiaporama, itemBoutons;
    private JMenu menuFichier, menuApplications, menuAide;
    
    private Properties preferences;
    private String utilisateur;
    private int competenceUtilisateur = 4; // Niveau par défaut

    public Application(String user) {
        // Gestion de l'utilisateur (paramètre main ou login système)
        this.utilisateur = (user != null) ? user : System.getProperty("user.name");
        
        // Initialisation des préférences
        this.preferences = new Properties();
        chargerPreferences();
        
        setTitle("Multi-Window - Utilisateur : " + utilisateur);
        init();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {
        initActions();
        initMenus();
        initFenetres();
        appliquerCompetence(); // Mise à jour initiale de l'affichage
    }

    private void initActions() {
        actionQuitter = new ActionQuitter(this);
        actionAfficherConversion = new ActionAfficherConversion(this);
        actionAfficherTexte = new ActionAfficherTexte(this);
        actionAfficherDiaporama = new ActionAfficherDiaporama(this);
        actionAfficherBoutons = new ActionAfficherBoutons(this);
    }

    private void initFenetres() {
        this.setContentPane(new JDesktopPane());
        conversion = new FenetreConversion(this, actionAfficherConversion);
        texte = new FenetreTexte(this, actionAfficherTexte);
        diaporama = new FenetreDiaporama(this, actionAfficherDiaporama);
        boutons = new FenetreBoutons(this, actionAfficherBoutons);
        
        this.add(conversion);
        this.add(texte);
        this.add(diaporama);
        this.add(boutons);
    }

    private void initMenus() {
        JMenuBar barre = new JMenuBar();
        
        // ------ menu Fichier ------
        menuFichier = new JMenu("Fichier");
        menuFichier.setMnemonic(KeyEvent.VK_F);
        menuFichier.add(new JMenuItem(actionQuitter));
        barre.add(menuFichier);

        // ------ menu Applications ------
        menuApplications = new JMenu("Applications");
        menuApplications.setMnemonic(KeyEvent.VK_A);
        
        itemConversion = new JMenuItem(actionAfficherConversion);
        itemTexte = new JMenuItem(actionAfficherTexte);
        itemDiaporama = new JMenuItem(actionAfficherDiaporama);
        itemBoutons = new JMenuItem(actionAfficherBoutons);
        
        menuApplications.add(itemConversion);
        menuApplications.add(itemTexte);
        menuApplications.add(itemDiaporama);
        menuApplications.add(itemBoutons);
        barre.add(menuApplications);

        // ------ menu Aide ------
        menuAide = new JMenu("Aide");
        menuAide.setMnemonic(KeyEvent.VK_H);
        
        JMenuItem itemHowTo = new JMenuItem("Comment faire ?");
        itemHowTo.addActionListener(e -> new FenetreAide(this).setVisible(true));
        
        JMenuItem itemConfig = new JMenuItem("Configuration des menus");
        itemConfig.addActionListener(e -> new FenetreConfig(this, preferences).setVisible(true));
        
        menuAide.add(itemHowTo);
        menuAide.add(itemConfig);
        barre.add(menuAide);

        this.setJMenuBar(barre);
    }

    // --- Gestion des Préférences ---

    private void chargerPreferences() {
        String fichier = System.getProperty("user.home") + "/.ihm/" + utilisateur + ".xml";
        try (InputStream in = new FileInputStream(fichier)) {
            preferences.loadFromXML(in);
        } catch (IOException e) {
            System.err.println("Aucun fichier de préférences trouvé pour " + utilisateur);
        }
    }

    public void enregistrerPreferences() {
        String repertoire = System.getProperty("user.home") + "/.ihm";
        try {
            Files.createDirectories(Paths.get(repertoire));
            String fichier = repertoire + "/" + utilisateur + ".xml";
            try (OutputStream out = new FileOutputStream(fichier)) {
                preferences.storeToXML(out, "Préférences de " + utilisateur);
            }
        } catch (IOException e) {
            System.err.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }

    /**
     * Applique les règles de visibilité selon les choix utilisateur et la compétence.
     */
    public void appliquerCompetence() {
        itemDiaporama.setVisible(estAffiche("Diaporama", 2));
        itemConversion.setVisible(estAffiche("Conversion", 4));
        itemTexte.setVisible(estAffiche("Texte", 4));
        itemBoutons.setVisible(estAffiche("Boutons", 8));
        
        // Rafraîchir la barre de menu
        getJMenuBar().revalidate();
        getJMenuBar().repaint();
    }

    private boolean estAffiche(String nomProp, int niveauRequis) {
        String pref = preferences.getProperty(nomProp, "Auto");
        if (pref.equals("Toujours")) return true;
        if (pref.equals("Jamais")) return false;
        // Mode "Auto" : dépend du niveau de compétence
        return niveauRequis <= competenceUtilisateur;
    }

    // --- Méthodes de contrôle des fenêtres ---
    public void afficherBoutons() { boutons.setVisible(true); }
    public void afficherConversion() { conversion.setVisible(true); }
    public void afficherDiaporama() { diaporama.setVisible(true); }
    public void afficherTexte() { texte.setVisible(true); }

    public Action getActionAfficherConversion() { return actionAfficherConversion; }
    public Action getActionAfficherTexte() { return actionAfficherTexte; }
    public Action getActionAfficherDiaporama() { return actionAfficherDiaporama; }
}