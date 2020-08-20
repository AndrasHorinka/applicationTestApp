package hu.andrashorinka.test.mainmenu;

import hu.andrashorinka.test.Interfaces.Answer;
import hu.andrashorinka.test.models.DisplayMenu;
import hu.andrashorinka.test.models.Answers.MenuSelectionAnswer;
import hu.andrashorinka.test.models.Operations.AddQuestionOperation;
import hu.andrashorinka.test.models.Operations.AskQuestionOperation;
import hu.andrashorinka.test.models.QuestionAndAnswer;

import java.util.*;

public class MainMenu {

    // Fields
    private final String menuTitle = "Please chose what you want to do!";
    private final String menuSeparator = "---------------------------------";
    private final String menuElement0 = "Exit";
    private final String menuElement1 = "Add a new question";
    private final String menuElement2 = "Ask a question";

    private List<String> menuElementsNew;

    private ArrayList<QuestionAndAnswer> questionAndAnswers;

    // Constructor
    public MainMenu() {

        menuElementsNew = new ArrayList<>();
        menuElementsNew.add(menuElement0);
        menuElementsNew.add(menuElement1);
        menuElementsNew.add(menuElement2);

        questionAndAnswers = new ArrayList<>();
    }

    // Prints the main menu and its elements to the console
    public boolean displayMainMenu() {
        boolean isApplicationRunning = true;
        while (isApplicationRunning)
        {
            Answer answer = new MenuSelectionAnswer();

            DisplayMenu displayMenu = new DisplayMenu(menuTitle, menuSeparator, menuElementsNew, answer);
            answer = displayMenu.showMenu();
            int menuSelected = (int) answer.getAnswer();
            System.out.printf("\nMenu option selected %x.\n", menuSelected);

            // Pick a menu option from main menu and call respective operations
            switch (menuSelected) {
                case 1:
                    AddQuestionOperation addNewQuestion = new AddQuestionOperation();
                    Answer newQuestion = addNewQuestion.start();
                    try {
                        questionAndAnswers.add((QuestionAndAnswer) newQuestion.getAnswer());
                        System.out.println("Question added!");
                    }
                    catch (ClassCastException cce)
                    {
                        System.out.println("Cannot cast response to a new question");
                    }
                    break;
                case 2:
                    AskQuestionOperation askQuestion = new AskQuestionOperation();
                    Answer question = askQuestion.start();

                    // look up question in qquestion and answers
                    for (QuestionAndAnswer qa : questionAndAnswers) {
                        if (qa.getQuestion().equals(question.getAnswer()))
                        {
                            for (String a : qa.getAnswers()) {
                                System.out.println(a);
                            }
                            break;
                        }
                    }

                    System.out.println("No such question found");

                    break;
                default:
                    isApplicationRunning = false;
            }
        }
        return true;
    }
}
