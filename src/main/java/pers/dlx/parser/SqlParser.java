package pers.dlx.parser;

import java.util.LinkedList;

import static pers.dlx.parser.Scope.SQL_SCOPE_FREE;

public class SqlParser {

    public final Lexer lexer;
    // Source and target SQL dialects
    public final DbType source;
    public final DbType target;

    // Current conversion level (application, SQL code, dynamic string i.e.)
    Scope level;
    // Current object scope (table, view, procedure etc.)
    Scope objScope;
    // Current statement scope (CREATE TABLE, CREATE VIEW, ALTER TABLE etc.)
    Scope stmtScope;

    public SqlParser(Lexer lexer, DbType source, DbType target) {
        this.lexer = lexer;
        this.source = source;
        this.target = target;
    }

    // Parser high-level token
    public void parse(Scope scope, StringBuffer output) {
        StatementParser.parseStatement(this, scope, output);
    }

    public void convert(String sql, StringBuffer output) {
        StringBuilder buf = new StringBuilder(sql.length());

        // Process tokens until the end of input
        for (; ; ) {
            lexer.nextToken();

            Token token = lexer.token();
            if (token == Token.EOF) {
                break;
            }

            System.out.println(lexer.info());
            parse(SQL_SCOPE_FREE, output);
        }

    }
}
