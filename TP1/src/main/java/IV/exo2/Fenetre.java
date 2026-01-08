package IV.exo2;

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
        initCentre();
        initSud();
    }

    private void initNord() {
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);
        panel.add(new JButton("Fenetre 1"));
        panel.add(Box.createGlue());
        panel.add(new JButton("Fenetre 2"));
        getContentPane().add(panel, BorderLayout.NORTH);
    }

    private void initCentre() {
        JPanel panel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 1);
        panel.setLayout(gridLayout);
        JPanel boutonHaute = new JPanel();
        BoxLayout layoutBouttons = new BoxLayout(boutonHaute, BoxLayout.Y_AXIS);
        boutonHaute.setLayout(layoutBouttons);
        JPanel butonEnterne = new JPanel();
        boutonHaute.add(new JButton("Bouton 3"));
        boutonHaute.add(new JButton("Bouton 4"));
        panel.add(boutonHaute);
        getContentPane().add(panel, BorderLayout.CENTER);
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
