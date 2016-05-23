package info.nametake.sqlbuilder;

import info.nametake.dao.TableInfo;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class SQLBuilder {
    private static final String SELECT = "SELECT ";
    private static final String FROM = "FROM ";
    private static final String WHERE = "WHERE ";
    private static final String END = ";";

    private TableInfo tableInfo;


    public SQLBuilder(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }


    /**
     * テーブル情報を元に全てを取得するSELECT分を生成
     * @return
     */
    public String selectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append(SELECT);
        sb.append(getCommaSeparatedField());
        sb.append(getFromTable());
        sb.append(END);
        return new String(sb);
    }

    public String selectById(int id) {
        StringBuilder sb = new StringBuilder();
        return null;
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
     * FROM 分を生成
     * @return "FROM テーブル名" の文字列
     */
    private String getFromTable() {
        StringBuffer sb = new StringBuffer();
        sb.append(FROM);
        sb.append(tableInfo.getTableName());
        return new String(sb);
    }


}
