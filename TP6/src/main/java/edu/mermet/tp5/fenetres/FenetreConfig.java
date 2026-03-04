package edu.mermet.tp5.fenetres;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import edu.mermet.tp5.Application;

public class FenetreConfig extends JDialog {
    private Application appli;
    private Properties prefs;
    private String[] options = {"Conversion", "Texte", "Diaporama", "Boutons"};

    public FenetreConfig(Application parent, Properties prefs) {
        super(parent, "Configuration des menus", true); // Modale
        this.appli = parent;
        this.prefs = prefs;
        init();
    }

    private void init() {
        setLayout(new GridLayout(options.length + 1, 1));

        for (String opt : options) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            panel.add(new JLabel(opt + " : "));
            
            JRadioButton always = new JRadioButton("Toujours");
            JRadioButton never = new JRadioButton("Jamais");
            JRadioButton auto = new JRadioButton("Auto");
            
            ButtonGroup group = new ButtonGroup();
            group.add(always); group.add(never); group.add(auto);
            
            // Lire la valeur actuelle ou "Auto" par défaut
            String val = prefs.getProperty(opt, "Auto");
            if (val.equals("Toujours")) always.setSelected(true);
            else if (val.equals("Jamais")) never.setSelected(true);
            else auto.setSelected(true);

            // Ecouteurs pour sauvegarder le changement immédiatement dans l'objet Properties
            always.addActionListener(e -> prefs.setProperty(opt, "Toujours"));
            never.addActionListener(e -> prefs.setProperty(opt, "Jamais"));
            auto.addActionListener(e -> prefs.setProperty(opt, "Auto"));

            panel.add(always); panel.add(never); panel.add(auto);
            add(panel);
        }

        JButton valider = new JButton("Valider");
        valider.addActionListener(e -> {
            appli.enregistrerPreferences();
            appli.appliquerCompetence();
            dispose();
        });
        add(valider);

        pack();
        setLocationRelativeTo(getParent());
    }
}