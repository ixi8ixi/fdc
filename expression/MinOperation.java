package expression;

import java.math.BigInteger;

public class MinOperation extends AbstractBinaryOperation {
    public MinOperation(UnitedExpression leftOperand, UnitedExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperationString() {
        return " min ";
    }

    @Override
    int makeOperation(int a, int b) {
        return Math.min(a, b);
    }

    @Override
    BigInteger makeBigOperation(BigInteger a, BigInteger b) {
        return (a.compareTo(b) <= 0) ? a : b;
    }

    @Override
    public int getPriority() {
        return 3000;
    }

    @Override
    public int getRightInOperationPriority() {
        return 0;
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
