package edu.mermet.tp5.fenetres;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import edu.mermet.tp5.Application;

public class FenetreConversion extends AbstractFenetreInterne {
    private JTextField champCelsius, champFarenheit;
    private JButton boutonConvertir;
    private Action actionConvertir;
    private boolean celsiusAFocus = true;

    public FenetreConversion(Application appli, Action action) {
        super(appli, action, "Conversion celsius/Farenheit");
        setTitle("Conversion Celsius/Farenheit");
        this.setLayout(new GridLayout(3, 1));

        // 1. Aide par Bulles d'aide (Tooltips)
        // -----------------------------------
        champCelsius = new JTextField(15);
        champCelsius.setToolTipText("Entrez une température en degrés Celsius (ex: 25)");
        
        champFarenheit = new JTextField(15);
        champFarenheit.setToolTipText("Entrez une température en degrés Farenheit (ex: 77)");

        // 2. Aide par Icône "?"
        // ---------------------
        // Panel Celsius
        JPanel ligneCelsius = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        ligneCelsius.add(new JLabel("Celsius :"));
        ligneCelsius.add(champCelsius);
        ligneCelsius.add(creerBoutonAide("Entrez une valeur numérique en Celsius pour la convertir."));
        this.add(ligneCelsius);

        // Panel Farenheit
        JPanel ligneFarenheit = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        ligneFarenheit.add(new JLabel("Farenheit :"));
        ligneFarenheit.add(champFarenheit);
        ligneFarenheit.add(creerBoutonAide("Entrez une valeur numérique en Farenheit pour la convertir."));
        this.add(ligneFarenheit);

        // 3. Aide par Menu Contextuel (Clic droit)
        // ----------------------------------------
        JPopupMenu menuContextuel = new JPopupMenu();
        JMenuItem itemAide = new JMenuItem("Aide");
        itemAide.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Aide à la conversion :\n" +
                "- Saisissez un nombre dans l'un des champs.\n" +
                "- Cliquez sur 'Convertir' ou appuyez sur Entrée.\n" +
                "- L'unité source est celle du dernier champ sélectionné.",
                "Aide Contextuelle", JOptionPane.INFORMATION_MESSAGE);
        });
        menuContextuel.add(itemAide);

        // Ajouter le menu contextuel aux deux champs
        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e) { if (e.isPopupTrigger()) doPop(e); }
            public void mouseReleased(MouseEvent e) { if (e.isPopupTrigger()) doPop(e); }
            private void doPop(MouseEvent e) { menuContextuel.show(e.getComponent(), e.getX(), e.getY()); }
        };
        champCelsius.addMouseListener(mouseListener);
        champFarenheit.addMouseListener(mouseListener);

        // Fin de l'interface
        JPanel ligneValider = new JPanel(new FlowLayout(FlowLayout.CENTER));
        actionConvertir = new ActionConvertir();
        boutonConvertir = new JButton(actionConvertir);
        ligneValider.add(boutonConvertir);
        this.add(ligneValider);

        champCelsius.addFocusListener(new EcouteurFocus(true));
        champFarenheit.addFocusListener(new EcouteurFocus(false));
        
        pack();
        getRootPane().setDefaultButton(boutonConvertir);
    }

    /**
     * Crée un petit bouton d'aide discret avec une icône "?"
     */
    private JLabel creerBoutonAide(String message) {
        JLabel aide = new JLabel();
        
        // On essaie de charger l'icône, sinon on met du texte
        java.net.URL imgURL = getClass().getResource("/icons/help_small.png");
        if (imgURL != null) {
            aide.setIcon(new ImageIcon(imgURL));
        } else {
            aide.setText(" [?] ");
            aide.setForeground(Color.BLUE); // Pour qu'il ressemble à un lien
        }
        
        aide.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aide.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(FenetreConversion.this, 
                    message, "Aide Contextuelle", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return aide;
    }

    // --- Classes internes existantes (ActionConvertir, EcouteurFocus) gardées ---
    private class EcouteurFocus implements FocusListener {
        private boolean aStocker;
        public EcouteurFocus(boolean b) { aStocker = b; }
        @Override public void focusGained(FocusEvent fe) { celsiusAFocus = aStocker; }
        @Override public void focusLost(FocusEvent fe) {}
    }

    private class ActionConvertir extends AbstractAction {
        public ActionConvertir() { super("Convertir"); }
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (celsiusAFocus) {
                    double c = Double.parseDouble(champCelsius.getText());
                    champFarenheit.setText("" + (9./5*c + 32));
                } else {
                    double f = Double.parseDouble(champFarenheit.getText());
                    champCelsius.setText("" + ((f - 32) * 5./9));
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(FenetreConversion.this, "Veuillez saisir un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}