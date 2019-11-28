package pers.dlx.dialect.mysql;

import pers.dlx.parser.Keywords;
import pers.dlx.parser.Lexer;
import pers.dlx.parser.ParserException;
import pers.dlx.parser.Token;

import java.util.HashMap;
import java.util.Map;

import static pers.dlx.parser.CharTypes.isIdentifierChar;
import static pers.dlx.parser.Keywords.DEFAULT_KEYWORDS;
import static pers.dlx.parser.Token.LITERAL_CHARS;

public class PGLexer extends Lexer {

    public final static Keywords DEFAULT_PG_KEYWORDS;

    static {
        Map<String, Token> map = new HashMap<String, Token>();

        map.putAll(DEFAULT_KEYWORDS.getKeywords());

        map.put("BEGIN", Token.BEGIN);
        map.put("CASCADE", Token.CASCADE);
        map.put("CONTINUE", Token.CONTINUE);
        map.put("CURRENT", Token.CURRENT);
        map.put("FETCH", Token.FETCH);
        map.put("FIRST", Token.FIRST);

        map.put("IDENTITY", Token.IDENTITY);
        map.put("LIMIT", Token.LIMIT);
        map.put("NEXT", Token.NEXT);
        map.put("NOWAIT", Token.NOWAIT);
        map.put("OF", Token.OF);

        map.put("OFFSET", Token.OFFSET);
        map.put("ONLY", Token.ONLY);
        map.put("RECURSIVE", Token.RECURSIVE);
        map.put("RESTART", Token.RESTART);

        map.put("RESTRICT", Token.RESTRICT);
        map.put("RETURNING", Token.RETURNING);
        map.put("ROW", Token.ROW);
        map.put("ROWS", Token.ROWS);
        map.put("SHARE", Token.SHARE);
        map.put("SHOW", Token.SHOW);
        map.put("START", Token.START);

        map.put("USING", Token.USING);
        map.put("WINDOW", Token.WINDOW);

        map.put("TRUE", Token.TRUE);
        map.put("FALSE", Token.FALSE);
        map.put("ARRAY", Token.ARRAY);
        map.put("IF", Token.IF);
        map.put("TYPE", Token.TYPE);
        map.put("ILIKE", Token.ILIKE);

        DEFAULT_PG_KEYWORDS = new Keywords(map);
    }

    public PGLexer(String input) {
        super(input);
        this.skipComment = true;
        this.keepComments = true;
        super.keywods = DEFAULT_KEYWORDS;
    }

    protected void scanString() {
        mark = pos;
        boolean hasSpecial = false;

        for (; ; ) {
            if (isEOF()) {
                lexError("unclosed.str.lit");
                return;
            }

            ch = charAt(++pos);

            if (ch == '\\') {
                scanChar();
                if (!hasSpecial) {
                    initBuff(bufPos);
                    arraycopy(mark + 1, buf, 0, bufPos);
                    hasSpecial = true;
                }

                putChar('\\');
                switch (ch) {
                    case '\0':
                        putChar('\0');
                        break;
                    case '\'':
                        putChar('\'');
                        break;
                    case '"':
                        putChar('"');
                        break;
                    case 'b':
                        putChar('\b');
                        break;
                    case 'n':
                        putChar('\n');
                        break;
                    case 'r':
                        putChar('\r');
                        break;
                    case 't':
                        putChar('\t');
                        break;
                    case '\\':
                        putChar('\\');
                        break;
                    case 'Z':
                        putChar((char) 0x1A); // ctrl + Z
                        break;
                    default:
                        putChar(ch);
                        break;
                }
                scanChar();
            }

            if (ch == '\'') {
                scanChar();
                if (ch != '\'') {
                    token = LITERAL_CHARS;
                    break;
                } else {
                    initBuff(bufPos);
                    arraycopy(mark + 1, buf, 0, bufPos);
                    hasSpecial = true;
                    putChar('\'');
                    putChar('\'');
                    continue;
                }
            }

            if (!hasSpecial) {
                bufPos++;
                continue;
            }

            if (bufPos == buf.length) {
                putChar(ch);
            } else {
                buf[bufPos++] = ch;
            }
        }

        if (!hasSpecial) {
            stringVal = subString(mark + 1, bufPos);
        } else {
            stringVal = new String(buf, 0, bufPos);
        }
    }

    public void scanSharp() {
        scanChar();
        if (ch == '>') {
            scanChar();
            if (ch == '>') {
                scanChar();
                token = Token.POUNDGTGT;
            } else {
                token = Token.POUNDGT;
            }
        } else {
            token = Token.POUND;
        }
    }

    protected void scanVariable_at() {
        if (ch != '@') {
            throw new ParserException("illegal variable. " + info());
        }

        mark = pos;
        bufPos = 1;
        char ch;

        final char c1 = charAt(pos + 1);
        if (c1 == '@') {
            pos += 2;
            token = Token.MONKEYS_AT_AT;
            this.ch = charAt(++pos);
            return;
        } else if (c1 == '>') {
            pos += 2;
            token = Token.MONKEYS_AT_GT;
            this.ch = charAt(++pos);
            return;
        }

        for (; ; ) {
            ch = charAt(++pos);

            if (!isIdentifierChar(ch)) {
                break;
            }

            bufPos++;
            continue;
        }

        this.ch = charAt(pos);

        stringVal = addSymbol();
        token = Token.VARIANT;
    }
}
