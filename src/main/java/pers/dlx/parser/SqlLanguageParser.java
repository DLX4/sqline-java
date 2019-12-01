package pers.dlx.parser;

import static pers.dlx.parser.Token.*;

// SQLParser for language elements
public class SqlLanguageParser {

    private final SqlParser parser;

    public SqlLanguageParser(SqlParser parser) {
        this.parser = parser;
    }

    public static boolean parseStandaloneColumnConstraints(SqlParser parser, Lexer.TokenNode alter, Lexer.TokenNode tableName) {
        boolean exists = false;

        // In Oracle constraint can be enclosed with () i.e. ALTER TABLE ADD (PRIMARY KEY (c1,c2))
        boolean open = parser.getNextSpecifiedToken(LPAREN);
        if (open) {
            if (parser.target != DbType.ORACLE) {
                parser.remove(parser.getToken());
            }
        }

        while (true) {
            boolean existsNow = false;

            Lexer.TokenNode cns = parser.lexer.nextToken().tokenNode;
            if (cns.token == EOF) {
                break;
            }

            // Check for constraint name
            if (parser.lexer.token == Token.CONSTRAINT) {
                parser.getNextToken();

                // Now get constraint type keyword
                cns = parser.getNextToken();
            }

            // Parse PRIMARY KEY, UNIQUE in database and INDEX in MySQL
            if (parseKeyConstraint(parser, alter, tableName, parser.lexer.tokenNode)) {
                existsNow = true;
            }
            // Check for FOREIGN KEY
            else if (parseForeignKey(parser, cns)) {
                existsNow = true;
            }
            // CHECK constraint
            else if (parseCheckConstraint(parser, cns)) {
                existsNow = true;
            }

            // Check for constraint option
            if (existsNow) {
                parseConstraintOption(parser);
                exists = true;

                // Hive does not support constraints. TODO

                continue;
            }

            // Not a constraint
            parser.lexer.reset(cns.mark);
            break;
        }

        if (open) {
            boolean close = parser.getNextSpecifiedToken(RPAREN);
            if (close) {
                if (parser.target != DbType.ORACLE) {
                    parser.remove(parser.getToken());
                }
            }
        }

        return exists;
    }

    // Parse PRIMARY KEY, UNIQUE constraint clause, and INDEX/KEY in MySQL
    public static boolean parseKeyConstraint(SqlParser parser, Lexer.TokenNode alter, Lexer.TokenNode tableName, Lexer.TokenNode cns) {
        if (cns.token == EOF) {
            return false;
        }

        boolean hasPrimary = false;
        boolean hasUnique = false;

        // MySQL UNIQUE INDEX clause
        boolean hasUniqueIndex = false;
        boolean hasIndex = false;

        Lexer.TokenNode indexName = null;

        // PRIMARY KEY
        if (cns.token == Token.PRIMARY) {
            // token key
            parser.getNextSpecifiedToken(KEY);
            hasPrimary = true;
        }
        // UNIQUE
        else if (cns.token == Token.UNIQUE) {
            hasUnique = true;

            // Check for MySQL UNIQUE KEY or UNIQUE INDEX
            boolean isIndex = parser.getNextSpecifiedToken(Token.INDEX);
            Lexer.TokenNode index = parser.getToken();
            if (!isIndex) {
                isIndex = parser.getNextSpecifiedToken(Token.KEY);
            }

            if (isIndex) {
                hasUniqueIndex = true;
                indexName = parser.getNextToken();

                if (parser.target != DbType.MYSQL) {
                    // if index/key name is specified use it as CONSTRAINT name
                    parser.prepend(cns, CONSTRAINT);
                    parser.remove(indexName);
                    parser.prepend(cns, indexName);
                }
                // Remove INDEX or KEY keyword for other databases (UNIQUE constraint will be used)
                parser.remove(index);
            }
        }
        // MySQL INDEX or KEY for inline non-unique index
        else if (parser.getNextSpecifiedToken(INDEX) || parser.getNextSpecifiedToken(KEY)) {
            hasIndex = true;
            indexName = parser.getNextToken();

            if (parser.target != DbType.MYSQL) {
                parser.prepend(cns, CREATE);
                parser.prepend(cns, INDEX);
                parser.prepend(cns, ON);
                parser.prepend(cns, tableName);
                parser.remove(cns);
                parser.remove(indexName);
            }
        } else {
            return false;
        }

        if(!parser.getNextSpecifiedToken(LPAREN)) {
            return false;
        }

        Lexer.TokenNode open = parser.getToken();

        while (true) {
            // Lexer.TokenNode col = parser.getNextToken(IDENTITY);

        }

    }

    public static boolean parseForeignKey(SqlParser parser, Lexer.TokenNode cns) {
        return true;
    }

    public static boolean parseCheckConstraint(SqlParser parser, Lexer.TokenNode cns) {
        return true;
    }

    public static boolean parseConstraintOption(SqlParser parser) {
        return true;
    }
}
