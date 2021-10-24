package runtime;

import data.Cabinet;
import ui.Menu;

/**
 *
 * @author Kayt
 */
public class Program {

    public static void main(String[] args) {
        Menu menu = new Menu("Food Management");
        menu.addNewOption("1. Add a new food");
        menu.addNewOption("2. Search a food by name");
        menu.addNewOption("3. Remove the food by ID");
        menu.addNewOption("4. Print the food list in the descending order of expired date");
        menu.addNewOption("5. Save to file");
        menu.addNewOption("6. Quit");

        Cabinet fridge = new Cabinet();
        fridge.initFoodList();

        //==========
        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    System.out.println("You are prepare to add a new food");
                    fridge.addNewFood();
                    System.out.println("Done!");
                    break;
                }
                case 2: {
                    System.out.println("You are prepare to search for food by it's name");
                    fridge.searchFoodByName();
                    break;
                }
                case 3: {
                    System.out.println("You are prepare to remove a food by it's ID");
                    fridge.removeFoodById();
                    break;
                }
                case 4: {
                    System.out.println("You are prepare to print the food list (descending by expired date)");
                    fridge.printListDescByDate();
                    break;
                }
                case 5: {
                    System.out.println("Saving to file...");
                    fridge.storeFiles();
                    break;
                }
                case 6: {
                    System.out.println("Good bye!!");
                    break;
                }
            }
        } while (choice != 6);
    }
}
