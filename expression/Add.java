package expression;

import java.math.BigInteger;

public class Add extends AbstractBinaryOperation {

    public Add(UnitedExpression leftOperand, UnitedExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String getOperationString() {
        return " + ";
    }

    @Override
    int makeOperation(int a, int b) {
        return a + b;
    }

    @Override
    BigInteger makeBigOperation(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public int getPriority() {
        return 2000;
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
