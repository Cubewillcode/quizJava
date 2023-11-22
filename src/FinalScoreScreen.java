import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalScoreScreen extends JPanel {
    private JButton finishButton;

    public FinalScoreScreen(CardLayout cardLayout, JPanel cardPanel, Question questionManager, int points) {
        this.setBackground(Color.decode("#003366"));
        setLayout(new GridBagLayout());

        // Label
        JLabel finalLabel = new JLabel("Your final score was " + points + " out of 10!");
        finalLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
        finalLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#003366")));
        finalLabel.setForeground(Color.WHITE);
        finalLabel.setOpaque(true);
        finalLabel.setBackground(Color.decode("#003366"));

        // Create StyledButton
        finishButton = new StyledButton(" Finish Quiz ", "start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "StartingScreen");
            }
        });

        // Constraints for Label
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.insets = new Insets(10, 10, 10, 10); // Padding
        add(finalLabel, gbcLabel);

        // Constraints for Button
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 1;
        gbcButton.insets = new Insets(10, 10, 10, 10); // Padding
        add(finishButton, gbcButton);
    }

    // Update points in the label
    public void updatePoints(int points) {
        // Update the label text
        JLabel finalLabel = (JLabel) getComponent(0);
        finalLabel.setText("Your final score was " + points + " out of 10!");
    }
}
