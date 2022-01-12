package expression;

public interface UnitedExpression extends Expression, TripleExpression, BigIntegerExpression {
    int getPriority();
    int getRightInOperationPriority();
    int getLeftInOperationPriority();
    String getOperationString();
    void makeString(StringBuilder in);
    void makeMiniString(StringBuilder in);
}
