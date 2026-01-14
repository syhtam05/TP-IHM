package II;

import javax.swing.*;

public class Fenetre3 extends JFrame {
    public Fenetre3() {
        super("ma fenetre 3");
        init();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        JPanel panneau =  new JPanel();
        JLabel texte = new JLabel("texte dans un JPanal");
        panneau.add(texte);
        setContentPane(panneau);
    }
}
