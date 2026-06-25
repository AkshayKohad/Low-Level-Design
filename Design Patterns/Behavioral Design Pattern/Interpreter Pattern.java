// Expression interface
interface Expression {
    int interpret();
}

// Terminal expression
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

// Non-terminal expression for addition
class AddExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AddExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() + rightExpression.interpret();
    }
}

// Non-terminal expression for subtraction
class SubtractExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public SubtractExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() - rightExpression.interpret();
    }
}

// Client class
public class InterpreterPatternDemo {
    public static void main(String[] args) {

        // Expression: 10 + 5
        Expression addition = new AddExpression(
                new NumberExpression(10),
                new NumberExpression(5)
        );

        System.out.println("10 + 5 = " + addition.interpret());

        // Expression: 20 - 8
        Expression subtraction = new SubtractExpression(
                new NumberExpression(20),
                new NumberExpression(8)
        );

        System.out.println("20 - 8 = " + subtraction.interpret());

        // Expression: (10 + 5) - 3
        Expression complexExpression = new SubtractExpression(
                new AddExpression(
                        new NumberExpression(10),
                        new NumberExpression(5)
                ),
                new NumberExpression(3)
        );

        System.out.println("(10 + 5) - 3 = " + complexExpression.interpret());
    }
}
Output
10 + 5 = 15
20 - 8 = 12
(10 + 5) - 3 = 12
How it works

Expression is the common interface for all grammar rules.

NumberExpression represents numbers.

AddExpression represents addition.

SubtractExpression represents subtraction.
