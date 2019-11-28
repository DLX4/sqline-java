package pers.dlx.parser;


import static pers.dlx.parser.LayoutCharacters.EOI;

public class CharTypes {

    private final static boolean[] hexFlags = new boolean[256];
    private final static boolean[] firstIdentifierFlags = new boolean[256];
    private final static String[] stringCache = new String[256];
    private final static boolean[] identifierFlags = new boolean[256];
    private final static boolean[] whitespaceFlags = new boolean[256];

    static {
        for (char c = 0; c < hexFlags.length; ++c) {
            if (c >= 'A' && c <= 'F') {
                hexFlags[c] = true;
            } else if (c >= 'a' && c <= 'f') {
                hexFlags[c] = true;
            } else if (c >= '0' && c <= '9') {
                hexFlags[c] = true;
            }
        }
    }

    static {
        for (char c = 0; c < firstIdentifierFlags.length; ++c) {
            if (c >= 'A' && c <= 'Z') {
                firstIdentifierFlags[c] = true;
            } else if (c >= 'a' && c <= 'z') {
                firstIdentifierFlags[c] = true;
            }
        }
        firstIdentifierFlags['`'] = true;
        firstIdentifierFlags['_'] = true;
        firstIdentifierFlags['$'] = true;
    }

    static {
        for (char c = 0; c < identifierFlags.length; ++c) {
            if (c >= 'A' && c <= 'Z') {
                identifierFlags[c] = true;
            } else if (c >= 'a' && c <= 'z') {
                identifierFlags[c] = true;
            } else if (c >= '0' && c <= '9') {
                identifierFlags[c] = true;
            }
        }
        // identifierFlags['`'] = true;
        identifierFlags['_'] = true;
        identifierFlags['$'] = true;
        identifierFlags['#'] = true;

        for (int i = 0; i < identifierFlags.length; i++) {
            if (identifierFlags[i]) {
                char ch = (char) i;
                stringCache[i] = Character.toString(ch);
            }
        }
    }

    static {
        for (int i = 0; i <= 32; ++i) {
            whitespaceFlags[i] = true;
        }

        whitespaceFlags[EOI] = false;
        for (int i = 0x7F; i <= 0xA0; ++i) {
            whitespaceFlags[i] = true;
        }

        whitespaceFlags[160] = true; // 特别处理
    }

    public static boolean isHex(char c) {
        return c < 256 && hexFlags[c];
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean isFirstIdentifierChar(char c) {
        if (c <= firstIdentifierFlags.length) {
            return firstIdentifierFlags[c];
        }
        return c != '　' && c != '，';
    }

    public static boolean isIdentifierChar(char c) {
        if (c <= identifierFlags.length) {
            return identifierFlags[c];
        }
        return c != '　' && c != '，' && c != '）';
    }

    public static String valueOf(char ch) {
        if (ch < stringCache.length) {
            return stringCache[ch];
        }
        return null;
    }

    /**
     * @return false if {@link LayoutCharacters#EOI}
     */
    public static boolean isWhitespace(char c) {
        return (c <= whitespaceFlags.length && whitespaceFlags[c]) //
                || c == '　'; // Chinese space
    }

}