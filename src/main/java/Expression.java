import java.util.*;

public class Expression {

    public static double evaluate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder currentNumber = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) {
                continue;
            }

            if (Character.isDigit(ch) || ch == '.') {
                currentNumber.append(ch);
            } else {
                if (currentNumber.length() > 0) {
                    numbers.push(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                }

                if (ch == '(') {
                    operators.push(ch);
                } else if (ch == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        processTopOperator(numbers, operators);
                    }
                    if (!operators.isEmpty()) {
                        operators.pop(); // Удаляем '('
                    }
                } else if (isOperator(ch)) {
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                        processTopOperator(numbers, operators);
                    }
                    operators.push(ch);
                }
            }
        }

        if (currentNumber.length() > 0) {
            numbers.push(Double.parseDouble(currentNumber.toString()));
        }

        while (!operators.isEmpty()) {
            processTopOperator(numbers, operators);
        }

        return numbers.isEmpty() ? 0 : numbers.pop();
    }

    private static void processTopOperator(Stack<Double> numbers, Stack<Character> operators) {
        if (numbers.size() < 2) {
            return; // Защита от некорректного выражения
        }
        double b = numbers.pop();
        double a = numbers.pop();
        char operator = operators.pop();

        switch (operator) {
            case '+': numbers.push(a + b); break;
            case '-': numbers.push(a - b); break;
            case '*': numbers.push(a * b); break;
            case '/': numbers.push(b != 0 ? a / b : 0); break; // Деление на ноль возвращает 0
        }
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int precedence(char operator) {
        return (operator == '+' || operator == '-') ? 1 : 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();

        double result = evaluate(expression);
        System.out.println("Результат: " + result);
    }
}
