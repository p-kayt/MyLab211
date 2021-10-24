/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import util.MyToys;

/**
 *
 * @author Kayt
 */
public class Cabinet {

    private List<Vaccine> vList = new ArrayList<>();
    private List<Student> sList = new ArrayList<>();
    private List<Injection> injList = new ArrayList<>();

    public Cabinet() {
        vaccineWFile();
        studentWFile();
        loadFromFile();
    }

    public void showStudentNeedInj() {
        System.out.println("-------------------------");
        System.out.println("Student infomation:");
        System.out.println("-------------------------");
        System.out.println(String.format("%-10s|%-15s", "ID", "Name"));
        for (Student o : sList) {
            o.showIDN();
        }
    }

    public void storeFiles() {
//        File file;
        System.out.print("Saving to injection.dat...");
        sortByInjId();
        try {
            FileWriter writer = new FileWriter("injection.dat");
            for (Injection str : injList) {
                writer.write(str.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Saving falied!!");
        }
        System.out.println("Saved!");
    }
    //student's id
    public void searchInjBySId() {
        if (injList.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        String keyId = MyToys.getString("Student's ID to search: ", "Student's ID can not empty!");
        int pos = searchSIdInj(keyId);
        if (pos == -1) {
            System.out.println("Not found!");
        } else {
            System.out.println(String.format("%-5s | %-10s| %-14s | %-20s | %-20s | %-20s | %-20s",
                    "ID", "Student ID", "Vaccine ID", "1st Injection Date",
                    "1st Injection Place", "2nd Injection Date", "2nd Injection Place"));
            System.out.println(injList.get(pos).toString());
        }
    }
    //injection's id
    public void deleteInjById() {
        String keyId;
        String keyOp;

        if (injList.isEmpty()) {
            System.out.println("List is empty!!");
            return;
        }
        keyId = MyToys.getString("Injection's ID to be removed: ", "Injection's ID can not empty!");
        int pos = searchId(keyId);
        if (pos == -1) {
            System.out.println("Not found!!");
        } else {
            System.out.println(String.format("%-5s | %-10s| %-14s | %-20s | %-20s | %-20s | %-20s",
                    "ID", "Student ID", "Vaccine ID", "1st Injection Date",
                    "1st Injection Place", "2nd Injection Date", "2nd Injection Place"));
            System.out.println(injList.get(pos).toString());
            System.out.println("Remove this injection?");
            do {
                keyOp = MyToys.getString("[y/N]?:", "");
                if (keyOp.equalsIgnoreCase("N")) {
                    System.out.print("Cancelled");
                    return;
                } else if (keyOp.equalsIgnoreCase("y")) {
                    break;
                }
            } while (!keyOp.equalsIgnoreCase("y") && !keyOp.equalsIgnoreCase("N"));

            injList.remove(pos);
            System.out.println("The selected injection is removed.");
        }
    }
    //update 2nd dose (mũi 2)
    public void updateInjection() {
        String date2;
        String place2;

        if (injList.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        String keyId = MyToys.getString("Injection's ID to be updated: ", "Injection's ID can not empty!");
        int pos = searchId(keyId);
        if (pos == -1) {
            System.out.println("Not found!");
            return;
        }
        System.out.println(String.format("%-5s | %-10s| %-14s | %-20s | %-20s | %-20s | %-20s",
                "ID", "Student ID", "Vaccine ID", "1st Injection Date",
                "1st Injection Place", "2nd Injection Date", "2nd Injection Place"));
        System.out.println(injList.get(pos).toString());

        if (injList.get(pos).isM2()) {
            System.out.println("This student has received maximum amount of dose!");
            return;
        }

        date2 = MyToys.getDateInAmount("Date of 2nd injection (dd-MM-yyyy): ",
                "Wrong date form or the second dose not given 4 to 12 weeks after the first injection", injList.get(pos).getDate1(), 4, 12);

        place2 = MyToys.getString("Place of 2st dose: ", "Cannot leave empty!");

        injList.get(pos).setDate2(date2);
        injList.get(pos).setPlace2(place2);
        injList.get(pos).setM2(true);
        System.out.println("Student has completed 2 injections!");
    }
    //
    public void addInjection() {
        String injId;
        String studentId;
        String vaccineId;
        String date1;
        String place1;

        do {
            String keyOp;
            int pos;
            do {
                injId = MyToys.getString("Injection's ID: ", "Injection's ID can not empty!").toUpperCase();
                pos = searchId(injId);
                if (pos != -1) {
                    System.out.println("ID existed. Input again!");
                }
//                if (!injId.matches("^[a-z A-Z 0-9]+")){
//                    System.out.println("uhh");
//                }
            } while (pos != -1);

            do {
//                System.out.println("-------------------------");
//                System.out.println("Student infomation:");
//                System.out.println("-------------------------");
//                System.out.println(String.format("%-10s|%-15s", "ID", "Name"));
//                sList.forEach((o) -> {
//                    o.showIDN();
//                });
                studentId = MyToys.getString("Student's ID: ", "Student's ID can not empty!").toUpperCase();
                pos = searchSId(studentId);
                if (pos == -1) {
                    System.out.println("ID is not exsit, Input an available ID!");
                } else if (sList.get(pos).getInjNum() == 2) {
                    System.out.println("This student has recived maximum number of dose");
                    pos = -1;
                } else if (searchSIdInj(studentId) != -1) {
                    System.out.println("This student is already in the list, please move to update to update infomation!");
                    pos = -1;
                    do {
                        System.out.println("Return to menu?");
                        keyOp = MyToys.getString("[y/N]:", "");
                        if (keyOp.equalsIgnoreCase("y")) {
                            System.out.print("Cancelled");
                            return;
                        } else if (keyOp.equalsIgnoreCase("N")) {
                            break;
                        }
                    } while (!keyOp.equalsIgnoreCase("y") && !keyOp.equalsIgnoreCase("N"));
                }
            } while (pos == -1);
            System.out.println("-------------------------");
            System.out.println("Vaccine infomation:");
            System.out.println("-------------------------");
            System.out.println(String.format("%-15s|%-20s", "ID", "Name"));
            vList.forEach((o) -> {
                System.out.println(o.toString());
            });
            do {
                vaccineId = MyToys.getString("Vaccine's ID: ", "Vaccine's ID can not empty!");
                pos = searchVId(vaccineId);
                if (pos == -1) {
                    System.out.println("ID is not exsit, Input an available ID!");
                }
//                else if(!injList.get(searchSIdInj(studentId)).getVaccineId().equalsIgnoreCase(vaccineId) && sList.get(pos).getInjNum() == 1){
//                System.out.println("Vaccine must be same type with 1st dose!");
//            }
            } while (pos == -1);

            date1 = MyToys.getDateInForm("Date of 1st injection (dd-MM-yyyy): ", "Invalid date!");
            place1 = MyToys.getString("Place of 1st dose: ", "Cannot leave empty!");

            injList.add(new Injection(injId, studentId, vaccineId, date1, place1, true));
            sList.get(searchSId(studentId)).injNum++;
            do {
                System.out.println("Continute adding?");
                keyOp = MyToys.getString("[y/N]:", "");
                if (keyOp.equalsIgnoreCase("N")) {
                    System.out.print("Cancelled");
                    return;
                } else if (keyOp.equalsIgnoreCase("y")) {
                    break;
                }
            } while (!keyOp.equalsIgnoreCase("y") && !keyOp.equalsIgnoreCase("N"));

        } while (true);
    }

    //không cần nữa
    public void showInjInfoFromFlie() {
        String injId;
        String studentId;
        String vaccineId;
        String date1;
        String place1;
        String date2;
        String place2;
//        String fileName = MyToys.getString("File's Name:", "File name cannot empty!");
        String fileName = "injection.dat";

        System.out.println("INJECTION INFOMATION");
        System.out.println("--------------------------");
        System.out.println(String.format("%-5s | %-10s| %-14s | %-20s | %-20s | %-20s | %-20s",
                "ID", "Student ID", "Vaccine ID", "1st Injection Date",
                "1st Injection Place", "2nd Injection Date", "2nd Injection Place"));

        try {
            File f = new File(fileName);
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("\\s+\\|\\s+");
//                System.out.println(split[4]);
                injId = split[0];

                studentId = split[1];

                vaccineId = split[2];

                date1 = split[3];
                place1 = split[4];

                if (!split[5].equalsIgnoreCase("null")) {
                    date2 = split[5];
                } else {
                    date2 = null;
                }

                if (!split[6].equalsIgnoreCase("null")) {
                    place2 = split[6];
                } else {
                    place2 = null;
                }

                System.out.println(String.format("%-5s | %-10s| %-14s | %-20s | %-20s | %-20s | %-20s",
                        injId, studentId, vaccineId, date1, place1, date2, place2));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Reading failed!");
        }
    }
//------------------------------------------------------------------------------

    private int searchId(String key) {
        if (injList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < injList.size(); i++) {
            if (key.equals(injList.get(i).getInjId())) {
                return i;
            }
        }
        return -1;
    }

    private int searchSIdInj(String key) {
        if (injList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < injList.size(); i++) {
            if (key.equalsIgnoreCase(injList.get(i).getStudentId())) {
                return i;
            }
        }
        return -1;
    }

    private int searchSId(String key) {
        if (sList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < sList.size(); i++) {
            if (key.equalsIgnoreCase(sList.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    private int searchVId(String key) {
        if (vList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < vList.size(); i++) {
            if (key.equalsIgnoreCase(vList.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

//------------------------------------------------------------------------------
    private void sortByInjId() {
        if (injList.isEmpty()) {
            return;
        }
        Collections.sort(injList, new Comparator<Injection>() {
            @Override
            public int compare(Injection o1, Injection o2) {
                return o1.getInjId().compareToIgnoreCase(o2.getInjId());
            }
        });
    }

    private void loadFromFile() {
        String injId;
        String studentId;
        String vaccineId;
        String date1;
        String place1;
        String date2;
        String place2;
        boolean m2 = false;
//        String fileName = MyToys.getString("File's Name:", "File name cannot empty!");
        String fileName = "injection.dat";

        try {
            File f = new File(fileName);
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split("\\s+\\|\\s+");
                injId = split[0];

                studentId = split[1];

                vaccineId = split[2];

                date1 = split[3];
                place1 = split[4];

                if (split[5].equalsIgnoreCase("null")) {
                    date2 = null;
                    m2 = false;
                } else {
                    m2 = true;
                    date2 = split[5];
                }

                if (!split[6].equalsIgnoreCase("null")) {
                    place2 = split[6];
                } else {
                    place2 = null;
                }

                injList.add(new Injection(injId, studentId, vaccineId, date1, place1, true, date2, place2, m2));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Reading failed!");
        }
    }

    private void vaccineWFile() {
        try {
            String fileName = "vaccine.dat";
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            vList.add(new Vaccine("Covid-V001", "AstraZeneca"));
            vList.add(new Vaccine("Covid-V002", "SPUTNIK V"));
            vList.add(new Vaccine("Covid-V003", "Vero Cell"));
            vList.add(new Vaccine("Covid-V004", "Pfizer"));
            vList.add(new Vaccine("Covid-V005", "Moderna"));
            for (Vaccine o : vList) {
                oStream.writeObject(o);
            }
            oStream.close();
            file.close();
        } catch (IOException e) {
        }
    }

    private void studentWFile() {
        try {
            String fileName = "student.dat";
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            sList.add(new Student("SE1500", "Hoa Doan"));
            sList.add(new Student("SE1501", "Le Binh"));
            sList.add(new Student("SE1502", "Hoang Nguyen"));
            sList.add(new Student("SE1503", "Van An"));
            sList.add(new Student("SE1504", "Huy Hương"));
            sList.add(new Student("SE1505", "Long Đình"));
            sList.add(new Student("SE1506", "Lê Khang"));
            sList.add(new Student("SE1507", "Hoài Vũ"));
            sList.add(new Student("SE1508", "Phạm Duy"));
            sList.add(new Student("SE1509", "Lê Mười"));
            for (Student o : sList) {
                oStream.writeObject(o);
            }
            oStream.close();
            file.close();
        } catch (IOException e) {
        }
    }
}
