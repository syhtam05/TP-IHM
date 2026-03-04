

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class ActionQuitter extends AbstractAction {
    public ActionQuitter() {
        super("Quitter");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control Q"));
        putValue(SHORT_DESCRIPTION, "Quitte l'application");
        putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Ajout de la boîte de dialogue d'avertissement demandée
        int option = JOptionPane.showConfirmDialog(null, 
            "Voulez-vous vraiment quitter l'application ?", 
            "Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}