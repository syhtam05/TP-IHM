package I;

import javax.swing.*;

public class Fenetre extends JFrame{
    public Fenetre() {
        super("ma fenêtre 1");
        init();
        setSize(600, 300);
        // centre la fenetre
        setLocationRelativeTo(null);

        // fenetre unique -> fermer la fenetre quitte l'application
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // rendre la fenetre visible
        setVisible(true);
    }

    private void init(){
    }
}