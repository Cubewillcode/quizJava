import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Question {
    
    private Map<String, String> questionTrivia;
    private Map<String, String> questionRiddles;

    public Question() {
        questionTrivia = new HashMap<>();
        questionRiddles = new HashMap<>();

        initializeQuestions();
    }

    private void initializeQuestions() {
        loadQuestionsFromFile("trivia.txt", questionTrivia);
        loadQuestionsFromFile("riddles.txt", questionRiddles);
    }

    private void loadQuestionsFromFile(String fileName, Map<String, String> questionMap) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming each question and answer in the file is separated by ":"
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    questionMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getTriviaQuestions() {
        return questionTrivia;
    }

    public Map<String, String> getRiddlesQuestions() {
        return questionRiddles;
    }
}