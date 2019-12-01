package pers.dlx.parser;

import java.util.Objects;

import static pers.dlx.parser.Scope.SQL_STMT_ALTER_TABLE;
import static pers.dlx.parser.Token.ADD;

// alter statement parser
public class AlterStatementParser {

    private final SqlParser parser;

    public AlterStatementParser(SqlParser parser) {
        this.parser = parser;
    }

    public boolean parseAlterStatement() {
        boolean exists = false;

        Lexer.TokenNode next = parser.getNextToken();

        switch (next.token) {
            case TABLE:
                parseAlterTableStatement();
                break;
            case INDEX:
                break;
            case FUNCTION:
                break;
            case PROCEDURE:
                break;
            case SEQUENCE:
                break;
        }
        return exists;
    }

    public boolean parseAlterTableStatement() {
        boolean exists = false;
        Lexer.TokenNode tableName = parser.getNextToken();

        Lexer.TokenNode next = parser.getNextToken();

        Scope prevStmtScope = parser.stmtScope;
        parser.stmtScope = SQL_STMT_ALTER_TABLE;

        // ADD constraint
        if (Objects.equals(next, ADD)) {

        }

        return exists;
    }
}
