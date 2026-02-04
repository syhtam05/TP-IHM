package edu.mermet.tp5.fenetres;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.Ressources;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FenetreDiaporama extends AbstractFenetreInterne {
	ImageIcon[] images;
	String[] textes;
	JLabel affichage;
	FenetreDiaporama.Defilement defilement;
	private int indiceCourant = 0;

	public FenetreDiaporama(Application appli, Action action) {
		super(appli, action, "Diaporama");
		this.setTitle(this.ressourceManager.getString(Ressources.FEN_DIAPO_TITRE));
		this.images = new ImageIcon[3];

		try {
			this.images[0] = new ImageIcon((new ImageIcon(new URL(
					"http://bruno.mermet.pagesperso-orange.fr/Personnel/Anes/Randos/TourDeLaHague/10bocage.jpg")))
					.getImage().getScaledInstance(300, -1, 1));
			this.images[1] = new ImageIcon((new ImageIcon(new URL(
					"http://bruno.mermet.pagesperso-orange.fr/Personnel/Anes/Randos/TourDeLaHague/12baieEcalgrain.jpg")))
					.getImage().getScaledInstance(300, -1, 1));
			this.images[2] = new ImageIcon((new ImageIcon(
					new URL("http://bruno.mermet.pagesperso-orange.fr/Personnel/Anes/Randos/TourDeLaHague/15cote.jpg")))
					.getImage().getScaledInstance(300, -1, 1));
		} catch (MalformedURLException var5) {
			this.images[0] = null;
			this.images[1] = null;
			this.images[2] = null;
		}

		JPanel panneauTexte = new JPanel();
		this.affichage = new JLabel();
		panneauTexte.add(this.affichage);
		this.affichage.setIcon(this.images[0]);
		JScrollPane ascenseurs = new JScrollPane(this.affichage);
		this.add(ascenseurs, "Center");
		this.setSize(300, 300);
	}

	public void setVisible(boolean b) {
		super.setVisible(b);
		if (b) {
			this.defilement = new FenetreDiaporama.Defilement();
			Thread thread = new Thread(this.defilement);
			thread.start();
		} else if (this.defilement != null) {
			this.defilement.arreter();
		}

	}

	public void traduire() {
		this.setTitle(this.ressourceManager.getString(Ressources.FEN_DIAPO_TITRE));
	}

	class Defilement implements Runnable {
		private boolean arrete = false;

		public Defilement() {
		}

		public void run() {
			while (!this.arrete) {
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException var2) {
				}

				++FenetreDiaporama.this.indiceCourant;
				FenetreDiaporama var10000 = FenetreDiaporama.this;
				var10000.indiceCourant %= 3;
				FenetreDiaporama.this.affichage
						.setIcon(FenetreDiaporama.this.images[FenetreDiaporama.this.indiceCourant]);
			}

		}

		public void arreter() {
			this.arrete = true;
		}
	}
}