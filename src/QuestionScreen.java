import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class QuestionScreen extends JPanel {
    private JButton submitButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextArea questionText;
    private JTextField answerField; //Displays a field for users answer
    private Map<String, String> questions; //Grabs the questions from txt file in question : answer form
    private String currentQuestion;
    private ArrayList<String> selectedQuestions; // Keep track of selected questions
    private int points; // Counter for correct answers
    private JLabel feedbackLabel; // New JLabel for feedback
    private JLabel pointsLabel; // New JLabel for points display
    
    public QuestionScreen(CardLayout cardLayout, JPanel cardPanel, QuizState questionManager, String questionType) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#003366"));

        selectedQuestions = new ArrayList<>();
        points = 0;
        questionText = createStyledQuestionTextArea(); // Style for question
        answerField = createStyledAnswerTextField(); // Style for answer field

        // Initialize the feedback label
        feedbackLabel = new JLabel();
        feedbackLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
        feedbackLabel.setForeground(Color.ORANGE);
        feedbackLabel.setHorizontalAlignment(JLabel.CENTER);

        // Initialize the points label
        pointsLabel = new JLabel("0/10");
        pointsLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
        pointsLabel.setForeground(Color.ORANGE);
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);

        // If statement to choose a type of questions
        if ("trivia".equals(questionType)) {
            questions = questionManager.getTriviaQuestions();
        } else if ("riddles".equals(questionType)) {
            questions = questionManager.getRiddlesQuestions();
        } else {
            throw new IllegalArgumentException("Invalid question type");
        }

        // initialize the first question
        showNextQuestion();

        //Panel for the submit button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.decode("#003366"));

        //Create submit button and check the answer
        submitButton = new StyledButton(" Submit ", "start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent x) {
                checkAnswer();
            }
        });

        buttonPanel.add(submitButton);

        //Managing layout of the components

        //Question
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(questionText, gbc);
        //Answer
        GridBagConstraints gbcQt = new GridBagConstraints();
        gbcQt.gridx = 0;
        gbcQt.gridy = 3;
        gbcQt.gridwidth = 2;
        gbcQt.gridheight = 1;
        gbcQt.insets = new Insets(5, 5, 5, 5);
        add(answerField, gbcQt);
        //Feedback
        GridBagConstraints gbcFeedback = new GridBagConstraints();
        gbcFeedback.gridx = 0;
        gbcFeedback.gridy = 6;
        gbcFeedback.gridwidth = 2;
        gbcFeedback.gridheight = 1;
        gbcFeedback.insets = new Insets(5, 5, 5, 5);
        add(feedbackLabel, gbcFeedback);
        //Panel for submit button
        GridBagConstraints gbcBp = new GridBagConstraints();
        gbcBp.gridx = 0;
        gbcBp.gridy = 5;
        gbcBp.gridwidth = 2;
        gbcBp.gridheight = 1;
        gbcBp.insets = new Insets(5, 5, 5, 5);
        add(buttonPanel, gbcBp);
        //Points label
        GridBagConstraints gbcPoints = new GridBagConstraints();
        gbcPoints.gridx = 5;
        gbcPoints.gridy = 6;
        gbcPoints.gridwidth = 2;
        gbcPoints.gridheight = 1;
        gbcPoints.insets = new Insets(5, 5, 5, 5);
        add(pointsLabel, gbcPoints);
    }

    //Question field styles
    private JTextArea createStyledQuestionTextArea() {
        JTextArea textArea = new JTextArea(2, 30);  // Set rows and columns
        textArea.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 28));
        textArea.setBorder(BorderFactory.createLineBorder(Color.decode("#003366")));
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(true);
        textArea.setBackground(Color.decode("#003366"));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setLineWrap(true);  // Enable line wrapping
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    //Answer field styles
    private JTextField createStyledAnswerTextField() {
        JTextField textField = new JTextField(" Type your answer here.........");
        textField.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createMatteBorder(3, 3, 4, 4, Color.DARK_GRAY)
        ));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        textField.setOpaque(true);
        textField.setBackground(Color.decode("#003366"));
        textField.setMargin(new Insets(10, 10, 10, 10));
    
        // Add a focus listener to handle placeholder text
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (textField.getText().equals(" Type your answer here.........")) {
                        textField.setText("");
                    }
                });
            }
    
            @Override
            public void focusLost(FocusEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (textField.getText().isEmpty()) {
                        textField.setText(" Type your answer here.........");
                    }
                });
            }
        });
    
        return textField;
    }

    //Chooses a question to display and rotates through them
    private void showNextQuestion() {
        if (selectedQuestions.size() < 10 && !questions.isEmpty()) {
            // Choose a random question
            ArrayList<String> remainingQuestions = new ArrayList<>(questions.keySet());
            Collections.shuffle(remainingQuestions);
            String nextQuestion = remainingQuestions.get(0);

            selectedQuestions.add(nextQuestion);
            currentQuestion = nextQuestion;
            questionText.setText(currentQuestion);
            questionText.setEditable(false);
            answerField.setText(" Type your answer here.........");
            feedbackLabel.setText("");

        } else if (selectedQuestions.size() == 10) {
            //go to the final score
            cardLayout.show(cardPanel, "FinalScoreScreen");
            ((FinalScoreScreen) cardPanel.getComponent(4)).updatePoints(getPoints());
        
        } else {
            //go to the final score
            cardLayout.show(cardPanel, "FinalScoreScreen");
            ((FinalScoreScreen) cardPanel.getComponent(4)).updatePoints(getPoints());
        }
        //Updates the label displaying points
        pointsLabel.setText(points + "/10");
    }

    //Method for checking if the answer is correct or not and adding points
    private void checkAnswer() {
        String userAnswer = answerField.getText().trim().toLowerCase(); // Convert to lowercase for case-insensitive comparison
        String correctAnswer = questions.get(currentQuestion).toLowerCase(); // Convert to lowercase for case-insensitive comparison

        //Split correct answer into individual words
        String[] correctWords = correctAnswer.split("\\s+");

        //Check if at least one correct word is present in the user's answer
        boolean isCorrect = false;
        for (String word : correctWords) {
            if (userAnswer.contains(word)) {
                isCorrect = true;
                break;
            }
        }

        if (isCorrect) {
            //correct answer, move to the next question and update the feedback label
            points++;
            questions.remove(currentQuestion);
            showNextQuestion();
            feedbackLabel.setText("Correct!");
        } else {
            //incorrect answer, move to the next question and update the feedback label
            questions.remove(currentQuestion);
            showNextQuestion();
            feedbackLabel.setText("Incorrect. The correct answer was: " + correctAnswer);
        }
    }

    public int getPoints() {
        return points;
    }

    public void reset() {
        selectedQuestions.clear();
        points = 0;
        updatePointsLabel();
        showNextQuestion();
        feedbackLabel.setText("");
    }

    private void updatePointsLabel() {
        pointsLabel.setText(points + "/10");
    }
}