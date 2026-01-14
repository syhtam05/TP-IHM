package SaisieTexte;

import javax.swing.*;
import java.awt.*;

public class SaisiTexte extends JFrame {
	
	public SaisiTexte() {
		// Configuration de base de la fenêtre
        setTitle("Interface de Saisie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        // Panel principal avec un BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. Label "Bonjour" en haut (Nord)
        JLabel lblBonjour = new JLabel("Bonjour", SwingConstants.CENTER);
        lblBonjour.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblBonjour, BorderLayout.NORTH);

        // 2. Zone centrale pour les saisies (GridBagLayout pour plus de contrôle)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Login
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(new JLabel("Login :"), gbc);
        gbc.gridx = 1;
        centerPanel.add(new JTextField(15), gbc);

        // Mot de passe
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(new JLabel("Mot de passe :"), gbc);
        gbc.gridx = 1;
        centerPanel.add(new JPasswordField(15), gbc);

        // Anecdote
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH; // Aligner le label en haut
        centerPanel.add(new JLabel("Anecdote :"), gbc);
        
        JTextArea txtAnecdote = new JTextArea(5, 15);
        txtAnecdote.setLineWrap(true);          // Retour à la ligne automatique
        txtAnecdote.setWrapStyleWord(true);     // Ne coupe pas les mots
        JScrollPane scrollPane = new JScrollPane(txtAnecdote);
        
        gbc.gridx = 1;
        centerPanel.add(scrollPane, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 3. Boutons en bas (Sud)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnQuestions = new JButton("Questions");
        JButton btnQuitter = new JButton("Quitter");

        // Action pour quitter
        btnQuitter.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnQuestions);
        buttonPanel.add(btnQuitter);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Ajout du panel à la fenêtre
        add(mainPanel);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            new SaisiTexte().setVisible(true);
        });

	}

}
