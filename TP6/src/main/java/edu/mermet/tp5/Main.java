package edu.mermet.tp5;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        String user = (args.length > 0) ? args[0] : null;
        SwingUtilities.invokeLater(() -> new Application(user));
    }
}
