package III.exo1;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Fenetre4 extends JFrame {
    public Fenetre4() {
        super("ma fenetre 4");
        init();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        JPanel contenu = (JPanel)getContentPane();
        JLabel texte = new JLabel("Mont-Saint-Michel");
        contenu.add(texte, BorderLayout.NORTH);
        ImageIcon image = null;

        try {
            URI uri = new URI("https://i0.wp.com/welltravelled.com.au/wp-content/uploads/2018/10/mont-st-michel-800x600.jpg?fit=800%2C600&ssl=1");
            URL url = uri.toURL();
            image = new ImageIcon(url);
        } catch (URISyntaxException | MalformedURLException e) {
            System.err.println("Image non trouvé");
        }

        JLabel labelImage = new JLabel(image);
        contenu.add(labelImage, BorderLayout.SOUTH);
    }
}
