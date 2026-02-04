package edu.mermet.tp5.actions;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.Ressources;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;

public class ActionAfficherTexte extends AbstractActionTraduisible {
	public ActionAfficherTexte(Application application) {
		super(Ressources.ACTION_TEXTE, application);
		this.putValue("AcceleratorKey", KeyStroke.getKeyStroke(84, 128));
		this.putValue("MnemonicKey", 84);
	}

	public void actionPerformed(ActionEvent ae) {
		this.application.afficherTexte();
	}
}