package edu.mermet.tp5.fenetres;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.Ressources;
import edu.mermet.tp5.actions.AbstractActionTraduisible;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class FenetreTexte extends AbstractFenetreInterne {
	private JCheckBox gras;
	private JCheckBox rouge;
	private Action actionGras;
	private Action actionRouge;
	private JTextArea texte;
	private JMenu style;

	public FenetreTexte(Application appli, Action action) {
		super(appli, action, "Texte");
		this.setTitle(this.ressourceManager.getString(Ressources.FEN_TEXTE_TITRE));
		this.actionGras = new FenetreTexte.ActionGras(appli);
		this.gras = new JCheckBox(this.actionGras);
		this.actionRouge = new FenetreTexte.ActionRouge(appli);
		this.rouge = new JCheckBox(this.actionRouge);
		JPanel panneauBouton = new JPanel();
		panneauBouton.add(this.gras);
		panneauBouton.add(this.rouge);
		this.add(panneauBouton, "North");
		this.texte = new JTextArea(6, 20);
		this.texte.setLineWrap(true);
		this.texte.setWrapStyleWord(true);
		JScrollPane panneauTexte = new JScrollPane(this.texte);
		this.add(panneauTexte, "Center");
		JMenuBar barre = new JMenuBar();
		this.style = new JMenu(this.ressourceManager.getString(Ressources.FEN_TEXTE_STYLE));
		JMenuItem itemGras = new JCheckBoxMenuItem(this.actionGras);
		this.style.add(itemGras);
		JMenuItem itemRouge = new JCheckBoxMenuItem(this.actionRouge);
		this.style.add(itemRouge);
		barre.add(this.style);
		this.setJMenuBar(barre);
		this.pack();
	}

	public void traduire() {
		this.setTitle(this.ressourceManager.getString(Ressources.FEN_TEXTE_TITRE));
		this.style.setText(this.ressourceManager.getString(Ressources.FEN_TEXTE_STYLE));
	}

	private class ActionGras extends AbstractActionTraduisible {
		private boolean gras = false;

		public ActionGras(Application appli) {
			super(Ressources.FEN_TEXTE_GRAS, appli);
			this.putValue("AcceleratorKey", KeyStroke.getKeyStroke(71, 128));
			this.putValue("SwingSelectedKey", false);
		}

		public void actionPerformed(ActionEvent ae) {
			Font police = FenetreTexte.this.texte.getFont();
			if (!this.gras) {
				police = police.deriveFont(1);
			} else {
				police = police.deriveFont(0);
			}

			this.gras = !this.gras;
			this.putValue("SwingSelectedKey", this.gras);
			FenetreTexte.this.texte.setFont(police);
		}
	}

	private class ActionRouge extends AbstractActionTraduisible {
		private boolean rouge = false;

		public ActionRouge(Application appli) {
			super(Ressources.FEN_TEXTE_ROUGE, appli);
			this.putValue("AcceleratorKey", KeyStroke.getKeyStroke(82, 128));
			this.putValue("SwingSelectedKey", false);
		}

		public void actionPerformed(ActionEvent ae) {
			if (!this.rouge) {
				FenetreTexte.this.texte.setForeground(Color.RED);
			} else {
				FenetreTexte.this.texte.setForeground(Color.BLACK);
			}

			this.rouge = !this.rouge;
		}
	}
}