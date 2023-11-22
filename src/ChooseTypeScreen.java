import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseTypeScreen extends JPanel {
    private JButton triviaButton;
    private JButton riddlesButton;
    
    public ChooseTypeScreen(CardLayout cardLayout, JPanel cardPanel) {
        this.setBackground(Color.decode("#003366"));

        //Overall layout
        setLayout(new BorderLayout());

        //Explanation label
        JLabel explanationLabel = new JLabel("Please choose your quiz type.");
        explanationLabel.setForeground(Color.WHITE);
        explanationLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 30));
        explanationLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        explanationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(explanationLabel, BorderLayout.CENTER);

        //Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create StyledButton for trivia
        triviaButton = new StyledButton(" Trivia ", "start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "TriviaScreen");
            }
        });

        // Create StyledButton
        riddlesButton = new StyledButton(" Riddles ", "start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "RiddlesScreen");
            }
        });

        buttonPanel.add(triviaButton);
        buttonPanel.add(riddlesButton);
        buttonPanel.setBackground(Color.decode("#003366"));
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
