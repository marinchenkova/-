import java.util.Scanner;

//Без использования дополнительных библиотек, кроме библиотек ввода/вывода
public class TranslatorNS {

    //Метод возведения в степень
    static double Pow(int a, int b){
        double buf = 1;
        if (b > 0){
            for(int i = 1; i <= b; i++){
                buf *= a;
            }
        } else if (b <= 0){
            for(int i = 0; i > b; i--){
                buf /= a;
            }
        }
        return  buf;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number: ");
        String enterNum = scan.nextLine();
        System.out.println("Enter NS of this number: ");
        int enterNumSystem = scan.nextInt();
        System.out.println("Enter new NS: ");
        int exitNumSystem = scan.nextInt();
        System.out.println(Translator(enterNum, enterNumSystem, exitNumSystem));
    }

    static String Translator(String enterNum, int enterNumSystem, int exitNumSystem) {
        StringBuilder buf;
        String check = "";
        String zPart = "";
        String dPart = "";
        String exitNum = "";
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
            dPart = "0";
        } else {
            buf = new StringBuilder (enterNum.substring(0, iDot));
            dPart = enterNum.substring(iDot+1, enterNum.length());
        }
        zPart = buf.reverse().toString();

        //Перевод исходного числа в десятичную СС
        for(int i = 0; i < zPart.length(); i++) {
            z10 += Integer.parseInt("" + zPart.charAt(i)) * Pow(enterNumSystem, i);
        }
        for(int i = 1; i <= dPart.length(); i++) {
            d10 += Integer.parseInt("" + dPart.charAt(i-1)) * Pow(enterNumSystem, -i);
        }

        //Перевод целой части в новую СС
        while(z10 >= exitNumSystem){
            exitNum += Integer.toString(z10 % exitNumSystem);
            z10 /=  exitNumSystem;
        }
        exitNum += Integer.toString(z10 % exitNumSystem);
        buf = new StringBuilder (exitNum);
        exitNum = buf.reverse().toString();

        //Перевод дробной части в новую СС
        if(d10 != 0) {
            exitNum += ".";
            while (d10 % 1 != 0) {
                d10 *= exitNumSystem;
                exitNum += Integer.toString((int) d10);
                d10 -= (int) d10;
            }
        }

        System.out.println(zPart + "/" + dPart);
        System.out.println(d10);
        System.out.println("Result: ");
        return exitNum;
    }
}
