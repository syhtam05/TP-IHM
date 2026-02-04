package edu.mermet.tp5.actions;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.Ressources;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;

public class ActionQuitter extends AbstractActionTraduisible {
	public ActionQuitter(Application application) {
		super(Ressources.ACTION_QUITTER, application);
		this.putValue("AcceleratorKey", KeyStroke.getKeyStroke(81, 128));
		this.putValue("MnemonicKey", 81);
	}

	public void actionPerformed(ActionEvent ae) {
		System.exit(0);
	}
}