package pers.dlx;

import pers.dlx.parser.DbType;
import pers.dlx.parser.SQLParserUtils;
import pers.dlx.parser.SqlParser;

public class Sqlines {

    public static void main(String[] args) {
        StringBuffer output = new StringBuffer("1");
        String sql = "CREATE TABLE \"ZJIPST110\".\"BJQK_INFO_DAY\" (\n" +
                "\"TJRQ\" DATE NULL ,\n" +
                "\"XZQH\" VARCHAR2(6 BYTE) NULL ,\n" +
                "\"BJFSDM\" NUMBER(4) NULL ,\n" +
                "\"BJFSMC\" VARCHAR2(64 BYTE) NULL ,\n" +
                "\"HJ\" NUMBER(8) NULL ,\n" +
                "\"WFFZAN\" NUMBER(8) NULL ,\n" +
                "\"ZAAJ\" NUMBER(8) NULL ,\n" +
                "\"HZSG\" NUMBER(8) NULL ,\n" +
                "\"JTSG\" NUMBER(8) NULL ,\n" +
                "\"ZAZHSG\" NUMBER(8) NULL ,\n" +
                "\"ZHSG\" NUMBER(8) NULL ,\n" +
                "\"ZS\" NUMBER(8) NULL ,\n" +
                "\"JF\" NUMBER(8) NULL ,\n" +
                "\"JTBL\" NUMBER(8) NULL ,\n" +
                "\"GMQZ\" NUMBER(8) NULL ,\n" +
                "\"ZSXR\" NUMBER(8) NULL ,\n" +
                "\"JWJD\" NUMBER(8) NULL ,\n" +
                "\"QT\" NUMBER(8) NULL \n" +
                ");";
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
