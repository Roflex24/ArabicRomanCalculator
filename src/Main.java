import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static char operation;
    static int number1;
    static int number2;
    static int result;

    public static void main(String[] args) {
        //Запуск цикла с калькулятором
        while (true) {
            System.out.println("Введите выражение арабскими числами (10-2) или римскими (V + IV) в диопозоне чисел от 1(I) до 10(X). Для выхода из программы введите exit");
            String input = scanner.nextLine();
            if (input.contains("exit")) {
                System.out.println("Выход из приложения...");
                System.exit(0);
            }
            else {
                //Убираем пробелы
                input = input.replaceAll("\\s+", "");
                //Определяем операцию
                operation = operation(input);
                //Делим выражение по цифрам через знак
                String[] numbers = input.split("[-+/*]");
                if (numbers.length > 2) throw new RuntimeException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                if (numbers.length < 2) throw new RuntimeException("т.к. строка не является математической операцией или данная операция не обслуживается");
                number1 = romanToNumber(numbers[0]);
                number2 = romanToNumber(numbers[1]);
                //Проверяем значения
                //Арабские цифры
                if (number1 < 0 && number2 < 0) {
                    number1 = stringToInt(numbers[0]);
                    number2 = stringToInt(numbers[1]);
                    if (number1 < 1 || number2 < 1 || number1 > 10 || number2 > 10) throw new RuntimeException("т.к. используются одновременно разные системы счисления или выход из диопозона чисел от 1(I) до 10(X)");
                    result = calculate(number1, number2, operation);
                    System.out.println(numbers[0] + " " + operation + " " + numbers[1] + " = " + result);
                    System.out.println();
                //Римские цифры
                } else if (number1 > 0 && number2 > 0) {
                    result = calculate(number1, number2, operation);
                    if (result < 1) throw new RuntimeException("т.к. в римской системе нет отрицательных чисел");
                    String resultRoman = NumToRoman(result);
                    System.out.println(numbers[0] + " " + operation + " " + numbers[1] + " = " + resultRoman);
                    System.out.println();
                //Исключения
                } else if (number1 < 0 || number2 < 0)
                    throw new RuntimeException("т.к. используются одновременно разные системы счисления или выход из диопозона чисел от 1(I) до 10(X)");
                else {
                    throw new RuntimeException("Error");
                }
            }
        }
    }
    //Определение знака операции
    private static char operation(String op) {
        char[] example = new char[10];
        for (int i = 0; i < op.length(); i++) {
            if (i >= example.length) break;
            example[i] = op.charAt(i);
            if (example[i] == '+') {
                operation = '+';
            }
            if (example[i] == '-') {
                operation = '-';
            }
            if (example[i] == '*') {
                operation = '*';
            }
            if (example[i] == '/') {
                operation = '/';
            }
        }
        return operation;
    }
    //Перевод римских в арабские
    private static int romanToNumber(String roman) {
        int num = 0;
        switch (roman) {
            case "I" -> num = 1;
            case "II" -> num = 2;
            case "III" -> num = 3;
            case "IV" -> num = 4;
            case "V" -> num = 5;
            case "VI" -> num = 6;
            case "VII" -> num = 7;
            case "VIII" -> num = 8;
            case "IX" -> num = 9;
            case "X" -> num = 10;
            default -> num = - 1;
        }
        return num;
    }
    //Выполнение операций
    private static int calculate(int num1, int num2, char op) {
        switch (op) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> result = num1 / num2;
        }
        return result;
    }
    //Перевод результата из арабского в римские
    private static String NumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        String rom = roman[numArabian];
        return rom;
    }
    //Перевод из строки в число и отработка исключений
    private static int stringToInt (String num) {
        int number;
        try {
            number = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new RuntimeException("т.к. используются одновременно разные системы счисления или выход из диопозона чисел от 1(I) до 10(X)");
        }
        return number;
    }
}