package hu.andrashorinka.test.models;

import hu.andrashorinka.test.Interfaces.Answer;
import hu.andrashorinka.test.Interfaces.ConsoleScreenManager;
import hu.andrashorinka.test.models.Answers.MenuSelectionAnswer;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class DisplayMenu implements ConsoleScreenManager {

    // Constant error strings
    private final String invalidMenuElementGiven = "Menu elements cannot be null or empty!";
    private final String invalidInputFromUser = "Invalid input given. Please try again!";

    // Fields
    private Scanner scanner;
    private String menuTitle;
    private String menuTitleSeparator;
    private String instructions;
    private TreeMap<Integer, String> menuElements;

    private Answer answer;

    // Constructors
    public DisplayMenu(String menuTitle, String menuTitleSeparator, Answer answerType) {
        // initialize fields
        scanner = new Scanner(System.in);
        this.menuTitle = menuTitle;
        this.menuTitleSeparator = menuTitleSeparator;
        this.answer = answerType;

        // Add exit option
        menuElements = new TreeMap<>();
    }

    public DisplayMenu(String menuTitle, String menuTitleSeparator, List<String> newElements, Answer answerType) {
        // initialize fields
        scanner = new Scanner(System.in);
        this.menuTitle = menuTitle;
        this.menuTitleSeparator = menuTitleSeparator;
        this.answer = answerType;

        // Add exit option
        menuElements = new TreeMap<>();

        // Feed menu elements with provided elements
        for (String menuElement: newElements) {
            addMenuElement(menuElement);
        }
    }

    public void addMenuElement(String menuElement)
    {
        if (menuElement == null || menuElement.isEmpty()) {
            throw new IllegalArgumentException(invalidMenuElementGiven);
        }

        menuElements.put(menuElements.size(), menuElement);
    }

    public void addInstructions(String instructions) {
        this.instructions = instructions;
    }

    // returns the selected menu option
    public Answer showMenu()
    {
        boolean validUserInput = false;

        while (!validUserInput) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(menuTitle);
            System.out.println(menuTitleSeparator);

            if (instructions != null)
            {
                if (!instructions.isBlank() || !instructions.isEmpty())
                {
                    System.out.println(instructions);
                    System.out.println(menuTitleSeparator);
                }
            }

            for (Integer key : menuElements.keySet()) {
                System.out.println();
                System.out.printf("%x. %s", key, menuElements.get(key));
            }

            // Read input from user
            if (answer instanceof MenuSelectionAnswer)
            {
                validUserInput = handleMenuSelectionInput();
            }
            else {
                validUserInput = handleStringInput();
            }
        }

        // if valid input given
        return answer;
    }

    // Reads the input from the user and returns true if valid - false otherwise
    private boolean handleMenuSelectionInput() {

        boolean inputStatus = false;
        System.out.println();

        try {
            while (!inputStatus) {
                if (scanner.hasNextInt())
                {
                    int input = scanner.nextInt();

                    if (!menuElements.containsKey(input))
                    {
                        System.out.println(invalidInputFromUser);
                        continue;
                    }

                    answer.setAnswer(input);
                    inputStatus = true;
                }
            }

        } catch (NoSuchElementException nse) {
            System.out.println(invalidInputFromUser);
        } catch (Exception e) {
            System.out.printf("\nSomething went wrong %s", e);

        } finally {
            return inputStatus;
        }
    }

    // Reads the input from the user if it is a string input
    private boolean handleStringInput()
    {
        boolean inputStatus = false;
        System.out.println();

        try {
            while (!inputStatus) {
                while (scanner.hasNextLine()){
                        String input = scanner.nextLine();

                        // In case of invalid input
                        if (input == null || input.isBlank() || input.isEmpty())
                        {
                            System.out.println(invalidInputFromUser);
                            continue;
                        }

                        answer.setAnswer(input);
                        inputStatus = true;
                        break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.printf("\nSomething went wrong %s", e);
        }
        finally {
            return inputStatus;
        }
    }
}
