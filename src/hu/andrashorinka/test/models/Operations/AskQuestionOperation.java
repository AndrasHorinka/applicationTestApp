package hu.andrashorinka.test.models.Operations;

import hu.andrashorinka.test.Interfaces.Answer;
import hu.andrashorinka.test.Interfaces.Operations;
import hu.andrashorinka.test.models.Answers.AskQuestionAnswer;
import hu.andrashorinka.test.models.DisplayMenu;

public class AskQuestionOperation extends DefaultOperation implements Operations {

    public AskQuestionOperation() {
        answer = new AskQuestionAnswer();

        menuTitle = "Ask a question";
        menuSeparator = "------------------";
        instructions = "The following restrictions apply:\n" +
                "- If the user asks a question it has to be exactly the same as entered – no “best match”.\n" +
                "- If the user asks a question which is not stored yet the program should print “the answer to life, universe and everything is 42” according to “The hitchhikers guide to the Galaxy”\n" +
                "- If the user asks a question whish is stored the program should print all answers to that question. Every Answer in a separate line\n";

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
