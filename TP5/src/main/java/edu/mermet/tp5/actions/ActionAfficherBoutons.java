package edu.mermet.tp5.actions;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.Ressources;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;

public class ActionAfficherBoutons extends AbstractActionTraduisible {
	public ActionAfficherBoutons(Application application) {
		super(Ressources.ACTION_BOUTONS, application);
		this.putValue("AcceleratorKey", KeyStroke.getKeyStroke(66, 128));
		this.putValue("MnemonicKey", 66);
	}

	public void actionPerformed(ActionEvent ae) {
		this.application.afficherBoutons();
	}
}