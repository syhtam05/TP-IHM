package III.exo3;

import javax.swing.*;
import java.util.stream.IntStream;

public class Fenetre6a extends JFrame {
    public Fenetre6a() {
        super("ma fenetre 6a");
        init();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        JButton[] btns = IntStream.iterate(1, n -> n + 1)
                .limit(3)
                .mapToObj(n -> new JButton("Bouton " + n))
                .toArray(JButton[]::new);
        JPanel panneau = new JPanel();
        BoxLayout layout = new BoxLayout(panneau, BoxLayout.X_AXIS);
        panneau.setLayout(layout);
        setContentPane(panneau);
        panneau.add(Box.createGlue());
        panneau.add(btns[0]);
        panneau.add(btns[1]);
        panneau.add(btns[2]);
        panneau.add(Box.createGlue());
    }
}
