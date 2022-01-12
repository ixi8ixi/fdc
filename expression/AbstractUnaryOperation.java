package expression;

import java.math.BigInteger;

public abstract class AbstractUnaryOperation implements UnitedExpression {
    private final UnitedExpression operand;

    public AbstractUnaryOperation(UnitedExpression operand) {
        this.operand = operand;
    }

    public abstract String getOperationString();
    public abstract String getMiniOperationString();
    abstract int makeOperation(int a);
    abstract BigInteger makeBigOperation(BigInteger a);
    public abstract int getPriority();
    public abstract int getRightInOperationPriority();

    @Override
    public int evaluate(int x) {
        return makeOperation(operand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return makeOperation(operand.evaluate(x, y, z));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return makeBigOperation(evaluate(x));
    }

    @Override
    public void makeString(StringBuilder in) {
        in.append(getOperationString());
        in.append('(');
        operand.makeString(in);
        in.append(')');
    }

    @Override
    public String toString() {
        StringBuilder in = new StringBuilder();
        makeString(in);
        return in.toString();
    }

    @Override
    public void makeMiniString(StringBuilder in) {
        if (operand.getPriority() > 500) {
            in.append(getOperationString()).append('(');
            operand.makeMiniString(in);
            in.append(')');
        } else {
            in.append(getMiniOperationString());
            operand.makeMiniString(in);
        }
    }

    @Override
    public String toMiniString() {
        StringBuilder in = new StringBuilder();
        makeMiniString(in);
        return in.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AbstractUnaryOperation)) {
            return false;
        }
        AbstractUnaryOperation expr = (AbstractUnaryOperation) object;
        return getOperationString().equals(expr.getOperationString()) &&
                operand.equals(expr.operand);
    }

    @Override
    public int hashCode() {
        return operand.hashCode() * 17 + getOperationString().hashCode();
    }
}
