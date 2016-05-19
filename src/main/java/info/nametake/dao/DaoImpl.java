package info.nametake.dao;

import info.nametake.stmt.StatementExecutor;

import java.sql.Connection;
import java.util.List;

/**
 * Created by shogo on 2016/05/19.
 */
public class DaoImpl<T> implements Dao<T> {

    private final Connection connection;
    private final Class<T> clazz;
    private final StatementExecutor statementExecutor;

    public DaoImpl(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.clazz = clazz;
        this.statementExecutor = null;
    }

    public List<T> select(T data) {
        return null;
    }

    public T selectById(int id) {
        return null;
    }

    public List<T> selectAll() {
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
}
