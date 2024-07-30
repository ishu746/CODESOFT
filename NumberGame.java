import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class NumberGame {
    private static int generatedNo;
    private static int attempts = 0;
    private static final int MAX_ATTEMPTS = 3;
    private static JLabel messageLabel;
    private static JTextField guessField;
    private static JButton guessButton;
    private static JButton tryAgainButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGame::createAndShowGUI);
        generatedNo = new Random().nextInt(100) + 1;
        System.out.println("Number generated successfully: " + generatedNo); // For testing purposes
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(Color.LIGHT_GRAY);                  

        messageLabel = new JLabel("Guess the number between 1 and 100:", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        guessField = new JTextField();
        guessField.setFont(new Font("Arial", Font.PLAIN, 16));
        guessField.setMaximumSize(new Dimension(200, 30));
        guessField.setAlignmentX(Component.CENTER_ALIGNMENT);

        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 16));
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        guessButton.setMaximumSize(new Dimension(100, 40));
        guessButton.addActionListener(new GuessButtonListener());

        tryAgainButton = new JButton("Try Again");
        tryAgainButton.setFont(new Font("Arial", Font.BOLD, 16));
        tryAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        tryAgainButton.setMaximumSize(new Dimension(100, 40));
        tryAgainButton.addActionListener(new TryAgainButtonListener());
        tryAgainButton.setVisible(false);

        panel.add(messageLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(guessField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(guessButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(tryAgainButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guessedNo = Integer.parseInt(guessField.getText());
                attempts++;
                if (guessedNo < 1 || guessedNo > 100) {
                    messageLabel.setText("Please enter a number between 1 and 100.");
                } else if (generatedNo > guessedNo) {
                    messageLabel.setText("Too low! Attempts left: " + (MAX_ATTEMPTS - attempts));
                } else if (generatedNo < guessedNo) {
                    messageLabel.setText("Too high! Attempts left: " + (MAX_ATTEMPTS - attempts));
                } else {
                    messageLabel.setText("congrates!\nYou guessed the Correct number.");
                    guessButton.setEnabled(false);
                    tryAgainButton.setVisible(true);
                }

                if (attempts == MAX_ATTEMPTS && generatedNo != guessedNo) {
                    messageLabel.setText("Your attempts are over! The number was: " + generatedNo);
                    guessButton.setEnabled(false);
                    tryAgainButton.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
        }
    }

    private static class TryAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            attempts = 0;
            generatedNo = new Random().nextInt(100) + 1;
            System.out.println("Number generated successfully: " + generatedNo); // For testing purposes
            messageLabel.setText("Guess the number between 1 and 100:");
            guessField.setText("");
            guessButton.setEnabled(true);
            tryAgainButton.setVisible(false);
        }
    }
}
