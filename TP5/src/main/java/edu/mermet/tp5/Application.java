package edu.mermet.tp5;

import edu.mermet.tp5.actions.ActionAfficherBoutons;
import edu.mermet.tp5.actions.ActionAfficherConversion;
import edu.mermet.tp5.actions.ActionAfficherDiaporama;
import edu.mermet.tp5.actions.ActionAfficherTexte;
import edu.mermet.tp5.actions.ActionLangue;
import edu.mermet.tp5.actions.ActionQuitter;
import edu.mermet.tp5.fenetres.AbstractFenetreInterne;
import edu.mermet.tp5.fenetres.FenetreBoutons;
import edu.mermet.tp5.fenetres.FenetreConversion;
import edu.mermet.tp5.fenetres.FenetreDiaporama;
import edu.mermet.tp5.fenetres.FenetreTexte;
import java.awt.Component;
import java.util.Locale;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Application extends JFrame implements Traduisible {
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
	GestionnaireRessources gestionnaireRessources;

	public Application() {
		this.init();
		this.setDefaultCloseOperation(3);
		this.setSize(600, 300);
		this.setLocationRelativeTo((Component) null);
		this.setVisible(true);
	}

	private void initActions() {
		this.actionQuitter = new ActionQuitter(this);
		this.actionAfficherConversion = new ActionAfficherConversion(this);
		this.actionAfficherTexte = new ActionAfficherTexte(this);
		this.actionAfficherDiaporama = new ActionAfficherDiaporama(this);
		this.actionAfficherBoutons = new ActionAfficherBoutons(this);
		this.actionLangueParDefaut = new ActionLangue(this, Ressources.LANGUE_DEFAUT, Locale.getDefault());
		this.actionLangueFrancais = new ActionLangue(this, Ressources.LANGUE_FRANCAIS, new Locale("fr", "FR"));
		this.actionLangueAnglais = new ActionLangue(this, Ressources.LANGUE_ANGLAIS, new Locale("en", "GB"));
	}

	private void init() {
		this.gestionnaireRessources = GestionnaireRessources.getRessourceManager();
		this.gestionnaireRessources.addTraduisible(this);
		this.setTitle(this.gestionnaireRessources.getString(Ressources.TITRE));
		this.initActions();
		this.initMenus();
		this.initFenetres();
	}

	private void initFenetres() {
		this.setContentPane(new JDesktopPane());
		this.conversion = new FenetreConversion(this, this.actionAfficherConversion);
		this.add(this.conversion);
		this.texte = new FenetreTexte(this, this.actionAfficherTexte);
		this.add(this.texte);
		this.diaporama = new FenetreDiaporama(this, this.actionAfficherDiaporama);
		this.add(this.diaporama);
		this.boutons = new FenetreBoutons(this, this.actionAfficherBoutons);
		this.add(this.boutons);
	}

	private void initMenus() {
		JMenuBar barre = new JMenuBar();
		this.menuFichier = new JMenu(this.gestionnaireRessources.getString(Ressources.MENU_FICHIER));
		this.menuFichier.setMnemonic(70);
		JMenuItem quitter = new JMenuItem(this.actionQuitter);
		this.menuFichier.add(quitter);
		barre.add(this.menuFichier);
		this.setJMenuBar(barre);
		this.menuApplications = new JMenu(this.gestionnaireRessources.getString(Ressources.MENU_APPLICATIONS));
		this.menuApplications.setMnemonic(65);
		JMenuItem itemConversion = new JMenuItem(this.actionAfficherConversion);
		this.menuApplications.add(itemConversion);
		JMenuItem itemTexte = new JMenuItem(this.actionAfficherTexte);
		this.menuApplications.add(itemTexte);
		JMenuItem itemDiaporama = new JMenuItem(this.actionAfficherDiaporama);
		this.menuApplications.add(itemDiaporama);
		JMenuItem itemBoutons = new JMenuItem(this.actionAfficherBoutons);
		this.menuApplications.add(itemBoutons);
		barre.add(this.menuApplications);
		this.creerMenuLangue(barre);
	}

	public void enableConversion(boolean b) {
		this.actionAfficherConversion.setEnabled(b);
	}

	public void enableTexte(boolean b) {
		this.actionAfficherTexte.setEnabled(b);
	}

	public void enableDiaporama(boolean b) {
		this.actionAfficherDiaporama.setEnabled(b);
	}

	public void enableBoutons(boolean b) {
		this.actionAfficherBoutons.setEnabled(b);
	}

	public Action getActionAfficherConversion() {
		return this.actionAfficherConversion;
	}

	public Action getActionAfficherTexte() {
		return this.actionAfficherTexte;
	}

	public Action getActionAfficherDiaporama() {
		return this.actionAfficherDiaporama;
	}

	private void creerMenuLangue(JMenuBar barre) {
		this.menuLangues = new JMenu(this.gestionnaireRessources.getString(Ressources.MENU_LANGUES));
		JMenuItem defaut = new JRadioButtonMenuItem(this.actionLangueParDefaut);
		this.menuLangues.add(defaut);
		JMenuItem francais = new JRadioButtonMenuItem(this.actionLangueFrancais);
		this.menuLangues.add(francais);
		JMenuItem anglais = new JRadioButtonMenuItem(this.actionLangueAnglais);
		this.menuLangues.add(anglais);
		ButtonGroup groupe = new ButtonGroup();
		groupe.add(defaut);
		groupe.add(francais);
		groupe.add(anglais);
		barre.add(this.menuLangues);
	}

	public void traduire() {
		this.setVisible(false);
		String titre = this.gestionnaireRessources.getString(Ressources.TITRE);
		System.out.println("nouveau titre = " + titre);
		this.setTitle(titre);
		this.menuFichier.setText(this.gestionnaireRessources.getString(Ressources.MENU_FICHIER));
		this.menuApplications.setText(this.gestionnaireRessources.getString(Ressources.MENU_APPLICATIONS));
		this.menuLangues.setText(this.gestionnaireRessources.getString(Ressources.MENU_LANGUES));
		this.setVisible(true);
	}

	public void afficherBoutons() {
		this.boutons.setVisible(true);
	}

	public void afficherConversion() {
		this.conversion.setVisible(true);
	}

	public void afficherDiaporama() {
		this.diaporama.setVisible(true);
	}

	public void afficherTexte() {
		this.texte.setVisible(true);
	}
}