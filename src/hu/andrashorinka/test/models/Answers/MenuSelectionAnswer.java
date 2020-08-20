package hu.andrashorinka.test.models.Answers;

import hu.andrashorinka.test.Interfaces.Answer;

public class MenuSelectionAnswer implements Answer {
    private int menuSelected;

    @Override
    public Object getAnswer() {
        return menuSelected;
    }

    @Override
    public void setAnswer(Object answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Answer cannot be null!");
        }

        if (!(answer instanceof Integer)){
            throw new IllegalArgumentException("Answer cannot be null!");
        }

        menuSelected = (int) answer;
    }

    @Override
    public boolean isAnswerValid() {
        return true;
    }
}
