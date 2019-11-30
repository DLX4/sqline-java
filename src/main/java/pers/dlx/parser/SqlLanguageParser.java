package pers.dlx.parser;

import static pers.dlx.parser.Token.EOF;

// SQLParser for language elements
public class SqlLanguageParser {

    public static boolean parseStandaloneColumnConstraints(SqlParser parser, Lexer.TokenNode alter, Lexer.TokenNode tableName) {
        boolean exists = false;

        // In Oracle constraint can be enclosed with () i.e. ALTER TABLE ADD (PRIMARY KEY (c1,c2))
        Lexer.TokenNode open = parser.lexer.nextTokenLParen().tokenNode;
        if (open.token == Token.LPAREN) {
            if (parser.target != DbType.ORACLE) {
                parser.lexer.remove(open);
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
                parser.lexer.nextTokenIdent();

                // Now get constraint type keyword

                cns = parser.lexer.nextToken().tokenNode;
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

        if (open.token != EOF) {

            Lexer.TokenNode close = parser.lexer.nextTokenRParen().tokenNode;
            if (close.token != EOF) {
                if (parser.target != DbType.ORACLE) {
                    parser.lexer.remove(close);
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
            parser.lexer.nextToken(Token.KEY);
            hasPrimary = true;
        }
        // UNIQUE
        else if (cns.token == Token.UNIQUE) {
            hasUnique = true;

            // Check for MySQL UNIQUE KEY or UNIQUE INDEX
            boolean isIndex = parser.lexer.nextToken(Token.INDEX);

            if (!isIndex) {
                isIndex = parser.lexer.nextToken(Token.KEY);
            }

            if (isIndex) {
                hasUniqueIndex = true;
                indexName = parser.lexer.nextTokenIdent().tokenNode;

                if (parser.target == DbType.MYSQL) {
                    // if index/key name is specified use it as CONSTRAINT name
                    if (indexName.token != EOF) {

                    }
                }
            }
        }
        // MySQL INDEX or KEY for inline non-unique index
        else if () {

        }

    }

    public static boolean parseForeignKey(SqlParser parser, Lexer.TokenNode cns) {

    }

    public static boolean parseCheckConstraint(SqlParser parser, Lexer.TokenNode cns) {

    }

    public static boolean parseConstraintOption(SqlParser parser) {

    }
}
