package pers.dlx;

import pers.dlx.parser.DbType;
import pers.dlx.parser.Lexer;
import pers.dlx.parser.SQLParserUtils;
import pers.dlx.parser.SqlParser;

public class Sqlines {

    public static void main(String[] args) {
        String sql = "CREATE INDEX \"ZJIPST110\".\"IDX_CJD_CJD2\"\n" +
                "ON \"ZJIPST110\".\"CJD_CJD2\" (CJDBH2 ASC, \"CJDBH\" ASC, \"XZQH\" ASC);" +
                "ALTER TABLE \"ZJIPST110\".\"BB_ZD\" ADD CHECK (\"ZDMC\" IS NOT NULL);\n" +
                "ALTER TABLE \"ZJIPST110\".\"BB_ZD\" ADD CHECK (\"JGDM\" IS NOT NULL);";
        DbType source = DbType.ORACLE;
        DbType target = DbType.MYSQL;

        Lexer lexer = SQLParserUtils.createLexer(sql, source);

        SqlParser parser = new SqlParser(lexer, source, target);

        parser.convert(sql);

        System.out.println("Hello World!" + parser.target);
    }

}
