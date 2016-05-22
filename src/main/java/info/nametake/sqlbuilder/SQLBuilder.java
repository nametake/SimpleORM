package info.nametake.sqlbuilder;

import info.nametake.dao.TableInfo;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class SQLBuilder {
    private static final String SELECT = "SELECT ";
    private static final String FROM = "FROM ";

    private TableInfo tableInfo;


    public SQLBuilder(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String selectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append(SELECT);
        sb.append(getCommaSeparatedField());
        sb.append(getFromTable());
        return new String(sb);
    }

    private String getCommaSeparatedField() {
        StringBuffer sb = new StringBuffer(String.join(", ", tableInfo.getFieldNames()));
        sb.append(" ");
        return new String(sb);
    }

    private String getFromTable() {
        StringBuffer sb = new StringBuffer();
        sb.append(FROM);
        sb.append(tableInfo.getTableName());
        return new String(sb);
    }


}
