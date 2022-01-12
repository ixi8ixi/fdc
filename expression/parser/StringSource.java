package expression.parser;

public class StringSource implements CharSource {
    private final String DATA;
    private int position;

    public StringSource(String string) {
        this.DATA = string;
    }

    @Override
    public boolean hasNext() {
        return position < DATA.length();
    }

    @Override
    public char next() {
        return DATA.charAt(position++);
    }

    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException(String.format("%s : %d : %s", DATA.substring(0, position), position, message));
    }
}
