package edu.mermet.tp5;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;

import edu.mermet.tp5.actions.ActionAfficherBoutons;
import edu.mermet.tp5.actions.ActionAfficherConversion;
import edu.mermet.tp5.actions.ActionAfficherDiaporama;
import edu.mermet.tp5.actions.ActionAfficherTexte;
import edu.mermet.tp5.actions.ActionLangue;
import edu.mermet.tp5.actions.ActionQuitter;
import edu.mermet.tp5.fenetres.AbstractFenetreInterne;
import edu.mermet.tp5.fenetres.FenetreAide;
import edu.mermet.tp5.fenetres.FenetreBoutons;
import edu.mermet.tp5.fenetres.FenetreConversion;
import edu.mermet.tp5.fenetres.FenetreDiaporama;
import edu.mermet.tp5.fenetres.FenetreTexte;

/**
 *
 * @author brunomermet
 */
public class Application extends JFrame {
	AbstractFenetreInterne conversion;
	AbstractFenetreInterne texte;
	AbstractFenetreInterne diaporama;
	AbstractFenetreInterne boutons;
	private Action actionQuitter;
	private Action actionAfficherConversion;
	private Action actionAfficherTexte;
	private Action actionAfficherDiaporama;
	private Action actionAfficherBoutons;
	private ActionLangue actionLangueParDefaut;
	private ActionLangue actionLangueFrancais;
	private ActionLangue actionLangueAnglais;
	private JMenu menuFichier;
	private JMenu menuApplications;
	private JMenu menuLangues;
	private JMenuItem itemConversion;
	private JMenuItem itemTexte;
	private JMenuItem itemDiaporama;
	private JMenuItem itemBoutons;
	private int competenceUtilisateur = 4;

	public Application() {
		setTitle("Multi-Window");
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 300);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initActions() {
		actionQuitter = new ActionQuitter(this);
		actionAfficherConversion = new ActionAfficherConversion(this);
		actionAfficherTexte = new ActionAfficherTexte(this);
		actionAfficherDiaporama = new ActionAfficherDiaporama(this);
		actionAfficherBoutons = new ActionAfficherBoutons(this);
	}

	private void init() {
		initActions();
		initMenus();
		initFenetres();
	}

	private void initFenetres() {
		this.setContentPane(new JDesktopPane());
		// ------ fenêtre conversion ------
		conversion = new FenetreConversion(this, actionAfficherConversion);
		this.add(conversion);
		// ------ fenêtre texte ------
		texte = new FenetreTexte(this, actionAfficherTexte);
		this.add(texte);
		// ------ fenêtre diaporama ------
		diaporama = new FenetreDiaporama(this, actionAfficherDiaporama);
		this.add(diaporama);
		// ------ fenêtre boutons ------
		boutons = new FenetreBoutons(this, actionAfficherBoutons);
		this.add(boutons);
	}

	private void initMenus() {
		JMenuBar barre = new JMenuBar();
		// ------ menu Fichier ------
		menuFichier = new JMenu("Fichier");
		menuFichier.setMnemonic(KeyEvent.VK_F);

		JMenuItem quitter = new JMenuItem(actionQuitter);
		menuFichier.add(quitter);
		barre.add(menuFichier);
		this.setJMenuBar(barre);

		// ------ menu Applications ------
		menuApplications = new JMenu("Applications");
		menuApplications.setMnemonic(KeyEvent.VK_A);

		// On enlève "JMenuItem" devant car ce sont maintenant des attributs de classe
		itemConversion = new JMenuItem(actionAfficherConversion);
		menuApplications.add(itemConversion);

		itemTexte = new JMenuItem(actionAfficherTexte);
		menuApplications.add(itemTexte);

		itemDiaporama = new JMenuItem(actionAfficherDiaporama);
		menuApplications.add(itemDiaporama);

		itemBoutons = new JMenuItem(actionAfficherBoutons);
		menuApplications.add(itemBoutons);

		barre.add(menuApplications);

		// N'oubliez pas d'appeler la gestion de compétence à la fin
		// ou dans le constructeur après init()
		appliquerCompetence();
	}

	public void enableConversion(boolean b) {
		actionAfficherConversion.setEnabled(b);
	}

	public void enableTexte(boolean b) {
		actionAfficherTexte.setEnabled(b);
	}

	public void enableDiaporama(boolean b) {
		actionAfficherDiaporama.setEnabled(b);
	}

	public void enableBoutons(boolean b) {
		actionAfficherBoutons.setEnabled(b);
	}

	public Action getActionAfficherConversion() {
		return actionAfficherConversion;
	}

	public Action getActionAfficherTexte() {
		return actionAfficherTexte;
	}

	public Action getActionAfficherDiaporama() {
		return actionAfficherDiaporama;
	}

	public void afficherBoutons() {
		boutons.setVisible(true);
	}

	public void afficherConversion() {
		conversion.setVisible(true);
	}

	public void afficherDiaporama() {
		diaporama.setVisible(true);
	}

	public void afficherTexte() {
		texte.setVisible(true);
	}

	private void appliquerCompetence() {
		// Quitter (1), Diaporama (2), Conversion (4), Texte (4), Boutons (8)
		itemDiaporama.setEnabled((2 & competenceUtilisateur) == 2 || competenceUtilisateur >= 2);
		itemConversion.setEnabled((4 & competenceUtilisateur) == 4 || competenceUtilisateur >= 4);
		itemTexte.setEnabled((4 & competenceUtilisateur) == 4 || competenceUtilisateur >= 4);
		itemBoutons.setEnabled((8 & competenceUtilisateur) == 8 || competenceUtilisateur >= 8);
	}

}
