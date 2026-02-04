package edu.mermet.tp5.actions;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.Ressources;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;

public class ActionAfficherConversion extends AbstractActionTraduisible {
	public ActionAfficherConversion(Application application) {
		super(Ressources.ACTION_CONVERSION, application);
		this.putValue("AcceleratorKey", KeyStroke.getKeyStroke(67, 128));
		this.putValue("MnemonicKey", 67);
	}

	public void actionPerformed(ActionEvent ae) {
		this.application.afficherConversion();
	}
}