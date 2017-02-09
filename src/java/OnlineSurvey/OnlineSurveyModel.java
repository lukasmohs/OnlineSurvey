package OnlineSurvey;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;


/**
 *
 * @author lukasmohs
 */
public class OnlineSurveyModel {
    
    private static OnlineSurveyModel instance = null;
    
    //create a list for all questions
    private ArrayList<OnlineSurveyModelQuestion> questions;
    
    //HashMap that stores the current questionID for each username
    private HashMap<String, Integer> userStatus;
    
    //HashMap that stores the different answers for a given username
    private HashMap<String, ArrayList<Integer>> userAnswers;
    
    //Singleton Pattern
    public static OnlineSurveyModel getInstance(){
        if(instance == null) {
            instance = new OnlineSurveyModel();
        } 
        return instance;
    }
    
    public OnlineSurveyModel() {
        //initialize data structures
        questions = new ArrayList<OnlineSurveyModelQuestion>();
        userStatus = new HashMap<String, Integer>();
        userAnswers = new HashMap<String, ArrayList<Integer>>();
        generateQuestions();
    }
    
    //This function checks whether the user has completed all questions
    public Boolean completedAllQuestions(String userName) {
        if(userStatus.get(userName) == null ){
            return false;
        }
        return userStatus.get(userName) + 1 == questions.size();
    }

    //This function returns the next question for a given username
    public OnlineSurveyModelQuestion getNextQuestion(String userName) {
        if(!userStatus.containsKey(userName)){
            userStatus.put(userName,0);
        }
        if(userStatus.get(userName) == questions.size()){
            userStatus.replace(userName,0);
            return null;
        } else {
            return questions.get(userStatus.get(userName));
        }
    }
    
    //This function saves the answer to a question for a specific user
    public void submitAnswer(String userName,  int answerId) {
        if(userAnswers.get(userName) != null) {
            ArrayList<Integer> previousAnswers = userAnswers.get(userName);
            previousAnswers.add(answerId);
            userAnswers.remove(userName);
            userAnswers.put(userName, previousAnswers);
        } else {
            ArrayList<Integer> answerList = new ArrayList<Integer>();
            answerList.add(answerId);
            userAnswers.put(userName, answerList);
        }
        userStatus.replace(userName,userStatus.get(userName)+1);
    }
    
    //Returns a list of <key:value> pairs where the key is each question and the value the chosen answer for a specific user
    public ArrayList<Pair<OnlineSurveyModelQuestion, Integer>> getUserAnswers(String userName) {
        ArrayList<Pair<OnlineSurveyModelQuestion, Integer>> specificUserAnswers = new ArrayList<Pair<OnlineSurveyModelQuestion, Integer>>();
        
        for(int i = 0; i < questions.size(); i++) {
            specificUserAnswers.add(new Pair(questions.get(i), userAnswers.get(userName).get(i)));
        }
        return specificUserAnswers;
    }
    
    //This function calculates the number of correctly answered questions
    public int getNumberOfCorrectAnswers(String userName) {
        int count=0;
        for(Pair<OnlineSurveyModelQuestion, Integer> p:getUserAnswers(userName)){
            if(p.getKey().getAllAnswers().get(p.getValue()).getValue()) {
                count++;
            }
        }
        return count;
    }
    
    //Returns the number of all questions
    public int getNumberOfQuestions(){
        return questions.size();
    }
    
    //Provides a String to respond the user with feedback about his last answer submission
    public String getAnswerToLastQuestion(String userName) {
        if(userStatus.get(userName) == null || userStatus.get(userName)==0) {
            return "";
        } else {
            return "Your answer '" + (userAnswers.get(userName).get((userStatus.get(userName)-1)) + 1) + "' to question number '" + userStatus.get(userName)+ "' was recorded";
        }
    }
    
    public HashMap<String, Integer> getUserStatus(){
        return userStatus;
    }

    private void generateQuestions() {
        //Create one question with answers
        OnlineSurveyModelQuestion questionA = new OnlineSurveyModelQuestion("What means CMU?");
        questionA.addAnswer("Carnegie Mellon University", Boolean.TRUE);
        questionA.addAnswer("Central Michigan University", Boolean.TRUE);
        questionA.addAnswer("Center for Multiple Universes", Boolean.FALSE);
        //Add questions to list
        questions.add(questionA);   
        
        //Create one question with answers
        OnlineSurveyModelQuestion questionB = new OnlineSurveyModelQuestion("What is 36/6?");
        questionB.addAnswer("6", Boolean.TRUE);
        questionB.addAnswer("5", Boolean.FALSE);
        questionB.addAnswer("4", Boolean.FALSE);
        //Add questions to list
        questions.add(questionB); 
        
        //Create one question with answers
        OnlineSurveyModelQuestion questionC = new OnlineSurveyModelQuestion("What is the largest City");
        questionC.addAnswer("Boston", Boolean.FALSE);
        questionC.addAnswer("Chicago", Boolean.FALSE);
        questionC.addAnswer("San Francisco", Boolean.FALSE);
        questionC.addAnswer("Berlin", Boolean.TRUE);
        //Add questions to list
        questions.add(questionC); 
    }

}
