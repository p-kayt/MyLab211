/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtime;

import data.Cabinet;
import ui.Menu;

/**
 *
 * @author Kayt
 */
public class Program {
    public static void main(String[] args) {
        
        Menu menu = new Menu("Vaccine Injection Management");
        menu.addNewOption("1. Show all injection infomation from file injection.dat");
        menu.addNewOption("2. Show all student infomation.");
        menu.addNewOption("3. Add new injection");
        menu.addNewOption("4. Update an injection");
        menu.addNewOption("5. Remove an injection");
        menu.addNewOption("6. Search injection by student ID");
        menu.addNewOption("7. Save to file");
        menu.addNewOption("8. Quit");
        
        Cabinet fl = new Cabinet();
        
        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    fl.showInjInfoFromFlie();
                    break;
                }
                case 2: {
                    fl.showStudentNeedInj();
                    break;
                }
                case 3: {
                    fl.addInjection();
                    break;
                }
                case 4: {
                    fl.updateInjection();
                    break;
                }
                case 5: {
                    fl.deleteInjById();
                    break;
                }
                case 6: {
                    fl.searchInjBySId();
                    break;
                }
                case 7: {
                    fl.storeFiles();
                    break;
                }
                case 8: {
                    System.out.println("Good bye!!");
                    break;
                }
            }
        } while (choice != 8);    
    }
}
