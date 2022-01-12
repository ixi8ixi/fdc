package expression;

import java.math.BigInteger;

public class UnaryMinus extends AbstractUnaryOperation {
    public UnaryMinus(UnitedExpression operand) {
        super(operand);
    }

    @Override
    public String getOperationString() {
        return "-";
    }

    @Override
    public String getMiniOperationString() {
        return  "- ";
    }

    @Override
    int makeOperation(int a) {
        return -a;
    }

    @Override
    BigInteger makeBigOperation(BigInteger a) {
        return a.negate();
    }

    @Override
    public int getPriority() {
        return 500;
    }

    @Override
    public int getRightInOperationPriority() {
        return 0;
    }

    @Override
    public int getLeftInOperationPriority() {
        return 0;
    }
}
