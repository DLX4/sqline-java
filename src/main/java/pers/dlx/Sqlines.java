package pers.dlx;

import pers.dlx.parser.SQLParserUtils;
import pers.dlx.parser.SqlParser;

public class Sqlines {

    public static void main(String[] args) {
        StringBuffer output = new StringBuffer("1");
        String sql = "select * from dlx;";
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
