package pers.dlx.parser;

import java.util.Objects;

import static pers.dlx.parser.Scope.SQL_STMT_ALTER_TABLE;
import static pers.dlx.parser.Token.ADD;

public class StatementParser {

    public boolean parseAlterStatement(SqlParser parser, StringBuffer output) {
        boolean exists = false;

        parser.lexer.nextToken();
        Token next = parser.lexer.token();

        switch (next) {
            case TABLE:
                parseAlterTableStatement(parser, output);
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

    public boolean parseAlterTableStatement(SqlParser parser, StringBuffer output) {
        boolean exists = false;
        Lexer.TokenNode tableName = parser.lexer.nextTokenIdent().tokenNode;

        Lexer.TokenNode next = parser.lexer.nextToken().tokenNode;

        Scope prevStmtScope = parser.stmtScope;
        parser.stmtScope = SQL_STMT_ALTER_TABLE;

        // ADD constraint
        if (Objects.equals(next, ADD)) {

        }

        return exists;
    }

    public static void parseStatement(SqlParser parser, Scope scope, StringBuffer output) {
        // 解析各种sql语句入口
        switch (parser.lexer.token()) {
            case ALTER:
                break;
//            case ALLOCATE:
//                break;
//            case ASSOCIATE:
//                break;
            case BEGIN:
                break;
//            case CALL:
//                break;
            case CASE:
                break;
            case CLOSE:
                break;
//            case COLLECT:
//                break;
            case CONNECT:
                break;
            case COMMENT:
                break;
            case COMMIT:
                break;
            case CREATE:
                break;
            case DECLARE:
                break;
            case DELETE:
                break;
//            case DELIMITER:
//                break;
            case DROP:
                break;
            case EXCEPTION:
                break;
//            case EXEC:
//                break;
//            case EXECUTE:
//                break;
            case EXIT:
                break;
//            case EXPORT:
//                break;
            case FETCH:
                break;
            case FOR:
                break;
//            case FOREACH:
//                break;
//            case FREE:
//                break;
            case FUNCTION:
                break;
//            case HELP:
//                break;
            case IF:
                break;
            case INSERT:
                break;
//            case GET:
//                break;
            case GRANT:
                break;
            case LEAVE:
                break;
//            case LET:
//                break;
            case LOCK:
                break;
            case LOOP:
                break;
            case NULL:
                break;
            case ON:
                break;
            case OPEN:
                break;
//            case PERFORM:
//                break;
//            case PREPARE:
//                break;
//            case PRINT:
//                break;
            case PROCEDURE:
                break;
//            case PROMPT:
//                break;
//            case REM:
//                break;
//            case RAISE:
//                break;
            case REPEAT:
                break;
            case REPLACE:
                break;
//            case RESIGNAL:
//                break;
            case RETURN:
                break;
            case REVOKE:
                break;
//            case ROLLBACK:
//                break;
            case SELECT:
                break;
            case SET:
                break;
            case SHOW:
                break;
//            case SIGNAL:
//                break;
//            case SYSTEM:
//                break;
//            case TERMINATE:
//                break;
            case TRUNCATE:
                break;
            case UPDATE:
                break;
            case USE:
                break;
            case VALUES:
                break;
            case WHILE:
                break;
            default:
                    break;

        }
    }
}
