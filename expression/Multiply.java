package expression;

import java.math.BigInteger;

public class Multiply extends AbstractBinaryOperation {
    public Multiply(UnitedExpression leftOperand, UnitedExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperationString() {
        return " * ";
    }

    @Override
    int makeOperation(int a, int b) {
        return a * b;
    }

    @Override
    BigInteger makeBigOperation(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public int getPriority() {
        return 1000;
    }

    @Override
    public int getRightInOperationPriority() {
        return 1;
    }

    @Override
    public int getLeftInOperationPriority() {
        return 0;
    }

    @Override
    public boolean isLeftAssociative() {
        return true;
    }

    @Override
    public boolean isRightAssociative() {
        return true;
    }
}
