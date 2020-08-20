package hu.andrashorinka.test.models.Operations;

import hu.andrashorinka.test.Interfaces.Answer;
import hu.andrashorinka.test.Interfaces.Operations;
import hu.andrashorinka.test.models.Answers.NewQuestionAnswer;
import hu.andrashorinka.test.models.DisplayMenu;

public class AddQuestionOperation extends DefaultOperation implements Operations {

    public AddQuestionOperation() {
        answer = new NewQuestionAnswer();
        menuTitle = "Add a new question";
        menuSeparator = "------------------";
        instructions = "The following restrictions apply:\n" +
                "- A Question is a String with max 255 chars\n" +
                "- An Answer is a String with max 255 chars\n" +
                "- A Question can have multiple answers (like bullet points)\n" +
                "- Adding a question looks like:\n" +
                "o <question>? “<answer1>” “<answer2>” “<answerX>”\n" +
                "o Char “?” is the separator between question and answers\n" +
                "o Every Question needs to have at least one answer but can have unlimited answers all inside of char.\n";

        displayMenu = new DisplayMenu(menuTitle, menuSeparator, answer);
        displayMenu.addInstructions(instructions);
    }

    @Override
    public Answer start() {
        while (!answer.isAnswerValid())
        {
            answer = displayMenu.showMenu();
        }

        return answer;
    }

}
