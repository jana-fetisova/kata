import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        System.out.println(calc(str));
    }

    public static String calc(String input) {
        String[] arr = input.split("");

        //убираем пробелы
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].equals(" ")) {
                arr[i] = "";
            }
        }

        // проверяем, чтобы оба числа были или арабские, или римские
        boolean romFlag = false;
        boolean arabFlag = false;
        String flag = "ok";
        String[] romArr = {"I", "V", "X", "C", "D", "M", "L"};
        String[] arabArr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < romArr.length; j++) {
                if(arr[i].equals(romArr[j])) {
                    romFlag = true;
                }
            }
        }
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arabArr.length; j++) {
                if(arr[i].equals(arabArr[j])) {
                    arabFlag = true;
                }
            }
        }
        if(romFlag==arabFlag) {
            flag = "mist";
            throw new NumberFormatException("Калькулятор умеет работать или с арабскими, или с римскими числами одновременно");
        }

        // проверяем арифметическую операцию на корректность
        boolean arifFlag = false;
        int arifQ = 0;
        String[] arifArr = {"+", "-", "*", "/"};
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arifArr.length; j++) {
                if(arr[i].equals(arifArr[j])) {
                    arifFlag = true;
                    arifQ = arifQ + 1;
                }
            }
        }
        if(!arifFlag | arifQ > 1) {
            throw new NumberFormatException("Введите корректную арифметическую операцию");
        }

        // распознаем первое число
        String num1 = "";
        boolean num1IsAppointed = false;
        for(int i = 0; i < arr.length; i++) {
            if(num1IsAppointed == false) {
                switch (arr[i]) {
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        num1IsAppointed = true;
                        break;
                    default:
                        num1 = num1 + arr[i];
                }
            }
        }

        // распознаем второе число
        String num2 = "";
        for(int i = 0; i < arr.length; i++) {
            switch(arr[i]) {
                case "+" :
                case "-" :
                case "*" :
                case "/" :
                    for(int j = i+1; j < arr.length; j++) {
                        num2 = num2 + arr[j];
                    }

            }
        }

        // переводим первое число в int
        int intnum1 = 0;
        if(arabFlag==true) {
            intnum1 = Integer.parseInt(num1);
        } else {
            String[] num1arr = num1.split("");
            switch(num1arr[0]) {
                case "M":
                    intnum1 = intnum1 + 1000;
                    break;
                case "D":
                    intnum1 = intnum1 + 500;
                    break;
                case "C":
                    intnum1 = intnum1 + 100;
                    break;
                case "L":
                    intnum1 = intnum1 + 50;
                    break;
                case "X":
                    intnum1 = intnum1 + 10;
                    break;
                case "V":
                    intnum1 = intnum1 + 5;
                    break;
                case "I":
                    intnum1 = intnum1 + 1;
                    break;
            }
            for(int i = 1; i < num1arr.length; i++) {
                switch(num1arr[i]) {
                    case "M" :
                        if(num1arr[i-1].equals("C")) {
                            intnum1 = intnum1 - 100 + 900;
                            break;
                        } else {
                            intnum1 = intnum1 + 1000;
                            break;
                        }
                    case "D" :
                        if(num1arr[i-1].equals("C")) {
                            intnum1 = intnum1 - 100 + 400;
                            break;
                        } else {
                            intnum1 = intnum1 + 500;
                            break;
                        }
                    case "C" :
                        if(num1arr[i-1].equals("X")) {
                            intnum1 = intnum1 - 10 + 90;
                            break;
                        } else {
                            intnum1 = intnum1 + 100;
                            break;
                        }
                    case "L" :
                        if(num1arr[i-1].equals("X")) {
                            intnum1 = intnum1 - 10 + 40;
                            break;
                        } else {
                            intnum1 = intnum1 + 50;
                            break;
                        }
                    case "X" :
                        if(num1arr[i-1].equals("I")) {
                            intnum1 = intnum1 - 1 + 9;
                            break;
                        } else {
                            intnum1 = intnum1 + 10;
                            break;
                        }
                    case "V" :
                        if(num1arr[i-1].equals("I")) {
                            intnum1 = intnum1 - 1 + 4;
                            break;
                        } else {
                            intnum1 = intnum1 + 5;
                            break;
                        }
                    case "I" :
                        intnum1 = intnum1 + 1;
                }
            }
        }

        // переводим второе число в int
        int intnum2 = 0;
        if(arabFlag==true) {
            intnum2 = Integer.parseInt(num2);
        } else {
            String[] num2arr = num2.split("");
            switch(num2arr[0]) {
                case "M":
                    intnum2 = intnum2 + 1000;
                    break;
                case "D":
                    intnum2 = intnum2 + 500;
                    break;
                case "C":
                    intnum2 = intnum2 + 100;
                    break;
                case "L":
                    intnum2 = intnum2 + 50;
                    break;
                case "X":
                    intnum2 = intnum2 + 10;
                    break;
                case "V":
                    intnum2 = intnum2 + 5;
                    break;
                case "I":
                    intnum2 = intnum2 + 1;
                    break;
            }
            for(int i = 1; i < num2arr.length; i++) {
                switch(num2arr[i]) {
                    case "M" :
                        if(num2arr[i-1].equals("C")) {
                            intnum2 = intnum2 - 100 + 900;
                            break;
                        } else {
                            intnum2 = intnum2 + 1000;
                            break;
                        }
                    case "D" :
                        if(num2arr[i-1].equals("C")) {
                            intnum2 = intnum2 - 100 + 400;
                            break;
                        } else {
                            intnum2 = intnum2 + 500;
                            break;
                        }
                    case "C" :
                        if(num2arr[i-1].equals("X")) {
                            intnum2 = intnum2 - 10 + 90;
                            break;
                        } else {
                            intnum2 = intnum2 + 100;
                            break;
                        }
                    case "L" :
                        if(num2arr[i-1].equals("X")) {
                            intnum2 = intnum2 - 10 + 40;
                            break;
                        } else {
                            intnum2 = intnum2 + 50;
                            break;
                        }
                    case "X" :
                        if(num2arr[i-1].equals("I")) {
                            intnum2 = intnum2 - 1 + 9;
                            break;
                        } else {
                            intnum2 = intnum2 + 10;
                            break;
                        }
                    case "V" :
                        if(num2arr[i-1].equals("I")) {
                            intnum2 = intnum2 - 1 + 4;
                            break;
                        } else {
                            intnum2 = intnum2 + 5;
                            break;
                        }
                    case "I" :
                        intnum2 = intnum2 + 1;
                }
            }
        }

        if(intnum1 > 10 | intnum2 > 10) {
            throw new NumberFormatException("Каждое число должно быть не больше 10");
        }

        // производим операцию над двумя числами
        String strresult = "";
        int result = 0;
        for(int i = 0; i < arr.length; i++) {
            switch(arr[i]) {
                case "+" :
                    result = intnum1 + intnum2;
                    break;
                case "-" :
                    result = intnum1 - intnum2;
                    break;
                case "*" :
                    result = intnum1 * intnum2;
                    break;
                case "/" :
                    result = intnum1 / intnum2;
                    break;

            }
        }

        // проверяем, чтобы итог операции с римскими числами не был отрицательным
        if(romFlag) {
            if(result < 1) {
                throw new NumberFormatException("Результат операции с римскими числами не должен быть меньше I");
            }
        }

        if(arabFlag) {
            strresult = String.valueOf(result);
        } else {
            int thousands = result / 1000;
            int hundreds = result % 1000 / 100;
            int tens = result % 1000 % 100 / 10;
            int units = result % 1000 % 100 % 10;
            for (int i = 0; i < thousands; i++) {
                strresult = strresult + "M";
            }
            if(hundreds > 0) {
                switch(hundreds) {
                    case 1:
                        strresult = strresult + "C";
                        break;
                    case 2:
                        strresult = strresult + "CC";
                        break;
                    case 3:
                        strresult = strresult + "CCC";
                        break;
                    case 4:
                        strresult = strresult + "CD";
                        break;
                    case 5:
                        strresult = strresult + "D";
                        break;
                    case 6:
                        strresult = strresult + "DC";
                        break;
                    case 7:
                        strresult = strresult + "DCC";
                        break;
                    case 8:
                        strresult = strresult + "DCCC";
                        break;
                    case 9:
                        strresult = strresult + "CM";
                        break;
                }
            }
            if(tens > 0) {
                switch (tens) {
                    case 1:
                        strresult = strresult + "X";
                        break;
                    case 2:
                        strresult = strresult + "XX";
                        break;
                    case 3:
                        strresult = strresult + "XXX";
                        break;
                    case 4:
                        strresult = strresult + "XL";
                        break;
                    case 5:
                        strresult = strresult + "L";
                        break;
                    case 6:
                        strresult = strresult + "LX";
                        break;
                    case 7:
                        strresult = strresult + "LXX";
                        break;
                    case 8:
                        strresult = strresult + "LXXX";
                        break;
                    case 9:
                        strresult = strresult + "XC";
                        break;
                }
            }
            if(units > 0) {
                switch (units) {
                    case 1:
                        strresult = strresult + "I";
                        break;
                    case 2:
                        strresult = strresult + "II";
                        break;
                    case 3:
                        strresult = strresult + "III";
                        break;
                    case 4:
                        strresult = strresult + "IV";
                        break;
                    case 5:
                        strresult = strresult + "V";
                        break;
                    case 6:
                        strresult = strresult + "VI";
                        break;
                    case 7:
                        strresult = strresult + "VII";
                        break;
                    case 8:
                        strresult = strresult + "VIII";
                        break;
                    case 9:
                        strresult = strresult + "IX";
                        break;
                }
            }
        }

        //return String.valueOf(units);
        return strresult;

    }
}
