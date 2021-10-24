/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Kayt
 */
public class Student {
    private String id;
    private String name;
    public int injNum = 0;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInjNum() {
        return injNum;
    }

    public void setInjNum(int injNum) {
        this.injNum = injNum;
    }

    @Override
    public String toString() {
        return String.format("%-12s| %-15s| %-5d", id, name, injNum); //!!!!!!!!! viết vào file
    }
    
    public void showIDN(){
        System.out.println(String.format("%-10s| %-15s", id, name));
    }
    
}
