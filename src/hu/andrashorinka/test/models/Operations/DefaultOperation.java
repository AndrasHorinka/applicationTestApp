package hu.andrashorinka.test.models.Operations;

import hu.andrashorinka.test.Interfaces.Answer;
import hu.andrashorinka.test.Interfaces.Operations;
import hu.andrashorinka.test.models.DisplayMenu;

public abstract class DefaultOperation implements Operations {
    protected String instructions;
    protected String menuTitle;
    protected String menuSeparator;
    protected DisplayMenu displayMenu;
    protected Answer answer;

}
