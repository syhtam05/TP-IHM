package edu.mermet.tp5.fenetres;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class FenetreAide extends JDialog {
    private JList<String> listeTitres;
    private JEditorPane affichageAide;
    private ResourceBundle titres, textes;

    public FenetreAide(JFrame parent) {
        super(parent, "Comment faire ?", false); // Non modale
        
        // Chargement des ressources
        titres = ResourceBundle.getBundle("HowTo.titres");
        textes = ResourceBundle.getBundle("HowTo.textes");

        // Liste à gauche
        DefaultListModel<String> model = new DefaultListModel<>();
        Enumeration<String> keys = titres.getKeys();
        while (keys.hasMoreElements()) model.addElement(titres.getString(keys.nextElement()));
        
        listeTitres = new JList<>(model);
        
        // Affichage à droite
        affichageAide = new JEditorPane();
        affichageAide.setContentType("text/html");
        affichageAide.setEditable(false);

        // Ecouteur de sélection
        listeTitres.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String titreSel = listeTitres.getSelectedValue();
                // Retrouver la clé à partir du titre (simplifié ici)
                titres.keySet().stream()
                    .filter(k -> titres.getString(k).equals(titreSel))
                    .findFirst()
                    .ifPresent(k -> affichageAide.setText(textes.getString(k)));
            }
        });

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(listeTitres), new JScrollPane(affichageAide));
        split.setDividerLocation(150);
        add(split);
        setSize(500, 300);
        setLocationRelativeTo(parent);
    }
}