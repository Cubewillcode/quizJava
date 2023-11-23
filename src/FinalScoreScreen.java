import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalScoreScreen extends JPanel {
    private JButton finishButton;
    private Question questionManager;

    public FinalScoreScreen(CardLayout cardLayout, JPanel cardPanel, Question questionManager) {
        this.questionManager = questionManager;

        setLayout(new GridBagLayout());
        setBackground(Color.decode("#003366"));

        // Final screen label showing the score
        JLabel finalLabel = new JLabel("Your final score was 0 out of 10!");
        finalLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
        finalLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#003366")));
        finalLabel.setForeground(Color.ORANGE);
        finalLabel.setOpaque(true);
        finalLabel.setBackground(Color.decode("#003366"));

        // Button to reset quiz state and show the starting screen
        finishButton = new StyledButton(" Finish Quiz ", "start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset quiz state and show the starting screen
                resetQuizState();
                cardLayout.show(cardPanel, "StartingScreen");
            }
        });

        // Layout settings for final label
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.insets = new Insets(10, 10, 10, 10); // Padding
        add(finalLabel, gbcLabel);

        // Layout settings for finish button
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 1;
        gbcButton.insets = new Insets(10, 10, 10, 10); // Padding
        add(finishButton, gbcButton);
    }

    // Update points in the label text
    public void updatePoints(int points) {
        JLabel finalLabel = (JLabel) getComponent(0);
        finalLabel.setText("Your final score was " + points + " out of 10!");
    }

    // Reset quiz state
    private void resetQuizState() {
        questionManager.reset();
        updatePoints(0);
    }
}