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

    // token list
    private LinkedList<Lexer.TokenNode> targetTokens = new LinkedList<>();

    // get next token
    public Lexer.TokenNode getNextToken() {
        Lexer.TokenNode next = lexer.nextToken().tokenNode;
        accept(next);
        return next;
    }

    // get next specifiedToken
    public boolean getNextSpecifiedToken(Token token) {
        boolean exist = lexer.nextToken(token);
        if (exist) {
            accept(this.getToken());
        }
        return exist;
    }

    // get current token
    public Lexer.TokenNode getToken() {
        return lexer.tokenNode;
    }

    // remove a token from token list
    public final void remove(Lexer.TokenNode tokenNode) {
        Lexer.TokenNode toRemove = null;
        for (Lexer.TokenNode node : targetTokens) {
            if (node == tokenNode) {
                toRemove = node;
                break;
            }
        }

        if (toRemove != null) {
            targetTokens.remove(toRemove);
        }
    }

    // Prepend the token with the specified token
    public final void prepend(Lexer.TokenNode existNode, Token token) {
        Lexer.TokenNode prepend = lexer.createTokenNode(token);

        int index = 0;
        for (Lexer.TokenNode node : targetTokens) {
            if (node == existNode) {
                break;
            }
            index++;
        }
        targetTokens.add(index, prepend);
    }

    // Prepend the token with the specified token node
    public final void prepend(Lexer.TokenNode existNode, Lexer.TokenNode prepend) {
        int index = 0;
        for (Lexer.TokenNode node : targetTokens) {
            if (node == existNode) {
                break;
            }
            index++;
        }
        targetTokens.add(index, prepend);
    }

    // accept a token
    public final void accept(Lexer.TokenNode tokenNode) {
        targetTokens.add(tokenNode);
    }

    public SqlParser(Lexer lexer, DbType source, DbType target) {
        this.lexer = lexer;
        this.source = source;
        this.target = target;
    }

    // Parser high-level token
    public void parse(Scope scope) {
        new StatementParser(this).parseStatement( scope);
    }

    public void convert(String sql) {
        // Process tokens until the end of input
        for (; ; ) {
            lexer.nextToken();

            Token token = lexer.token();
            if (token == Token.EOF) {
                break;
            }

            System.out.println(lexer.info());
            //parse(SQL_SCOPE_FREE);
        }
    }
}
