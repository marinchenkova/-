import java.util.Scanner;

//Без использования дополнительных библиотек, кроме библиотек ввода/вывода
public class TranslatorNS {

    //Метод возведения в степень
    static double pow(int a, int b){
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

    //Преобразование символов 0-9, a-f в 10-ю СС
    static int transformTo10 (char ch){
        int num = 0;
        switch (ch) {
            case '0': num = 0; break;
            case '1': num = 1; break;
            case '2': num = 2; break;
            case '3': num = 3; break;
            case '4': num = 4; break;
            case '5': num = 5; break;
            case '6': num = 6; break;
            case '7': num = 7; break;
            case '8': num = 8; break;
            case '9': num = 9; break;
            case 'A': num = 10; break;
            case 'a': num = 10; break;
            case 'B': num = 11; break;
            case 'b': num = 11; break;
            case 'C': num = 12; break;
            case 'c': num = 12; break;
            case 'D': num = 13; break;
            case 'd': num = 13; break;
            case 'E': num = 14; break;
            case 'e': num = 14; break;
            case 'F': num = 15; break;
            case 'f': num = 15; break;
        }
        return num;
    }


    //Преобразование чисел 0-15 в знаки 0-9, a-f
    static char transformTo16 (int num) {
        char ch = '0';
        switch (num){
            case 0: ch = '0'; break;
            case 1: ch = '1'; break;
            case 2: ch = '2'; break;
            case 3: ch = '3'; break;
            case 4: ch = '4'; break;
            case 5: ch = '5'; break;
            case 6: ch = '6'; break;
            case 7: ch = '7'; break;
            case 8: ch = '8'; break;
            case 9: ch = '9'; break;
            case 10: ch = 'A'; break;
            case 11: ch = 'B'; break;
            case 12: ch = 'C'; break;
            case 13: ch = 'D'; break;
            case 14: ch = 'E'; break;
            case 15: ch = 'F'; break;
        }
        return ch;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter number: ");
        String enterNum = scan.nextLine();

        System.out.println("Enter NS of this number: ");
        int enterNumSystem = scan.nextInt();

        System.out.println("Enter new NS: ");
        int exitNumSystem = scan.nextInt();

        System.out.println(translator(enterNum, enterNumSystem, exitNumSystem));
    }

    static String translator(String enterNum, int enterNumSystem, int exitNumSystem) {
        StringBuilder buf;
        String check = "";
        String exitNum = "";
        String zPart;
        String dPart;
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
            z10 += transformTo10(zPart.charAt(i)) * pow(enterNumSystem, i);
        }
        for(int i = 1; i <= dPart.length(); i++) {
            d10 += transformTo10(dPart.charAt(i-1)) * pow(enterNumSystem, -i);
        }


        //Перевод целой части в новую СС
        while(z10 >= exitNumSystem){
            exitNum += transformTo16(z10 % exitNumSystem);
            z10 /=  exitNumSystem;
        }
        exitNum += transformTo16(z10 % exitNumSystem);
        buf = new StringBuilder (exitNum);
        exitNum = buf.reverse().toString();


        //Перевод дробной части в новую СС
        if(d10 != 0) {
            exitNum += ".";
            while (d10 % 1 != 0) {
                d10 *= exitNumSystem;
                exitNum += transformTo16((int) d10);
                d10 -= (int) d10;
            }
        }


        System.out.println("Result (String): ");
        return exitNum;
    }
}
