package edu.mermet.tp5.fenetres;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.GestionnaireRessources;
import edu.mermet.tp5.Traduisible;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public abstract class AbstractFenetreInterne extends JInternalFrame implements Traduisible {
	private Action action;
	protected GestionnaireRessources ressourceManager;

	public AbstractFenetreInterne(Application appli, Action monAction, String nom) {
		super(nom, true, true, true, true);
		this.action = monAction;
		this.setDefaultCloseOperation(1);
		this.addInternalFrameListener(new AbstractFenetreInterne.EcouteurFenetre());
		this.ressourceManager = GestionnaireRessources.getRessourceManager();
		this.ressourceManager.addTraduisible(this);
	}

	private class EcouteurFenetre extends InternalFrameAdapter {
		public void internalFrameClosing(InternalFrameEvent ife) {
			AbstractFenetreInterne.this.action.setEnabled(true);
		}

		public void internalFrameActivated(InternalFrameEvent ife) {
			AbstractFenetreInterne.this.action.setEnabled(false);
		}
	}
}