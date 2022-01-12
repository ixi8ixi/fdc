package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Variable implements UnitedExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new IllegalStateException("Illegal variable name: " + name);
        }
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return x;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public String toString() {
        return name;
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
        return null;
    }

    @Override
    public void makeString(StringBuilder in) {
        in.append(name);
    }

    @Override
    public void makeMiniString(StringBuilder in) {
        in.append(name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Variable)) {
            return false;
        }
        Variable variable = (Variable) object;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
