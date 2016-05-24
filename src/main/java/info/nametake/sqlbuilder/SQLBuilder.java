package info.nametake.sqlbuilder;

import info.nametake.dao.TableInfo;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class SQLBuilder {
    private static final String SELECT = "SELECT ";
    private static final String FROM   = "FROM ";
    private static final String WHERE  = "WHERE ";
    private static final String END    = ";";
    private static final String EQ     = " = ";

    private TableInfo tableInfo;


    public SQLBuilder(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }


    /**
     * テーブル情報を元に全てを取得するSELECT分を生成
     * @return
     */
    public String selectAll() {
        StringBuffer sb = new StringBuffer();
        sb.append(SELECT);
        sb.append(getCommaSeparatedField());
        sb.append(getFromTable());
        sb.append(END);
        return new String(sb);
    }

    /**
     * IDを指定したSelect文を生成
     * @param id
     * @return
     */
    public String selectById(int id) {
        StringBuffer sb = new StringBuffer();
        sb.append(SELECT);
        sb.append(getCommaSeparatedField());
        sb.append(getFromTable());
        sb.append(getWhereById(id));
        sb.append(END);
        return new String(sb);
    }

    /**
     * カンマ区切りのフィールド名を取得
     * @return カンマ区切りのフィールドの文字列
     */
    private String getCommaSeparatedField() {
        StringBuffer sb = new StringBuffer(String.join(", ", tableInfo.getFieldNames()));
        sb.append(" ");
        return new String(sb);
    }

    /**
     * FROM 文を取得
     * @return "FROM テーブル名" の文字列
     */
    private String getFromTable() {
        StringBuffer sb = new StringBuffer(FROM);
        sb.append(tableInfo.getTableName());
        sb.append(" ");
        return new String(sb);
    }

    /**
     * 指定された PK を指定するWHERE文をを取得
     * @param id
     * @return "WHERE (PK) = id"の文字列
     */
    private String getWhereById(int id) {
        StringBuffer sb = new StringBuffer(WHERE);
        sb.append(tableInfo.getPrimaryKey());
        sb.append(EQ);
        sb.append(id);
        sb.append(" ");
        return new String(sb);
    }


}
