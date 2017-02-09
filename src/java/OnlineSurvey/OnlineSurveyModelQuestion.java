package OnlineSurvey;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author lukasmohs
 */
public class OnlineSurveyModelQuestion {
    
    private String question;
    private ArrayList<Pair<String, Boolean>> answers;
    
    public OnlineSurveyModelQuestion(String question) {
        //Initialize the question with a question string
        this.question=question;
        this. answers = new ArrayList<Pair<String, Boolean>>();
    }
    
    //Add an answer to the question by stating whether it's a correct answer
    public void addAnswer(String answer, Boolean correct) {
        this.answers.add(new Pair(answer, correct));
    }
    
    //Returns the question String
    public String getQuestion() {
        return this.question;
    }
    
    //Returns a list of all possible answers as <key:value> pair
    public ArrayList<Pair<String, Boolean>> getAllAnswers(){
        return this.answers;
    }
    
}
