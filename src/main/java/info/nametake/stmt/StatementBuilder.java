package info.nametake.stmt;

import info.nametake.dao.TableInfo;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by shogo on 2016/05/22.
 */
public class StatementBuilder<T> {
    private Connection connection;
    private TableInfo<T> tableInfo;

    public StatementBuilder(Connection connection, TableInfo<T> tableInfo) {
        this.connection = connection;
        this.tableInfo = tableInfo;
    }

    public Statement getSelectAll() {
        return null;
    }
}
