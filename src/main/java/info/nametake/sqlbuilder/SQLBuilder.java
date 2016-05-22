package info.nametake.sqlbuilder;

import info.nametake.dao.TableInfo;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class SQLBuilder {
    private TableInfo tableInfo;


    public SQLBuilder(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }

    public String selectAll() {
        return null;
    }
}
