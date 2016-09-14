import java.util.*;
import java.lang.Math;
/**
 * Created by Валентин on 14.09.2016.
 */
public class TranslatorNS {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите исходное число: ");
        String enterNum = scan.nextLine();
        System.out.println("Введите основание СС исх. числа: ");
        int enterNumSystem = scan.nextInt();
        System.out.println("Введите основание СС, в которую хотите перевести: ");
        int exitNumSystem = scan.nextInt();
        System.out.println(Translator(enterNum, enterNumSystem, exitNumSystem));
    }

    static String Translator(String enterNum, int enterNumSystem, int exitNumSystem) {
        StringBuilder buf;
        String check = "";
        String zPart;
        String dPart;
        String exit = "";
        int iDot = 0;
        int z10 = 0;
        double d10 = 0;

        //Выделение целой и дробной части
        for(int i = 0; i < enterNum.length(); i++) {
            check += enterNum.charAt(i);
            if (check.equals(".") || check.equals(",")){
                iDot = i;
            }
            check = "";
        }
        if(iDot == 0){
            buf = new StringBuilder (enterNum);
        } else {
            buf = new StringBuilder (enterNum.substring(0, iDot));
        }
        zPart = buf.reverse().toString();
        dPart = enterNum.substring(iDot+1, enterNum.length());;

        //Перевод исходного числа в десятичную СС
        for(int i = 0; i < zPart.length(); i++) {
            z10 += Integer.parseInt("" + zPart.charAt(i)) * Math.pow(enterNumSystem, i);
        }
        for(int i = 0; i < dPart.length(); i++) {
            d10 += Integer.parseInt("" + dPart.charAt(i)) * Math.pow(enterNumSystem, -(i+1));
        }

        //Перевод целой части в новую СС
        while(z10 >= exitNumSystem){
            exit += Integer.toString(z10 % exitNumSystem);
            z10 /=  exitNumSystem;
        }
        exit += Integer.toString(z10 % exitNumSystem);
        buf = new StringBuilder (exit);
        exit = buf.reverse().toString();


        //Перевод дробной части в новую СС
        if(d10 != 0) {
            exit += ".";
            while (d10 != 1) {
                d10 *= exitNumSystem;
                exit += Integer.toString((int) d10);
            }
        }

        System.out.println("Результат: ");
        return exit;
    }
}
