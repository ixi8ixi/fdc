package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Const implements UnitedExpression {
    private final BigInteger VALUE;

    public Const(int value) {
        this.VALUE = BigInteger.valueOf(value);
    }

    public Const(BigInteger value) {
        VALUE = value;
    }

    public Const negate() {
        return new Const(VALUE.negate());
    }

    @Override
    public int evaluate(int x) {
        return VALUE.intValue();
    }


    @Override
    public int evaluate(int x, int y, int z) {
        return VALUE.intValue();
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return VALUE;
    }

    @Override
    public String toMiniString() {
        return VALUE.toString();
    }

    @Override
    public String toString() {
        return VALUE.toString();
    }

    @Override
    public int getPriority() {
        return 0;
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
    public String getOperationString() {
        throw new AssertionError();
    }

    @Override
    public void makeString(StringBuilder in) {
        in.append(VALUE);
    }

    @Override
    public void makeMiniString(StringBuilder in) {
        in.append(VALUE);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Const)) {
            return false;
        }
        Const check = (Const) object;
        return Objects.equals(VALUE, check.VALUE);
    }

    @Override
    public int hashCode() {
        return VALUE.intValue();
    }
}
