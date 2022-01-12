package expression.parser;

import expression.*;

public class ExpressionParser {
    public static UnitedExpression parse(CharSource in) {
        return new ExpressionParserFilling(in).parse();
    }

    public TripleExpression parse(String expression) {
        return new ExpressionParserFilling(new StringSource(expression)).parse();
    }

    public static class ExpressionParserFilling extends BaseParser {
        protected ExpressionParserFilling(CharSource source) {
            super(source);
        }

        public UnitedExpression parse() {
            skipWhitespace();
            if (eof()) {
                throw error("You have to enter expression");
            }
            UnitedExpression result = parseSum();
            while (!eof()) {
                result = parseMinMax(result);
            }
            return result;
        }

        public UnitedExpression parseElement() {
            skipWhitespace();
            UnitedExpression result = parseValue();
            skipWhitespace();
            return result;
        }

        public UnitedExpression parseValue() {
            if (take('-')) {
                if (between('0', '9')) {
                    return parseConst(true);
                } else {
                    return new UnaryMinus(parseElement());
                }
            } else if (between('0', '9')) {
                return parseConst(false);
            } else if (test('l') || test('t')) {
                return parseUnary();
            } else if (between('x', 'z')) {
                return parseVar();
            } else if (take('(')) {
                return parseBracket();
            } else {
                throw error("Strange symbol: " + take());
            }
        }

        public Const parseConst(boolean minus) {
            StringBuilder number = new StringBuilder();
            if (minus) {
                number.append('-');
            }
            if (take('0')) {
                number.append('0');
                if (take('0')) {
                    throw error("Double zero");
                }
                return new Const(0);
            } else if (between('1', '9')) {
                takeDigits(number);
                return new Const(Integer.parseInt(number.toString()));
            }
            throw error("Invalid number format: " + number);
        }

        private void takeDigits(StringBuilder in) {
            while (between('0', '9')) {
                in.append(take());
            }
        }

        public Variable parseVar() {
            char name = take();
            return switch (name) {
                case 'x' -> new Variable("x");
                case 'y' -> new Variable("y");
                case 'z' -> new Variable("z");
                default -> throw error("Illegal variable name: " + name);
            };
        }

        public UnitedExpression parseUnary() {
            if (take('l')) {
                expect('0');
                return new LeadingZerosOperation(parseElement());
            } else if (take('t')) {
                expect('0');
                return new TrailingZerosOperation(parseElement());
            } else {
                throw error("Expected: '-' or 'l' or 't', actual: '" + take() + "'");
            }
        }

        public UnitedExpression parseMultiplyDivide(UnitedExpression previous) {
            if (take('*')) {
                return new Multiply(previous, parseElement());
            } else if(take('/')) {
                return new Divide(previous, parseElement());
            } else {
                throw error("Expected: '*' or '/', actual: '" + take() + "'");
            }
        }

        public UnitedExpression parseTerm() {
            UnitedExpression result = parseElement();
            while (!test('+') && !test('-') && !test('m') && !test(')') && !eof()) {
                result = parseMultiplyDivide(result);
            }
            return result;
        }

        public UnitedExpression parseAddSubtract(UnitedExpression previous) {
            if (take('+')) {
                return new Add(previous, parseTerm());
            } else if (take('-')) {
                return new Subtract(previous, parseTerm());
            } else {
                throw error("Expected '+' or '-', actual: '" + take() + "'");
            }
        }

        public UnitedExpression parseSum() {
            UnitedExpression result = parseTerm();
            while (!test('m') && !test(')') && !eof()) {
                result = parseAddSubtract(result);
            }
            return result;
        }

        public UnitedExpression parseMinMax(UnitedExpression previous) {
            expect('m');
            if (take('i')) {
                expect('n');
                return new MinOperation(previous, parseSum());
            } else if (take('a')) {
                expect('x');
                return new MaxOperation(previous, parseSum());
            } else {
                throw error("Expected 'i' or 'a', actual: '" + take() + "'");
            }
        }

        public UnitedExpression parseBracket() {
            UnitedExpression result = parseSum();
            while (!take(')')) {
                if (eof()) {
                    throw error("End of file reached, expected ')'");
                }
                result = parseMinMax(result);
            }
            return result;
        }

        private boolean takeWhitespace() {
            return Character.isWhitespace(ch);
        }

        private void skipWhitespace() {
            while (takeWhitespace()) {
                take();
            }
        }
    }
}