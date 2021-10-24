package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setLenient(false);
        do {
            try {
                System.out.print(inputMsg);
                date = sc.nextLine();
                
                if(df.parse(date).compareTo(df.parse(df.format(cal.getTime()))) > 0){
                    throw new Exception();
                }
                break;
            } catch (ParseException e) {
                System.out.println(errorMsg);
            } catch (Exception e){
                System.out.println("1st injection date have to be less or equal current date!");
            }
        } while (true);
        return date;
    }

    public static String getDateInAmount(String inputMsg, String errorMsg, String date1, int noWeekBf, int noWeekAf) {
        String date;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setLenient(false);

        do {
            System.out.print(inputMsg);
            date = sc.nextLine();
            if (date.isEmpty()) {
                return null;
            }
            try {
                Calendar calB = Calendar.getInstance();
                Calendar calA = Calendar.getInstance();
                calB.setTime(df.parse(date1));
                calB.add(Calendar.WEEK_OF_YEAR, noWeekBf);
                
                calA.setTime(df.parse(date1));
                calA.add(Calendar.WEEK_OF_YEAR, noWeekAf);
                
                if (df.parse(date).before(calB.getTime()) || df.parse(date).after(calA.getTime())){
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        } while (true);
        return date;
    }
}
