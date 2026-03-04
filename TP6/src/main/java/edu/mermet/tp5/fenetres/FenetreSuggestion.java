package edu.mermet.tp5.fenetres;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import edu.mermet.tp5.Application;

public class FenetreSuggestion extends JDialog {
    private Application appli;
    private Properties prefs;
    private String astuceChoisie;
    private Map<String, String> toutesLesAstuces;

    public FenetreSuggestion(Application parent, Properties prefs) {
        super(parent, "Suggestion du jour", true);
        this.appli = parent;
        this.prefs = prefs;
        
        initAstuces();
        choisirAstuceAleatoire();
        
        if (astuceChoisie != null) {
            initInterface();
        } else {
            // Toutes les astuces sont masquées, on ne fait rien
            dispose();
        }
    }

    private void initAstuces() {
        toutesLesAstuces = new HashMap<>();
        toutesLesAstuces.put("astuce.quitter", "Le raccourci CTRL+Q permet de quitter instantanément.");
        toutesLesAstuces.put("astuce.conv", "La conversion Celsius/Farenheit supporte les nombres décimaux.");
        toutesLesAstuces.put("astuce.gras", "Utilisez CTRL+G dans la fenêtre texte pour passer en gras.");
        toutesLesAstuces.put("astuce.diapo", "Le diaporama défile automatiquement toutes les 2 secondes.");
        toutesLesAstuces.put("astuce.aide", "Faites un clic droit sur les champs de saisie pour obtenir de l'aide.");
    }

    private void choisirAstuceAleatoire() {
        // Déterminer le "niveau suivant"
        // Si comp=1 -> suivant=2, si comp=2 -> suivant=4, si comp=4 -> suivant=8
        int compActuelle = (int) Double.parseDouble(prefs.getProperty("competence", "1.0"));
        int niveauMaxAutorise = compActuelle * 2; 

        List<String> clesDisponibles = new ArrayList<>();
        
        // On suppose que vos clés d'astuces contiennent le niveau (ex: "astuce.quitter.1")
        // Ou alors vous créez une Map qui associe Clé -> Niveau
        Map<String, Integer> niveauxAstuces = Map.of(
            "astuce.quitter", 1,
            "astuce.diapo", 2,
            "astuce.conv", 4,
            "astuce.gras", 4,
            "astuce.aide", 1
        );

        for (String cle : toutesLesAstuces.keySet()) {
            int niveauAstuce = niveauxAstuces.getOrDefault(cle, 1);
            if (!"off".equals(prefs.getProperty(cle)) && niveauAstuce <= niveauMaxAutorise) {
                clesDisponibles.add(cle);
            }
        }

        if (!clesDisponibles.isEmpty()) {
            Random rand = new Random();
            astuceChoisie = clesDisponibles.get(rand.nextInt(clesDisponibles.size()));
        }
    }

    private void initInterface() {
        setLayout(new BorderLayout(10, 10));
        
        // Message de l'astuce
        JLabel label = new JLabel("<html><body style='width: 250px; padding:10px;'>" 
                                    + toutesLesAstuces.get(astuceChoisie) + "</body></html>");
        add(label, BorderLayout.CENTER);

        // Panneau de boutons
        JPanel panelBoutons = new JPanel();
        JButton boutonFermer = new JButton("Fermer");
        JButton boutonPlusAfficher = new JButton("Ne plus afficher cette astuce");

        boutonFermer.addActionListener(e -> dispose());
        
        boutonPlusAfficher.addActionListener(e -> {
            prefs.setProperty(astuceChoisie, "off");
            appli.enregistrerPreferences(); // Sauvegarde dans le XML
            dispose();
        });

        panelBoutons.add(boutonFermer);
        panelBoutons.add(boutonPlusAfficher);
        add(panelBoutons, BorderLayout.SOUTH);

        // Bouton par défaut (Touche Entrée)
        getRootPane().setDefaultButton(boutonFermer);

        pack();
        setLocationRelativeTo(getParent());
    }
}