// Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернёет «перевёрнутый» список.

// Реализуйте очередь с помощью LinkedList со следующими методами:
// • enqueue() — помещает элемент в конец очереди,
// • dequeue() — возвращает первый элемент из очереди и удаляет его,
// • first() — возвращает первый элемент из очереди, не удаляя.

// В калькулятор добавьте возможность отменить последнюю операцию.


import java.util.Stack;

public class Calculator {
    private Stack<Double> operandStack;
    private Stack<Character> operatorStack;
    private Stack<Double> prevOperandStack;
    private Stack<Character> prevOperatorStack;

    public Calculator() {
        operandStack = new Stack<Double>();
        operatorStack = new Stack<Character>();
        prevOperandStack = new Stack<Double>();
        prevOperatorStack = new Stack<Character>();
    }

    public void enter(double operand) {
        operandStack.push(operand);
    }

    public void apply(char operator) {
        operatorStack.push(operator);
        if (operator == '=') {
            prevOperandStack.clear();
            prevOperatorStack.clear();
            double result = evaluate();
            operandStack.clear();
            operatorStack.clear();
            operandStack.push(result);
            return;
        }
        if (operandStack.size() >= 2 && operatorStack.size() >= 1) {
            double operand2 = operandStack.pop();
            double operand1 = operandStack.pop();
            char op = operatorStack.pop();
            double result = evaluate(op, operand1, operand2);
            prevOperandStack.clear();
            prevOperatorStack.clear();
            prevOperandStack.addAll(operandStack);
            prevOperatorStack.addAll(operatorStack);
            operandStack.clear();
            operatorStack.clear();
            operandStack.push(result);
        }
    }

    public void undo() {
        operandStack.clear();
        operatorStack.clear();
        operandStack.addAll(prevOperandStack);
        operatorStack.addAll(prevOperatorStack);
    }

    public double getResult() {
        if (!operandStack.isEmpty()) {
            return operandStack.peek();
        } else {
            return 0.0;
        }
    }

    private double evaluate() {
        double result = 0.0;
        while (!operatorStack.isEmpty()) {
            char op = operatorStack.pop();
            double operand2 = operandStack.pop();
            double operand1 = operandStack.pop();
            result = evaluate(op, operand1, operand2);
            operandStack.push(result);
        }
        return operandStack.pop();
    }

    private double evaluate(char op, double operand1, double operand2) {
        double result = 0.0;
        switch (op) {
            case '+': result = operand1 + operand2; break;
            case '-': result = operand1 - operand2; break;
            case '*': result = operand1 * operand2; break;
            case '/': result = operand1 / operand2; break;
        }
        return result;
    }
}