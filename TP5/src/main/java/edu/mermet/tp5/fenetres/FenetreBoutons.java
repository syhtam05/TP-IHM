package edu.mermet.tp5.fenetres;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.Ressources;
import java.awt.FlowLayout;
import javax.swing.Action;
import javax.swing.JButton;

public class FenetreBoutons extends AbstractFenetreInterne {
	private JButton boutonTexte;
	private JButton boutonDiaporama;
	private JButton boutonDegres;

	public FenetreBoutons(Application appli, Action action) {
		super(appli, action, "Boutons");
		this.setTitle(this.ressourceManager.getString(Ressources.FEN_BOUTONS_TITRE));
		this.setLayout(new FlowLayout());
		this.boutonTexte = new JButton(appli.getActionAfficherTexte());
		this.boutonDiaporama = new JButton(appli.getActionAfficherDiaporama());
		this.boutonDegres = new JButton(appli.getActionAfficherConversion());
		this.add(this.boutonDegres);
		this.add(this.boutonTexte);
		this.add(this.boutonDiaporama);
		this.pack();
	}

	public void traduire() {
		this.setTitle(this.ressourceManager.getString(Ressources.FEN_BOUTONS_TITRE));
	}
}