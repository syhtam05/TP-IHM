package IV.exo1;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    public Fenetre() {
        super("ma fenetre 4");
        init();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        initNord();
        initSud();
    }

    private void initNord() {
        JPanel panel = new JPanel();
        panel.add(new JButton("Fenetre 1"));
        panel.add(new JButton("Fenetre 2"));
        panel.add(new JButton("Fenetre 3"));
        getContentPane().add(panel, BorderLayout.NORTH);
    }

    private void initSud() {
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        panel.add(new JButton("Fenetre 4"));
        panel.add(new JButton("Fenetre 5"));
        getContentPane().add(panel, BorderLayout.SOUTH);
    }
}
