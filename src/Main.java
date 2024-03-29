import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private QuizState questionManager;
    private QuestionScreen triviaScreen; // declare triviaScreen
    private QuestionScreen riddlesScreen; // declare riddlesScreen
    private Clip backgroundMusic;
    
    public Main() {
        // Style of the main window
        setTitle("Pub Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);
        loadBackgroundMusic();
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Assigns the class that manages the quiz state to this component
        questionManager = new QuizState();

        // Create instances of triviaScreen and riddlesScreen here
        triviaScreen = new QuestionScreen(cardLayout, cardPanel, questionManager, "trivia");
        riddlesScreen = new QuestionScreen(cardLayout, cardPanel, questionManager, "riddles");

        // Creates the screens
        StartingScreen startingScreen = new StartingScreen(cardLayout, cardPanel, questionManager, triviaScreen, riddlesScreen);
        ChooseTypeScreen chooseTypeScreen = new ChooseTypeScreen(cardLayout, cardPanel);
        FinalScoreScreen finalScoreScreen = new FinalScoreScreen(cardLayout, cardPanel, questionManager);

        // Adds each screen type to the cardPanel
        cardPanel.add(startingScreen, "StartingScreen");
        cardPanel.add(chooseTypeScreen, "ChooseTypeScreen");
        cardPanel.add(triviaScreen, "TriviaScreen");
        cardPanel.add(riddlesScreen, "RiddlesScreen");
        cardPanel.add(finalScoreScreen, "FinalScoreScreen");

        add(cardPanel);

        // Chooses the first card to show at the start of the game
        cardLayout.show(cardPanel, "StartingScreen");
    }
    
    private void loadBackgroundMusic() {
        try {
            backgroundMusic = AudioSystem.getClip();
            AudioInputStream musicStream = AudioSystem.getAudioInputStream(
                    new File("sound/shadydave_snowfall-final.wav"));
            backgroundMusic.open(musicStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Initializing the pop up of the whole GUI screen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
