package expression;

import java.math.BigInteger;
import java.util.Objects;

public abstract class AbstractBinaryOperation implements UnitedExpression {
    private final UnitedExpression leftOperand;
    private final UnitedExpression rightOperand;

    public AbstractBinaryOperation(final UnitedExpression leftOperand,
                                   final UnitedExpression rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public abstract String getOperationString();
    abstract int makeOperation(int a, int b);
    abstract BigInteger makeBigOperation(BigInteger a, BigInteger b);
    public abstract int getPriority();
    public abstract int getRightInOperationPriority();
    public abstract int getLeftInOperationPriority();
    public abstract boolean isLeftAssociative();
    public abstract boolean isRightAssociative();

    @Override
    public int evaluate(final int x) {
        return makeOperation(leftOperand.evaluate(x), rightOperand.evaluate(x));
    }

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return makeOperation(leftOperand.evaluate(x, y, z), rightOperand.evaluate(x, y, z));
    }

    @Override
    public BigInteger evaluate(final BigInteger x) {
        return makeBigOperation(leftOperand.evaluate(x), rightOperand.evaluate(x));
    }

    private void bracketWrapper(final StringBuilder in, final boolean left) {
        final UnitedExpression operand = left ?  leftOperand : rightOperand;

        if (getPriority() < operand.getPriority() ||
                (getPriority() == operand.getPriority() &&
                        (left && (!isLeftAssociative() ||
                                operand.getLeftInOperationPriority() < getLeftInOperationPriority()) ||
                                !left && (!isRightAssociative() ||
                                        operand.getRightInOperationPriority() < getRightInOperationPriority()))
                )
        ) {
            in.append("(");
            operand.makeMiniString(in);
            in.append(")");
        } else {
            operand.makeMiniString(in);
        }
    }

    @Override
    public void makeMiniString(final StringBuilder in) {
        bracketWrapper(in, true);
        in.append(getOperationString());
        bracketWrapper(in, false);
    }

    @Override
    public String toMiniString() {
        final StringBuilder in = new StringBuilder();
        makeMiniString(in);
        return in.toString();
    }

    @Override
    public void makeString(final StringBuilder in) {
        in.append("(");
        leftOperand.makeString(in);
        in.append(getOperationString());
        rightOperand.makeString(in);
        in.append(")");
    }

    @Override
    public String toString() {
        final StringBuilder in = new StringBuilder();
        makeString(in);
        return in.toString();
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AbstractBinaryOperation)) {
            return false;
        }
        final AbstractBinaryOperation expr = (AbstractBinaryOperation) object;
        return getOperationString().equals(expr.getOperationString()) &&
                leftOperand.equals(expr.leftOperand) &&
                Objects.equals(rightOperand, expr.rightOperand);
    }

    @Override
    public int hashCode() {
        return (leftOperand.hashCode() * 17 + rightOperand.hashCode()) * 17 + getOperationString().hashCode();
    }
}
