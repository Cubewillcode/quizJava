import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
//import java.awt.event.FocusAdapter;
//import java.awt.event.FocusEvent;
//import com.jgoodies.forms.factories.Paddings;
//import com.jgoodies.forms.factories.PromptSupport;

public class QuestionScreen extends JPanel {
    private JButton submitButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextArea questionText;
    private JTextField answerField;
    private JTextField userAnswerText;
    private Map<String, String> questions;
    private String currentQuestion;
    private ArrayList<String> selectedQuestions; // Keep track of selected questions
    private int points; // Counter for correct answers

    public QuestionScreen(CardLayout cardLayout, JPanel cardPanel, Question questionManager, String questionType) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.decode("#003366"));
        
        selectedQuestions = new ArrayList<>();
        points = 0;
        questionText = createStyledQuestionTextArea(); // Style for question
        answerField = createStyledAnswerTextField(); // Style for answer field
        userAnswerText = createStyledAnswerTextField(); // Style for user answer

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

        // Panel for the submit button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.decode("#003366"));

        // Create StyledButton and check the answer
        submitButton = new StyledButton(" Submit ", "start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent x) {
                checkAnswer();
            }
        });

        buttonPanel.add(submitButton);

        // managing layout of the components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(questionText, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(answerField, gbc);

        gbc.gridy = 2;
        add(buttonPanel, gbc);
    }

        //Question field styles
        private JTextArea createStyledQuestionTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
        textArea.setBorder(BorderFactory.createLineBorder(Color.decode("#003366")));
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(true); // Ensure that the JTextArea is opaque
        textArea.setBackground(Color.decode("#003366")); // Set background color for the question
        textArea.setMargin(new Insets(10, 10, 10, 10)); // Add margin
        return textArea;
        }
        //Answer field styles
        private JTextField createStyledAnswerTextField() {
        JTextField textField = new JTextField(" Type your answer here       ");
        textField.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#669999")));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE); // Set caret color to white
        textField.setOpaque(true);
        textField.setBackground(Color.DARK_GRAY);
        textField.setMargin(new Insets(10, 10, 10, 10));
        
        //PromptSupport.setPrompt(" Type your answer here..........", textField);

        /*
        //method to keep a placeholder text until the user clicks on answer field
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(" Type your answer here       ")) {
                    textField.setText("");
                }
            }
    
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(" Type your answer here       ");
                }
            }
        });
        */
    
        return textField;
    }

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
            userAnswerText.setText("");
            userAnswerText.setEditable(false);
        } else if (selectedQuestions.size() == 10) {
            cardLayout.show(cardPanel, "FinalScoreScreen");
            ((FinalScoreScreen) cardPanel.getComponent(4)).updatePoints(getPoints());
        } else {
            // go to the final score
            cardLayout.show(cardPanel, "FinalScoreScreen");
            ((FinalScoreScreen) cardPanel.getComponent(4)).updatePoints(getPoints());
        }
    }

    private void checkAnswer() {
        String userAnswer = answerField.getText().trim().toLowerCase(); // Convert to lowercase for case-insensitive comparison
        String correctAnswer = questions.get(currentQuestion).toLowerCase(); // Convert to lowercase for case-insensitive comparison
    
        userAnswerText.setText("Your Answer: " + userAnswer);
    
        String[] correctWords = correctAnswer.split("\\s+");
    
        // Check if at least one correct word is present in the user's answer
        boolean isCorrect = false;
        for (String word : correctWords) {
            if (userAnswer.contains(word)) {
                isCorrect = true;
                break;
            }
        }
    
        if (isCorrect) {
            // correct answer, move to the next question
            points++;
            questions.remove(currentQuestion);
            showNextQuestion();
            JOptionPane.showMessageDialog(this, "Correct!");
        } else {
            questions.remove(currentQuestion);
            showNextQuestion();
            JOptionPane.showMessageDialog(this, "Incorrect. The correct answer is: " + correctAnswer);
        }
    }

    public int getPoints() {
        return points;
    }
}