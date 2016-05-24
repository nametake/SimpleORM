package info.nametake.stmt;

import info.nametake.dao.TableInfo;
import info.nametake.sqlbuilder.SQLBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by shogo on 2016/05/22.
 */
public class StatementBuilder<T> {
    private Connection connection;
    private TableInfo<T> tableInfo;
    private SQLBuilder sqlBuilder;

    public StatementBuilder(Connection connection, TableInfo<T> tableInfo) {
        this.connection = connection;
        this.tableInfo = tableInfo;
        this.sqlBuilder = new SQLBuilder(tableInfo);
    }

    public PreparedStatement getSelectAllStatement() throws SQLException {
        String sql = sqlBuilder.selectAll();
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }

    public PreparedStatement getSelectByIdStatement(int id) throws SQLException {
        String sql = sqlBuilder.selectById(id);
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }
}
