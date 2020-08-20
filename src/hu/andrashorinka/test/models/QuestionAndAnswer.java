package hu.andrashorinka.test.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionAndAnswer {
    private final String invalidParameterForConstructor = "Invalid parameter given for constructor";
    private final String invalidAnswerProvided = "Answer(s) must be a string and cannot be null or empty or zero sized container";

    private String question;
    private List<String> answers;

    // Getters
    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    // Add a new answer to the possible answers
    public void addAnswer(String answer)
    {
        // Error check
        if (answer == null || answer.isEmpty())
        {
            throw new IllegalArgumentException(invalidAnswerProvided);
        }

        answers.add(answer);
    }

    public QuestionAndAnswer(String question, ArrayList<String> answers) {
        // Error checks
        if (question == null) {
            throw new IllegalArgumentException(invalidParameterForConstructor);
        }
        if (answers == null || answers.size() < 1){
            throw new IllegalArgumentException(invalidAnswerProvided);
        }

        this.question = question;
        this.answers = answers;
    }

    public QuestionAndAnswer(String question, String[] answers) {
        // Error checks
        if (question == null) {
            throw new IllegalArgumentException(invalidParameterForConstructor);
        }
        if (answers == null || answers.length < 1){
            throw new IllegalArgumentException(invalidAnswerProvided);
        }

        this.question = question;
        this.answers = Arrays.asList(answers);
    }

    public boolean isValid()
    {
        if (question == null) {
            return false;
        }
        if (question.isEmpty() || question.isBlank()) {
            return false;
        }

        if (answers == null)
        {
            return false;
        }
        if (answers.isEmpty()) {
            return false;
        }

        for (String answer : answers) {
            if (answer == null) {
                return false;
            }
            if (answer.isBlank() || answer.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

}
