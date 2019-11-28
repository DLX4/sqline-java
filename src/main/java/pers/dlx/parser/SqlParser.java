package pers.dlx.parser;

import pers.dlx.DbType;

public class SqlParser {

    private final Lexer lexer;
    private final DbType source;
    private final DbType target;

    public SqlParser(Lexer lexer, DbType source, DbType target) {
        this.lexer = lexer;
        this.source = source;
        this.target = target;
    }

    // Parser high-level token
    public void parse(Token token, int scope) {
    }

    public void convert(String sql, StringBuffer output) {
        // Process tokens until the end of input
        while (true) {

        }
    }
}
