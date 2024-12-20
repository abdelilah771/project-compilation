package mohsabde;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParseurAppSwing {

    public static void main(String[] args) {
        // Appliquer un Look and Feel moderne (Nimbus)
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Créer la fenêtre principale
        JFrame frame = new JFrame("Analyseur de phrases");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Panel principal avec une bordure pour l'espacement
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Création des composants
        JLabel instructionLabel = new JLabel("Entrez une phrase :");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField phraseField = new JTextField(20);
        phraseField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton analyseButton = new JButton("Analyser");
        analyseButton.setFont(new Font("Arial", Font.BOLD, 14));
        analyseButton.setBackground(new Color(100, 149, 237));
        analyseButton.setForeground(Color.WHITE);
        analyseButton.setFocusPainted(false);

        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Panel pour le champ de saisie et le bouton
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
        inputPanel.add(instructionLabel, BorderLayout.NORTH);
        inputPanel.add(phraseField, BorderLayout.CENTER);
        inputPanel.add(analyseButton, BorderLayout.EAST);

        // Panel pour afficher les résultats
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout(5, 5));
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Ajouter les panels au panel principal
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);

        // Ajouter le panel principal à la fenêtre
        frame.setContentPane(mainPanel);

        // Ajouter un gestionnaire d'événements au bouton
        analyseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phrase = phraseField.getText().trim();
                if (phrase.isEmpty()) {
                    resultArea.setText("Veuillez entrer une phrase.");
                } else {
                    ParseurDescendant parseur = new ParseurDescendant(phrase);
                    boolean isValid = parseur.analyser();
                    if (isValid) {
                        resultArea.setText("Phrase valide !");
                    } else {
                        resultArea.setText("Phrase invalide !");
                    }
                }
            }
        });

        // Rendre la fenêtre visible et centrer la fenêtre
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
