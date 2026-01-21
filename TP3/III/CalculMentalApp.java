package III;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Random;

public class CalculMentalApp {

    // --- PARAMÈTRES DU TEST ---
    static String operationType;
    static int maxValeur;
    static int nbQuestions;
    static boolean tempsLimite;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConfigDialog config = new ConfigDialog();
            if (config.isValide()) {
                new TestFrame();
            } else {
                System.exit(0);
            }
        });
    }

    // --- BOITE DE DIALOGUE DE CONFIGURATION ---
    static class ConfigDialog extends JDialog {
        private boolean valide = false;
        private JComboBox<String> comboOp;
        private JSlider sliderMax;
        private JSpinner spinnerNb;
        private JCheckBox cbTemps;

        public ConfigDialog() {
            setTitle("Paramètres du Test");
            setModal(true);
            setLayout(new GridLayout(5, 2, 10, 10));

            // 1. Opérations
            add(new JLabel(" Opération :"));
            comboOp = new JComboBox<>(new String[]{"Addition", "Soustraction", "Multiplication", "Mélange"});
            add(comboOp);

            // 2. Valeur Max avec Slider et Champ non éditable
            add(new JLabel(" Valeur Max :"));
            JPanel panSlider = new JPanel(new BorderLayout());
            JTextField txtMax = new JTextField("25", 3);
            txtMax.setEditable(false);
            sliderMax = new JSlider(2, 50, 25);
            sliderMax.addChangeListener(e -> txtMax.setText(String.valueOf(sliderMax.getValue())));
            panSlider.add(sliderMax, BorderLayout.CENTER);
            panSlider.add(txtMax, BorderLayout.EAST);
            add(panSlider);

            // 3. Nombre de questions
            add(new JLabel(" Nb Questions :"));
            spinnerNb = new JSpinner(new SpinnerNumberModel(10, 1, 20, 1));
            add(spinnerNb);

            // 4. Temps limité
            add(new JLabel(" Temps limité (15s) :"));
            cbTemps = new JCheckBox();
            add(cbTemps);

            // Bouton de validation
            JButton btnLancer = new JButton("Lancer le test");
            btnLancer.addActionListener(e -> {
                operationType = (String) comboOp.getSelectedItem();
                maxValeur = sliderMax.getValue();
                nbQuestions = (int) spinnerNb.getValue();
                tempsLimite = cbTemps.isSelected();
                valide = true;
                dispose();
            });
            add(new JLabel()); // vide
            add(btnLancer);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public boolean isValide() { return valide; }
    }

    // --- FENÊTRE PRINCIPALE DU TEST ---
    static class TestFrame extends JFrame {
        private int questionActuelle = 1;
        private double scoreCumule = 0;
        private int nbBonnesReponses = 0;
        
        private JLabel lblScore, lblOperation;
        private JProgressBar barreProgression, barreTemps;
        private JTextField txtReponse;
        private int resultatAttendu;
        private Random random = new Random();

        public TestFrame() {
            setTitle("Calcul Mental");
            setSize(450, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout(10, 10));

            // Haut : Score
            lblScore = new JLabel("Score: 0  ", SwingConstants.RIGHT);
            add(lblScore, BorderLayout.NORTH);

            // Milieu : Question et Saisie
            JPanel panCentral = new JPanel(new GridLayout(3, 1));
            lblOperation = new JLabel("", SwingConstants.CENTER);
            lblOperation.setFont(new Font("Arial", Font.BOLD, 40));
            
            txtReponse = new JTextField();
            txtReponse.setFont(new Font("Arial", Font.PLAIN, 24));
            txtReponse.setHorizontalAlignment(JTextField.CENTER);
            
            JButton btnOk = new JButton("OK");
            getRootPane().setDefaultButton(btnOk); // Bouton par défaut

            panCentral.add(lblOperation);
            panCentral.add(txtReponse);
            panCentral.add(btnOk);
            add(panCentral, BorderLayout.CENTER);

            // Bas : Progressions
            JPanel panBas = new JPanel(new GridLayout(2, 1));
            barreProgression = new JProgressBar(0, nbQuestions);
            barreTemps = new JProgressBar(0, 15);
            barreTemps.setForeground(Color.ORANGE);
            panBas.add(new JLabel("Progression totale :"));
            panBas.add(barreProgression);
            // On affiche la barre de temps même si non implantée (bonus visuel)
            if(tempsLimite) {
                panBas.add(new JLabel("Temps question :"));
                panBas.add(barreTemps);
            }
            add(panBas, BorderLayout.SOUTH);

            btnOk.addActionListener(e -> validerReponse());

            genererQuestion();
            setLocationRelativeTo(null);
            setVisible(true);
        }

        private void genererQuestion() {
            if (questionActuelle > nbQuestions) {
                terminerTest();
                return;
            }

            int a = random.nextInt(maxValeur) + 1;
            int b = random.nextInt(maxValeur) + 1;
            String op = "";
            
            String choix = operationType;
            if (choix.equals("Mélange")) {
                String[] ops = {"Addition", "Soustraction", "Multiplication"};
                choix = ops[random.nextInt(3)];
            }

            switch (choix) {
                case "Addition" -> { op = "+"; resultatAttendu = a + b; }
                case "Soustraction" -> { 
                    if (a < b) { int temp = a; a = b; b = temp; } // Eviter négatifs
                    op = "-"; resultatAttendu = a - b; 
                }
                case "Multiplication" -> { op = "x"; resultatAttendu = a * b; }
            }

            lblOperation.setText(a + " " + op + " " + b + " = ?");
            txtReponse.setText("");
            txtReponse.requestFocus();
            barreProgression.setValue(questionActuelle - 1);
        }

        private void validerReponse() {
            try {
                int rep = Integer.parseInt(txtReponse.getText());
                if (rep == resultatAttendu) {
                    nbBonnesReponses++;
                    scoreCumule += 1.0; // Logique simplifiée (hors temps limité)
                }
                lblScore.setText("Score: " + nbBonnesReponses + "  ");
                questionActuelle++;
                genererQuestion();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir un nombre entier.");
            }
        }

        private void terminerTest() {
            double pourcentage = (double) nbBonnesReponses / nbQuestions * 100;
            JOptionPane.showMessageDialog(this, 
                "Test terminé !\nVotre score final : " + String.format("%.1f", pourcentage) + "%",
                "Résultat", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}
