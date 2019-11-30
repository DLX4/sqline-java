package pers.dlx;

import pers.dlx.parser.DbType;
import pers.dlx.parser.SQLParserUtils;
import pers.dlx.parser.SqlParser;

public class Sqlines {

    public static void main(String[] args) {
        StringBuffer output = new StringBuffer("1");
        String sql = "ALTER TABLE \"ZJIPST110\".\"BB_ZD\" ADD CHECK (\"GXSJ\" IS NOT NULL);\n" +
                "ALTER TABLE \"ZJIPST110\".\"BB_ZD\" ADD CHECK (\"ZDMC\" IS NOT NULL);\n" +
                "ALTER TABLE \"ZJIPST110\".\"BB_ZD\" ADD CHECK (\"JGDM\" IS NOT NULL);";
        DbType source = DbType.ORACLE;
        DbType target = DbType.MYSQL;
        new Sqlines().convertSql(
                new SqlParser(SQLParserUtils.createLexer(sql, source), source, target),
                sql, output);
        System.out.println("Hello World!" + output.toString());
    }

    public void convertSql(SqlParser parser, String sql, StringBuffer output) {
        parser.convert(sql, output);
    }

}
