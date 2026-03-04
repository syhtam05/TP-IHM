package edu.mermet.tp5.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import edu.mermet.tp5.Application;

public class ActionAfficherTexte extends AbstractAction {
    /**
	 * 
	 */
	private final Application application;

	public ActionAfficherTexte(Application application) {
        super("Saisie de texte");
		this.application = application;
        putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY,KeyEvent.VK_T);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        application.afficherTexte();
    }
}
