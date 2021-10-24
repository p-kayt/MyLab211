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
public class Injection {
    private String injId;
    private String studentId;
    private String vaccineId;
    
    private String date1;
    private String place1;
    
    private String date2 = null;
    private String place2 = null;

    private boolean m1;
    private boolean m2 = false;
    public Injection() {
    }

    public Injection(String injId, String studentId, String vaccineId, String date1, String place1, boolean m1) {
        this.injId = injId;
        this.studentId = studentId;
        this.vaccineId = vaccineId;
        this.date1 = date1;
        this.place1 = place1;
        this.m1 = m1;
    }
    
    public Injection(String injId, String studentId, String vaccineId, String date1, String place1, boolean m1, String date2, String place2, boolean m2) {
        this.injId = injId;
        this.studentId = studentId;
        this.vaccineId = vaccineId;
        this.date1 = date1;
        this.place1 = place1;
        this.m1 = m1;
        this.date2 = date2;
        this.place2 = place2;
        this.m2 = m2;
    }

    public boolean isM1() {
        return m1;
    }

    public void setM1(boolean m1) {
        this.m1 = m1;
    }

    public boolean isM2() {
        return m2;
    }

    public void setM2(boolean m2) {
        this.m2 = m2;
    }

    
    

    public String getInjId() {
        return injId;
    }

//    public void setInjId(String injId) {
//        this.injId = injId;
//    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-10s| %-14s | %-20s | %-20s | %-20s | %-20s",
                            injId, studentId, vaccineId, date1, place1, date2, place2);
    }
    
    
}
