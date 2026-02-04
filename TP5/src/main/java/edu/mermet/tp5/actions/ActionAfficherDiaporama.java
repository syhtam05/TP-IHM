package edu.mermet.tp5.actions;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.Ressources;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;

public class ActionAfficherDiaporama extends AbstractActionTraduisible {
	public ActionAfficherDiaporama(Application application) {
		super(Ressources.ACTION_DIAPORAMA, application);
		this.putValue("AcceleratorKey", KeyStroke.getKeyStroke(68, 128));
		this.putValue("MnemonicKey", 68);
	}

	public void actionPerformed(ActionEvent ae) {
		this.application.afficherDiaporama();
	}
}