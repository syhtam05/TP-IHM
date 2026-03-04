package edu.mermet.tp5.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import edu.mermet.tp5.Application;

public class ActionQuitter extends AbstractAction {
    /**
	 * 
	 */
	private final Application application;

	public ActionQuitter(Application application) {
	    super("Quitter");
	    this.application = application;
	    putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
	    putValue(Action.MNEMONIC_KEY,KeyEvent.VK_Q);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
	    application.incrementerCompetence(1); // Niveau 1
	    System.exit(0);
	}
}
