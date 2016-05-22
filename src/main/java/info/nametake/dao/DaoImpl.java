package info.nametake.dao;

import info.nametake.stmt.StatementExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shogo on 2016/05/19.
 */
class DaoImpl<T> implements Dao<T> {

    private final Connection connection;
    private final Class<T> clazz;
    private final TableInfo<T> tableInfo;

    public DaoImpl(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.clazz = clazz;
        this.tableInfo = new TableInfo<T>(clazz);
    }

    public List<T> select(T data) {
        return null;
    }

    public T selectById(int id) {
        return null;
    }

    public List<T> selectAll() throws SQLException {
        // TODO:
        // SQLの作成
        String sql = "";
        PreparedStatement ps = connection.prepareStatement(sql);
        return null;
    }

    public int update(T data) {
        return 0;
    }

    public int insert(T data) {
        return 0;
    }

    public int delete(T data) {
        return 0;
    }

    public PreparedStatement buildStatement() {
        return null;
    }
}
