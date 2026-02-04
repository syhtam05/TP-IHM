package edu.mermet.tp5.fenetres;

import edu.mermet.tp5.Application;
import edu.mermet.tp5.Ressources;
import edu.mermet.tp5.actions.AbstractActionTraduisible;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreConversion extends AbstractFenetreInterne {
	private JTextField champCelsius;
	private JTextField champFarenheit;
	private JButton boutonConvertir;
	private Action actionConvertir;
	private boolean celsiusAFocus;

	public FenetreConversion(Application appli, Action action) {
		super(appli, action, "Conversion celsius/Farenheit");
		this.setTitle(this.ressourceManager.getString(Ressources.FEN_CONV_TITRE));
		this.setSize(new Dimension(100, 50));
		this.setLayout(new GridLayout(3, 1));
		JPanel ligneCelsius = new JPanel();
		ligneCelsius.setLayout(new FlowLayout(4));
		JLabel labCelsius = new JLabel("Celsius :");
		this.champCelsius = new JTextField(15);
		labCelsius.setLabelFor(this.champCelsius);
		ligneCelsius.add(labCelsius);
		ligneCelsius.add(this.champCelsius);
		this.add(ligneCelsius);
		this.celsiusAFocus = true;
		this.champCelsius.addFocusListener(new FenetreConversion.EcouteurFocus(true));
		JPanel ligneFarenheit = new JPanel();
		ligneFarenheit.setLayout(new FlowLayout(4));
		JLabel labFarenheit = new JLabel("Farenheit :");
		this.champFarenheit = new JTextField(15);
		labFarenheit.setLabelFor(this.champFarenheit);
		ligneFarenheit.add(labFarenheit);
		ligneFarenheit.add(this.champFarenheit);
		this.add(ligneFarenheit);
		this.champFarenheit.addFocusListener(new FenetreConversion.EcouteurFocus(false));
		JPanel ligneValider = new JPanel();
		ligneValider.setLayout(new FlowLayout(1));
		this.actionConvertir = new FenetreConversion.ActionConvertir(appli);
		this.boutonConvertir = new JButton(this.actionConvertir);
		ligneValider.add(this.boutonConvertir);
		this.add(ligneValider);
		this.pack();
		this.getRootPane().setDefaultButton(this.boutonConvertir);
	}

	public void traduire() {
		this.setTitle(this.ressourceManager.getString(Ressources.FEN_CONV_TITRE));
	}

	private class ActionConvertir extends AbstractActionTraduisible {
		public ActionConvertir(Application appli) {
			super(Ressources.FEN_CONV_CONVERTIR, appli);
		}

		public void actionPerformed(ActionEvent ae) {
			double tempCelsius = 0.0D;
			double tempFarenheit = 0.0D;
			if (FenetreConversion.this.celsiusAFocus) {
				try {
					tempCelsius = Double.parseDouble(FenetreConversion.this.champCelsius.getText());
					tempFarenheit = 1.8D * tempCelsius + 32.0D;
					FenetreConversion.this.champFarenheit.setText(String.valueOf(tempFarenheit));
				} catch (NumberFormatException var8) {
					FenetreConversion.this.champFarenheit
							.setText(FenetreConversion.this.ressourceManager.getString(Ressources.FEN_CONV_ERR_FORMAT));
				}
			} else {
				try {
					tempFarenheit = Double.parseDouble(FenetreConversion.this.champFarenheit.getText());
					tempCelsius = (tempFarenheit - 32.0D) * 5.0D / 9.0D;
					FenetreConversion.this.champCelsius.setText(String.valueOf(tempCelsius));
				} catch (NumberFormatException var7) {
					FenetreConversion.this.champCelsius
							.setText(FenetreConversion.this.ressourceManager.getString(Ressources.FEN_CONV_ERR_FORMAT));
				}
			}

		}
	}

	private class EcouteurFocus implements FocusListener {
		private boolean aStocker;

		public EcouteurFocus(boolean b) {
			this.aStocker = b;
		}

		public void focusGained(FocusEvent fe) {
			FenetreConversion.this.celsiusAFocus = this.aStocker;
		}

		public void focusLost(FocusEvent fe) {
		}
	}
}