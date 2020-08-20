package hu.andrashorinka.test.models.Answers;

import hu.andrashorinka.test.Interfaces.Answer;
import hu.andrashorinka.test.models.QuestionAndAnswer;

public class NewQuestionAnswer implements Answer {
    private String invalidFormatOfQuestion = "Invalid format of question!";
    private QuestionAndAnswer questionAndAnswer;
    private String preProcessedAnswer;

    @Override
    public Object getAnswer() {
        return questionAndAnswer;
    }

    @Override
    public void setAnswer(Object answer) {
        // Error checks
        if (answer == null) {
            throw new IllegalArgumentException("Answer cannot be null!");
        }

        if (answer instanceof String)
        {
            preProcessedAnswer = (String) answer;
            answer = processPreprocessedAnswer();
        }

        if (!(answer instanceof QuestionAndAnswer)){
            throw new IllegalArgumentException("Answer must be of QuestionAndAnswer object!");
        }

        // Cast and error checks
        QuestionAndAnswer castedAnswer = (QuestionAndAnswer) answer;

        if (castedAnswer.getQuestion().isBlank() ||
                castedAnswer.getQuestion().isEmpty() )
        {
            throw new IllegalArgumentException("Question cannot be blank or empty!");
        }

        if (castedAnswer.getAnswers().size() < 1)
        {
            throw new IllegalArgumentException("There must be at least one valid answer!");
        }

        for (String questionAnswer: castedAnswer.getAnswers()) {
            if (
                    questionAnswer == null ||
                    questionAnswer.isEmpty() ||
                    questionAnswer.isBlank())
            {
                throw new IllegalArgumentException("None of the acceptable answers to the question can be null or empty or blank!");
            }
        }

        questionAndAnswer = castedAnswer;
    }

    private QuestionAndAnswer processPreprocessedAnswer()
    {
        // Extract question
        int indexOfEndOfQuestion = preProcessedAnswer.indexOf("?");
        if (indexOfEndOfQuestion == -1) {
            throw new IllegalArgumentException(invalidFormatOfQuestion);
        }

        String question = preProcessedAnswer.substring(0, indexOfEndOfQuestion+1);
        // TODO: validate if question meets criteria

        // extract answers
        String allAnswer = preProcessedAnswer.substring(indexOfEndOfQuestion+1);
        String[] answers = allAnswer.split("\" ");

        for (String answer : answers) {

            answer = answer.replace("\"", "");
            // TODO: validate answers if they meet criteria
        }

        return new QuestionAndAnswer(question, answers);
    }

    public boolean isAnswerValid()
    {
        if (questionAndAnswer == null) {
            return false;
        }

        return questionAndAnswer.isValid();
    }
}
