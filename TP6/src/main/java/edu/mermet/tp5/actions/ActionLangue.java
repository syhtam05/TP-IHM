package edu.mermet.tp5.actions;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import edu.mermet.tp5.Application;

public class ActionLangue extends AbstractAction {
    /**
	 * 
	 */
	private final Application application;

	public ActionLangue(Application lApplication) {
	    super("À définir");
	    application = lApplication;
	    //TODO : à définir
	}

    @Override
    public void actionPerformed(ActionEvent ae) {
    System.err.println("à définir");
    //TODO : à définir
    }
}
