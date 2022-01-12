package expression;

import java.math.BigInteger;

public class TrailingZerosOperation extends AbstractUnaryOperation {
    public TrailingZerosOperation(UnitedExpression operand) {
        super(operand);
    }

    @Override
    public String getOperationString() {
        return "t0";
    }

    @Override
    public String getMiniOperationString() {
        return "t0 ";
    }

    @Override
    int makeOperation(int a) {
        return Integer.numberOfTrailingZeros(a);
    }

    @Override
    BigInteger makeBigOperation(BigInteger a) {
        return null;
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
