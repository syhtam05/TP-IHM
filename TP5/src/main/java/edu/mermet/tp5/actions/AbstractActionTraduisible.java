package edu.mermet.tp5.actions;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.GestionnaireRessources;
import edu.mermet.tp5.Ressources;
import edu.mermet.tp5.Traduisible;
import javax.swing.AbstractAction;

public abstract class AbstractActionTraduisible extends AbstractAction implements Traduisible {
	private Ressources cle;
	private GestionnaireRessources ressourceManager;
	protected final Application application;

	public AbstractActionTraduisible(Ressources laCle, Application appli) {
		this.cle = laCle;
		this.application = appli;
		this.ressourceManager = GestionnaireRessources.getRessourceManager();
		this.putValue("Name", this.ressourceManager.getString(this.cle));
		this.ressourceManager.addTraduisible(this);
	}

	public void traduire() {
		this.putValue("Name", this.ressourceManager.getString(this.cle));
	}
}