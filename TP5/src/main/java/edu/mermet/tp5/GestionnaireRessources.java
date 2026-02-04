package edu.mermet.tp5;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GestionnaireRessources {
	private Locale lieu;
	private ResourceBundle bundle;
	private List<Traduisible> traduisibles = new ArrayList();
	private static GestionnaireRessources gestionnaire = null;

	private GestionnaireRessources() {
		this.definirLieu(Locale.getDefault());
	}

	public static GestionnaireRessources getRessourceManager() {
		if (gestionnaire == null) {
			gestionnaire = new GestionnaireRessources();
		}

		return gestionnaire;
	}

	public String getString(Ressources ressource) {
		return this.bundle.getString(ressource.getNom());
	}

	public void definirLieu(Locale leLieu) {
		PrintStream var10000 = System.err;
		String var10001 = String.valueOf(this.lieu);
		var10000.println("changement de langue on passe de " + var10001 + " à " + String.valueOf(leLieu));
		this.lieu = leLieu;
		this.bundle = ResourceBundle.getBundle("textes", this.lieu);
		this.fireTraduire();
	}

	public Locale getLieu() {
		return this.lieu;
	}

	public void addTraduisible(Traduisible trad) {
		this.traduisibles.add(trad);
	}

	public void removeTraduisible(Traduisible trad) {
		this.traduisibles.remove(trad);
	}

	private void fireTraduire() {
		Iterator var2 = this.traduisibles.iterator();

		while (var2.hasNext()) {
			Traduisible trad = (Traduisible) var2.next();
			trad.traduire();
		}

	}
}