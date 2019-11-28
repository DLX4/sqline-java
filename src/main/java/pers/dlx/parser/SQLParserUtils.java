package pers.dlx.parser;

import pers.dlx.dialect.mysql.MySqlLexer;
import pers.dlx.dialect.mysql.OracleLexer;
import pers.dlx.dialect.mysql.PGLexer;

public class SQLParserUtils {

    public static Lexer createLexer(String sql, DbType dbType) {
        if (DbType.ORACLE.equals(dbType)) {
            return new OracleLexer(sql);
        }

        if (DbType.MYSQL.equals(dbType)) {
            return new MySqlLexer(sql);
        }

        if (DbType.POSTGRESQL.equals(dbType)) {
            return new PGLexer(sql);
        }

        return new Lexer(sql);
    }
}
