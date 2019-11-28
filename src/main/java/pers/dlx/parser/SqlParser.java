package pers.dlx.parser;

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
        StringBuilder buf = new StringBuilder(sql.length());

        // Process tokens until the end of input
        for (; ; ) {
            lexer.nextToken();

            Token token = lexer.token();
            if (token == Token.EOF) {
                break;
            }

            System.out.println(lexer.info());
        }

    }
}
