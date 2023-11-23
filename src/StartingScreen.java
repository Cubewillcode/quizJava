import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingScreen extends JPanel {
    private JButton startButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private QuizState questionManager;
    private QuestionScreen triviaScreen;
    private QuestionScreen riddlesScreen;

    public StartingScreen(CardLayout cardLayout, JPanel cardPanel, QuizState questionManager,
                          QuestionScreen triviaScreen, QuestionScreen riddlesScreen) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.questionManager = questionManager;
        this.triviaScreen = triviaScreen;
        this.riddlesScreen = riddlesScreen;

        setBackground(Color.decode("#003366"));
        setLayout(new BorderLayout());

        // Title Label
        JLabel titleLabel = new JLabel("Pub Quiz Game");
        titleLabel.setFont(new Font("Berlin Sans FB", Font.BOLD, 36));
        titleLabel.setForeground(Color.ORANGE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Explanation Label
        JLabel explanationLabel = new JLabel("<HTML>Test yourself with fun trivia and riddles!<br>Please answer questions in English.</html>");
        explanationLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 30));
        explanationLabel.setForeground(Color.WHITE);
        explanationLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        explanationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(explanationLabel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.decode("#003366"));

        // Create StyledButton and start quiz
        startButton = new StyledButton(" Start Quiz ", "start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset quiz state and move to the question screen
                resetQuizState();
                cardLayout.show(cardPanel, "ChooseTypeScreen");
            }
        });

        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Reset quiz state
    private void resetQuizState() {
        questionManager.reset();
        triviaScreen.reset();
        riddlesScreen.reset();
    }
}