import javax.swing.*;
import java.awt.*;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cryptage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        // Création du layout GridBag
        GridBagLayout layout = new GridBagLayout();
        frame.setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;  // colonne 0
        gbc.gridy = 0;  // ligne 0
        frame.add(new JLabel("Mot à crypté :"), gbc);
        gbc.gridx = 1;  // colonne 0
        gbc.gridy = 0;  // ligne 0
        frame.add(new JLabel(""), gbc);
        gbc.gridx = 2;  // colonne 0
        gbc.gridy = 0;  // ligne 0
        frame.add(new JLabel("resultat"), gbc);

        gbc.gridx = 0;  // colonne 1
        gbc.gridy = 1;  // ligne 0
        frame.add(new JTextField(15), gbc);
        gbc.gridx = 1;  // colonne 1
        gbc.gridy = 1;  // ligne 0
        frame.add(new JButton("Crypter"), gbc);
        gbc.gridx = 2;  // colonne 1
        gbc.gridy = 1;  // ligne 0
        frame.add(new JTextField(15), gbc);

        gbc.gridx = 0;  // colonne 0
        gbc.gridy = 2;  // ligne 0
        frame.add(new JLabel("Mot à crypté :"), gbc);
        gbc.gridx = 1;  // colonne 0
        gbc.gridy = 2;  // ligne 0
        frame.add(new JLabel(""), gbc);
        gbc.gridx = 2;  // colonne 0
        gbc.gridy = 2;  // ligne 0
        frame.add(new JLabel("resultat"), gbc);

        gbc.gridx = 0;  // colonne 1
        gbc.gridy = 3;  // ligne 0
        frame.add(new JTextField(15), gbc);
        gbc.gridx = 1;  // colonne 1
        gbc.gridy = 3;  // ligne 0
        frame.add(new JButton("Crypter"), gbc);
        gbc.gridx = 2;  // colonne 1
        gbc.gridy = 3;  // ligne 0
        frame.add(new JTextField(15), gbc);

        // Affichage du frame
        frame.setVisible(true);
    }
}
