package VowelCount;

public class Parser {
    private String original;
    private char[] searchLetters;

    public Parser(String original) {
        this.original = original;
        searchLetters = new char[]{'a', 'o', 'u', 'i', 'e', 'y'};
    }

    public int[] Parse() {
        String[] lines = splitOriginal();
        return checkLines( lines );
    }

    private String[] splitOriginal() {
        return original.split( "\n" );
    }

    private int[] checkLines(String[] lines) {
        int[] result = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            result[i] = checkLine( lines[i] );
        }

        return result;
    }

    private int checkLine(String line) {
        int result = 0;

        for (char c : line.toCharArray()) {
            result+= checkChar(c);
        }

        return result;
    }

    private int checkChar(char c){
        for (char s : searchLetters) {
            if (s == c) {
                return 1;
            }
        }

        return 0;
    }
}
