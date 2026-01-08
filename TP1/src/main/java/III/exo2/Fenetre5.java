package III.exo2;

import javax.swing.*;
import java.util.stream.IntStream;

public class Fenetre5 extends JFrame {
    public Fenetre5() {
        super("ma fenetre 5");
        init();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        JPanel panel = new JPanel();
        setContentPane(panel);
        IntStream.iterate(1, i -> i + 1)
                .limit(5)
                .mapToObj(n -> new JButton("Bouton " + n))
                .forEach(bouton -> panel.add(bouton));
    }
}
