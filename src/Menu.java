/*

Menu.java


 */

import java.util.Scanner;

public class Menu implements IMenu {

    //This array will store each seperated menu option
    String mainMenuOptionArr[];

    //This receives the string of menu options from main program, splits  
    //them, then stores them.
    Menu(String menuOptions) {

        try {
            mainMenuOptionArr = menuOptions.split("%");

        } catch (StringIndexOutOfBoundsException exStr) {
            System.out.println("String index is out of bounds! " + exStr);
        }

    }

    @Override
    public void SetMenuItems(String menuOptions) {

        try {
            mainMenuOptionArr = menuOptions.split("%");

        } catch (StringIndexOutOfBoundsException exStr) {
            System.out.println("String index is out of bounds! " + exStr);
        }

    }

    //This prints the menu, allowing for extension without modification
    @Override
    public void PrintMainMenu() {
        try {

            for (int i = 0; i < mainMenuOptionArr.length; i++) {
                System.out.println("\t" + (i + 1) + ". " + mainMenuOptionArr[i]);
            }

            System.out.println("");

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Array index out of bounds in Menu.PrintMainMenu() " + ex);
        }

    }

    //This run method is in charge of displaying the menu, input validation,
    //and returning the name of the selected menu option back to main().
    @Override
    public String runMenu() {

        String usrSelection;

        do {

            System.out.println("\n\nSelect an option :\n");

            PrintMainMenu();

            System.out.print("Selection: ");

            Scanner kb = new Scanner(System.in);

            usrSelection = kb.nextLine();
            
            

            System.out.println("");

            if (isSelectionGood(usrSelection)) {
                return mainMenuOptionArr[Integer.valueOf(usrSelection) - 1];
            } else {
                System.out.println("Not a valid entry, try again!\n");
            }

        } while (!isSelectionGood(usrSelection));

        return usrSelection;
    }

    //for input validation
    @Override
    public boolean isSelectionGood(String usrSelection) {

        try {

            // regex expression for numerical input
            if (usrSelection.matches("\\d*\\.?\\d+")) {
                if (Integer.valueOf(usrSelection) > 0 && Integer.valueOf(usrSelection) <= mainMenuOptionArr.length) {
                    return true;
                }
            }

        } catch (NumberFormatException ex) {
            System.out.println("Illegal number format exception in Menu.isSelectionGood()");
        }

        return false;
    }

}
