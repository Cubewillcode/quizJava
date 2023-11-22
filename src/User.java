/*
import java.util.List;
import java.util.ArrayList;

public class User {

    //Creating a property score list for the User
    private List<Integer> testScore = new ArrayList<Integer>();

    //Creating a property test history list for the User
    private List<List<Integer>> testHistory = new ArrayList<>();  
    
    //Method for adding test scores to the User
    public void addTestScore(int score) {
        testScore.add(score);
    }

    //Method to add current score to the testHistory list property
    public void saveTestScore(){
        List<Integer> copyOfTestScore = new ArrayList<Integer>(testScore);
        testHistory.add(copyOfTestScore);
    }

    //getter for the testhistory to access the property outside of this class
    public List<List<Integer>> getTestHistory() {
        return testHistory;
    }
    
    //Main method
    public static void main(String[] args) {
        // Example usage:
        User user = new User();
        user.addTestScore(18);
        user.addTestScore(15);
        user.addTestScore(17);
        user.addTestScore(16);
        user.addTestScore(13);
        user.saveTestScore();

        //Printout of test score history to the console
        List<List<Integer>> testHistory = user.getTestHistory();
        for (List<Integer> scores : testHistory) {
            System.out.println(scores);
        }
    }
}
*/