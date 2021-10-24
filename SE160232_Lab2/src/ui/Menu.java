package ui;

import java.util.ArrayList;
import util.MyToys;

/**
 *
 * @author Kayt
 */
public class Menu {

    private ArrayList<String> optionList = new ArrayList();
    //tên menu
    private String menuTitle;

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }
    
    public void addNewOption(String newOption) {
        optionList.add(newOption);        
    }
    
    
    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("There is no item in the menu");
            return;
        }
        System.out.println("\n------------------------------------");
        //tittle (Vaccine Management)
        System.out.println("Welcome to " + menuTitle + " - @2021 by SE160232 - PHAM KHAC TRIEU");
        for (String x : optionList)
            System.out.println(x);  
    }
    
    public int getChoice() {
        //số lượng các lựa chọn tron menu
        int maxOption = optionList.size();
        return MyToys.getAnInteger("Choose [1.." + maxOption + "]: ", "You are required to choose between 1 to " + maxOption, 1, maxOption);
        //int (lb and ub)
    }
}
