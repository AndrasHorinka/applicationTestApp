package hu.andrashorinka.test.models.Answers;

import hu.andrashorinka.test.Interfaces.Answer;

public class AskQuestionAnswer implements Answer {
    private String questionInput;

    @Override
    public Object getAnswer() {
        return questionInput;
    }

    @Override
    public void setAnswer(Object answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Answer cannot be null!");
        }

        if (!(answer instanceof String)){
            throw new IllegalArgumentException("Answer cannot be null!");
        }

        String castedAnswer = (String) answer;
        if (castedAnswer.isEmpty() || castedAnswer.isBlank())
        {
            throw new IllegalArgumentException("Answer cannot be empty!");
        }

        questionInput = castedAnswer;
    }

    @Override
    public boolean isAnswerValid() {
        if (questionInput == null)
        {
            return false;
        }
        if (questionInput.isEmpty() || questionInput.isBlank())
        {
            return false;
        }

        return true;
    }
}
