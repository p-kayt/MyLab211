/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;
import util.MyToys;

/**
 *
 * @author Kayt
 */
public class Cabinet {

    public Cabinet() {
    }

    private ArrayList<Food> foodList = new ArrayList();
    private Scanner sc = new Scanner(System.in);
    String placeList[] = {"Freezer", "Cooler", "Others"};

    public void addNewFood() {
        String tmpId;
        String name;
        double weight;
        String type;
        String place;
        String expiredDate;

        int pos;
        do {
            tmpId = MyToys.getString("Food's ID: ", "Food's ID can not be empty!").toUpperCase();
            //tìm kiếm trong danh sách
            pos = searchFoodByID(tmpId);
            if (pos >= 0) {
                System.out.println("ID is already exist. Please input another one.");
            }
            //lặp lại đến khi pos = -1 => tìm k thấy
        } while (pos != -1);
        //name
        name = MyToys.getString("Food's name: ", "Food's name can not be empty!");

        //weight
        do {
            weight = MyToys.getADouble("Food's weight (kg): ", "Food's weight must be a number!");
            if (weight >= 0) {
                break;
            } else {
                System.out.println("Food's weight can not less than 0!");
            }
        } while (true);

        //type
        type = MyToys.getString("Food's type: ", "Food's type can not be empty!");

        //place
        do {
            place = MyToys.getString("Food's place (Cooler, Freezer or Others): ", "Please choose Cooler, Freezer or Others");
            if (searchStr(place)) {
                break;
            }
            System.out.println("Place is not avalible, please choose Cooler, Freezer or Others");
        } while (!searchStr(place));

        //expiredDate
        expiredDate = MyToys.getDateInForm("Food's expired date", "Food's expired date is invalid!");

        //add
        foodList.add(new Food(tmpId, name, weight, type, place, expiredDate));
    }

    public void searchFoodByName() {
        String keyName;
        boolean flag = false;

        if (foodList.isEmpty()) {
            System.out.println("List is empty!!");
            return;
        }
        keyName = MyToys.getString("Food's name:", "Food's name can not be empty!");

        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getName().contains(keyName)) {
                foodList.get(i).show();
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Food can not be found.");
        }

    }

    public void removeFoodById() {
        String keyId;
        String keyOp;
        if (foodList.isEmpty()) {
            System.out.println("List is empty!!");
            return;
        }
        keyId = MyToys.getString("Food's ID: ", "Food's ID can not be empty!").toUpperCase();
        int pos = searchFoodByID(keyId);
        if (pos == -1) {
            System.out.println("Not found!!");
        } else {
            System.out.print("Food's infomation:");
            foodList.get(pos).show();
            System.out.println("Do you want to remove this item ?");
            do {
                keyOp = MyToys.getString("[y/N]?:", "");
                if (keyOp.equalsIgnoreCase("N")) {
                    System.out.print("Cancelled");
                    return;
                } else if (keyOp.equalsIgnoreCase("y")) {
                    break;
                }
            } while (!keyOp.equalsIgnoreCase("y") && !keyOp.equalsIgnoreCase("N"));

            foodList.remove(pos);
            System.out.println("The selected food is removed.");
        }
    }

    public void printListDescByDate() {
        if (foodList.isEmpty()) {
            System.out.println("List is empty!!");
            return;
        }
        Collections.sort(foodList, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                int rs = 0;
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    rs = df.parse(o1.getExpiredDate()).compareTo(df.parse(o2.getExpiredDate()));
                } catch (Exception e) {
                    System.out.println(e);
                }
//                   return o1.getExpiredDate().compareToIgnoreCase(o2.getExpiredDate());
                return rs;
            }
        });

        for (Food o : foodList) {
            o.show();
        }

    }

    public void storeFiles() {
        File file;
        boolean isCreate = false;
        System.out.print("Input file's name: ");
        String name = sc.nextLine();
        try {
            file = new File(name);

            isCreate = file.createNewFile();
            if (isCreate) {
                System.out.println("Created");
            } else {
                System.out.println("File's name is already exist!");
            }

        } catch (IOException e) {
            System.out.print(e);
        }
        if (isCreate) {
            try {
                FileWriter writer = new FileWriter(name);
                for (Food str : foodList) {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
            } catch (Exception e) {
                System.out.println("Saving falied!!");
            }
        }

    }

//================================================================================
    //sub func trả vị trí trong list
    public int searchFoodByID(String foodID) {
        if (foodList.isEmpty()) {
            return -1; //ko tìm gì cả
        }
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getId().equalsIgnoreCase(foodID)) {
                return i;
            }
        }
        return -1;
    }

    private boolean searchStr(String key) {
        for (int i = 0; i < placeList.length; i++) {
            if (key.equalsIgnoreCase(placeList[i])) {
                return true;
            }
        }
        return false;
    }
//test

    public void initFoodList() {
        foodList.add(new Food("F1", "fish", 2, "Raw Food", "freezer", "1-10-2021"));
        foodList.add(new Food("F2", "cake", 3, "Snack", "cooler", "1-12-2021"));
        foodList.add(new Food("F3", "ice cream", 1, "Snack", "freezer", "15-2-2021"));
        foodList.add(new Food("F4", "whipped cream", 1, "Ingredient", "others", "1-1-2021"));
    }
}
