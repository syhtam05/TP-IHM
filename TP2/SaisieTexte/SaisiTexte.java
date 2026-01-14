package SaisieTexte;

import javax.swing.*;
import java.awt.*;

public class SaisiTexte extends JFrame {
	
	public SaisiTexte() {
        setTitle("Interface de Saisie");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblBonjour = new JLabel("Bonjour", SwingConstants.CENTER);
        lblBonjour.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblBonjour, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(new JLabel("Login :"), gbc);
        gbc.gridx = 1;
        centerPanel.add(new JTextField(15), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(new JLabel("Mot de passe :"), gbc);
        gbc.gridx = 1;
        centerPanel.add(new JPasswordField(15), gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        centerPanel.add(new JLabel("Anecdote :"), gbc);
        
        JTextArea txtAnecdote = new JTextArea(5, 15);
        txtAnecdote.setLineWrap(true);
        txtAnecdote.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtAnecdote);
        
        gbc.gridx = 1;
        centerPanel.add(scrollPane, gbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnQuestions = new JButton("Questions");
        JButton btnQuitter = new JButton("Quitter");

        btnQuitter.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnQuestions);
        buttonPanel.add(btnQuitter);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            new SaisiTexte().setVisible(true);
        });

	}

}
