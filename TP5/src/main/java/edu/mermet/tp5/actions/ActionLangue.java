package edu.mermet.tp5.actions;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.GestionnaireRessources;
import edu.mermet.tp5.Ressources;
import java.awt.event.ActionEvent;
import java.util.Locale;

public class ActionLangue extends AbstractActionTraduisible {
	private Locale lieu;
	private Ressources cle;

	public ActionLangue(Application application, Ressources laCle, Locale leLieu) {
		super(laCle, application);
		this.cle = laCle;
		this.lieu = leLieu;
	}

	public void actionPerformed(ActionEvent ae) {
		GestionnaireRessources.getRessourceManager().definirLieu(this.lieu);
	}
}