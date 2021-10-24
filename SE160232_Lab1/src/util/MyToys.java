package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author Kayt
 */
public class MyToys {

    private static Scanner sc = new Scanner(System.in);
    

    //nhận data kiểu int
    public static int getAnInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    //nhận data kiểu int trong khoảng (lb...ub)
    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        //nếu đầu vào lower > upper thì đổi chỗ
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    //nhận data kiểu double
    public static double getADouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    //nhận data kiểu double trong khoảng (lb...ub)
    public static double getADouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double n, tmp;
        //nếu đầu vào lower > upper thì đổi chỗ
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    //lấy 1 string không chấp nhận rỗng
    public static String getString(String inputMsg, String errorMsg) {

        String tmp;
        while (true) {
            System.out.print(inputMsg);
            tmp = sc.nextLine().trim();
            if (tmp.length() == 0 || tmp.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return tmp;
            }
        }
    }

    public static String getDateInForm(String inputMsg, String errorMsg) {
        String date;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        //tránh cộng ngày tháng
        df.setLenient(false);
        do {
            try {
                System.out.print(inputMsg);
                System.out.print("(dd-MM-yyyy):");
                date = sc.nextLine();
                //regex chặn nhập yyyy/M/d
                //vì liên quan đến sort !!
//                boolean flag = date.matches("\\d{2}-\\d{2}-\\d{4}");
//                if(!flag){
//                    throw new Exception();
//                }
                df.parse(date);
                break;
            } catch (ParseException e) {
                System.out.println(errorMsg);
            } 
//            catch (Exception e){
//                System.out.println(errorMsg);
//            }
        } while (true);
        return date;

    }
}
