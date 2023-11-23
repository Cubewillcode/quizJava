import javax.swing.*;
import java.awt.*;

/*Main class and entry point for this project. 
It is creating the interface using Swing library adding each of the screens*/
public class Main extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Question questionManager;

    public Main(){
        //Style of the main window
        setTitle("Pub Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300,700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        //Assigns the class that manages the quiz state to this component
        questionManager = new Question();

        //Creates the screens
        StartingScreen startingScreen = new StartingScreen(cardLayout, cardPanel);
        ChooseTypeScreen chooseTypeScreen = new ChooseTypeScreen(cardLayout, cardPanel);
        QuestionScreen triviaScreen = new QuestionScreen(cardLayout, cardPanel, questionManager, "trivia");
        QuestionScreen riddlesScreen = new QuestionScreen(cardLayout, cardPanel, questionManager, "riddles");
        FinalScoreScreen finalScoreScreen = new FinalScoreScreen(cardLayout, cardPanel, questionManager);

        //Adds each screen type to the cardPanel
        cardPanel.add(startingScreen, "StartingScreen");
        cardPanel.add(chooseTypeScreen, "ChooseTypeScreen");
        cardPanel.add(triviaScreen, "TriviaScreen");
        cardPanel.add(riddlesScreen, "RiddlesScreen");
        cardPanel.add(finalScoreScreen, "FinalScoreScreen");

        add(cardPanel);

        //Chooses the first card to show at the start of the game
        cardLayout.show(cardPanel, "StartingScreen");
    }

    //Initializing the pop up of the whole GUI screen
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
