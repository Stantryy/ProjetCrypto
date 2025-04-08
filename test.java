import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cryptage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null); // centre la fenêtre

        try {
            // Charge l'image de fond
            Image backgroundImage = ImageIO.read(new File("matix.jpg"));

            // JPanel personnalisé pour afficher l'image
            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            };

            backgroundPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // marges internes

            Color labelColor = new Color(255, 0, 0); // rouge vif
            int longTextF=15;
            // === Ligne 0 ===
            gbc.gridx = 0; gbc.gridy = 0;
            JLabel jl1 = new JLabel("Mot à crypter :");
            jl1.setForeground(labelColor);
            backgroundPanel.add(jl1, gbc);

            gbc.gridx = 1;
            JLabel jl2 = new JLabel("");
            jl2.setForeground(labelColor);
            backgroundPanel.add(jl2, gbc);

            gbc.gridx = 2;
            JLabel jl3 = new JLabel("Résultat :");
            jl3.setForeground(labelColor);
            backgroundPanel.add(jl3, gbc);

            // === Ligne 1 ===
            gbc.gridy = 1;
            gbc.gridx = 0;
            JTextField zoneTxtACrypter = new JTextField(longTextF);
            backgroundPanel.add(zoneTxtACrypter, gbc);

            gbc.gridx = 1;
            JButton jbCrypte = new JButton("Crypter");
            backgroundPanel.add(jbCrypte, gbc);

            gbc.gridx = 2;
            JTextField zoneTxtMotCrypter = new JTextField(longTextF);
            backgroundPanel.add(zoneTxtMotCrypter, gbc);

            // === Ligne 2 ===
            gbc.gridy = 2;
            gbc.gridx = 0;
            JLabel jl4 = new JLabel("Mot à décrypter :");
            jl4.setForeground(labelColor);
            backgroundPanel.add(jl4, gbc);

            gbc.gridx = 1;
            JLabel vide2 = new JLabel("");
            backgroundPanel.add(vide2, gbc);

            gbc.gridx = 2;
            JLabel jl5 = new JLabel("Résultat :");
            jl5.setForeground(labelColor);
            backgroundPanel.add(jl5, gbc);

            // === Ligne 3 ===
            gbc.gridy = 3;
            gbc.gridx = 0;
            JTextField zoneTxtMotADecrypter = new JTextField(longTextF);
            backgroundPanel.add(zoneTxtMotADecrypter, gbc);

            gbc.gridx = 1;
            JButton jbDecrypte = new JButton("Décrypter");
            backgroundPanel.add(jbDecrypte, gbc);

            gbc.gridx = 2;
            JTextField zoneTxtMotDecrypter = new JTextField(longTextF);
            backgroundPanel.add(zoneTxtMotDecrypter, gbc);

            // === Ligne 4 ===
            gbc.gridy = 4;
            gbc.gridx = 0;
            JLabel jl6 = new JLabel("Deck :");
            jl6.setForeground(labelColor);
            backgroundPanel.add(jl6, gbc);

            gbc.gridx = 1;
            JLabel jl7 = new JLabel("Clé :");
            jl7.setForeground(labelColor);
            backgroundPanel.add(jl7, gbc);

            // === Ligne 5 ===
            int[] deck;
            gbc.gridy = 5;
            gbc.gridx = 0;
            JTextField zoneDeck = new JTextField(longTextF);
            importDeck(zoneDeck);
            backgroundPanel.add(zoneDeck, gbc);

            gbc.gridx = 1;
            JTextField jtfCle = new JTextField(longTextF);
            backgroundPanel.add(jtfCle, gbc);

            // Ajout du panel au frame
            frame.setContentPane(backgroundPanel);
            frame.setVisible(true);
            gbc.gridx = 0;
            gbc.gridy = 6;
            JButton jbShuffleDeck = new JButton("mélanger le deck");
            backgroundPanel.add(jbShuffleDeck, gbc);



            jbCrypte.addActionListener(e -> {
                String texteACrypter = zoneTxtACrypter.getText();
                
                String resultat=cryptage.CryptageAvecDeck(texteACrypter,stringToDeck(zoneDeck.getText()));
                zoneTxtMotCrypter.setText(resultat);
            });
            jbDecrypte.addActionListener(e -> {
                String texteADecrypter = zoneTxtMotADecrypter.getText();
                
                String resultat=cryptage.DecryptageAvecDeck(texteADecrypter,stringToDeck(zoneDeck.getText()));
                zoneTxtMotDecrypter.setText(resultat);
            });

            jbShuffleDeck.addActionListener(e -> {
                importDeck(zoneDeck);
            });
            

            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int[] importDeck(JTextField zoneTxtMotCrypter){
        int[] decktempo=cryptage.genDeck();
        String deckString=deckToString(decktempo);
        zoneTxtMotCrypter.setText("");
        zoneTxtMotCrypter.setText(deckString);


        return decktempo;
    }

    public static String deckToString(int[] deck) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deck.length; i++) {
            sb.append(deck[i]);
            if (i < deck.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static int[] stringToDeck(String deckString) {
        String[] parts = deckString.split(",");
        int[] deck2 = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            deck2[i] = Integer.parseInt(parts[i].trim());
        }
        return deck2;
    }
    
    
}
