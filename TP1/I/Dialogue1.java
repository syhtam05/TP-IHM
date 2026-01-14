package I;

import javax.swing.*;

public class Dialogue1 extends JDialog {
    public Dialogue1(JFrame fenetre) {
        super(fenetre, "dialogue 1", true);
        init();
        setSize(300, 400);
        setLocationRelativeTo(fenetre);
        setVisible(true);
    }

    private void init() {

    }
}
