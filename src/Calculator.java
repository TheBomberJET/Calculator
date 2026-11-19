import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        double result = 0;
        boolean hasFirstNumber = false;
        String operation = "";

        System.out.println("Команды: C — сброс, S — выход. Дробные числа вводите через точку.");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                if (!hasFirstNumber) {
                    System.out.print("Введите первое число: ");
                } else if (operation.isEmpty()) {
                    System.out.print("Введите операцию (+, -, *, /): ");
                } else {
                    System.out.print("Введите второе число: ");
                }

                if (!scanner.hasNext()) {
                    break;
                }
                String input = scanner.next();

                if (input.equalsIgnoreCase("s")) {
                    break;
                }
                if (input.equalsIgnoreCase("c")) {
                    result = 0;
                    hasFirstNumber = false;
                    operation = "";
                    System.out.println("Сброс. Результат: " + result);
                    continue;
                }

                if (hasFirstNumber && operation.isEmpty()) {
                    switch (input) {
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                            operation = input;
                            break;
                        default:
                            System.out.println("Ошибка: неизвестная операция.");
                    }
                    continue;
                }

                double number;
                try {
                    number = Double.parseDouble(input);
                } catch (NumberFormatException exception) {
                    System.out.println("Ошибка: введите число.");
                    continue;
                }
                if (!Double.isFinite(number)) {
                    System.out.println("Ошибка: введите конечное число.");
                    continue;
                }

                if (!hasFirstNumber) {
                    result = number;
                    hasFirstNumber = true;
                } else {
                    if (operation.equals("/") && number == 0) {
                        System.out.println("Ошибка: делить на ноль нельзя.");
                    } else {
                        result = calculate(result, operation, number);
                        System.out.println("Результат: " + result);
                    }
                    operation = "";
                }
            }
        }

        System.out.println("Калькулятор выключен.");
    }

    private static double calculate(double firstOperand, String operation, double secondOperand) {
        switch (operation) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                return firstOperand / secondOperand;
            default:
                throw new IllegalArgumentException("Неизвестная операция: " + operation);
        }
    }
}