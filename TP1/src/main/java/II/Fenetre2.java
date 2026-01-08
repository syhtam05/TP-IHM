package II;

import javax.swing.*;
import java.awt.*;

public class Fenetre2 extends JFrame {
    public Fenetre2() {
        super("ma fenetre 2");
        init();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        JLabel texte = new JLabel("Ceci est ma deuxieme fenetre");
        getContentPane().add(texte, BorderLayout.CENTER);
    }
}
