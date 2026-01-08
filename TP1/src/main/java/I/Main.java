package I;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () ->{
                    Fenetre fenetre = new Fenetre();
                    Dialogue1 dialogue = new Dialogue1(fenetre);
                });

    }
}
