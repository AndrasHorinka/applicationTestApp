package hu.andrashorinka.test;

import hu.andrashorinka.test.mainmenu.MainMenu;


public class Main {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        boolean isApplicationRunning = true;

        while (isApplicationRunning)
        {
            isApplicationRunning = mainMenu.displayMainMenu();
        }

        System.out.println("Application terminated due to user input");
        System.exit(0);
    }
}
