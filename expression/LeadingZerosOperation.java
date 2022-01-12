package expression;

import java.math.BigInteger;

public class LeadingZerosOperation extends AbstractUnaryOperation {
    public LeadingZerosOperation(UnitedExpression operand) {
        super(operand);
    }

    @Override
    public String getOperationString() {
        return "l0";
    }

    @Override
    public String getMiniOperationString() {
        return "l0 ";
    }

    @Override
    int makeOperation(int a) {
        return Integer.numberOfLeadingZeros(a);
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
