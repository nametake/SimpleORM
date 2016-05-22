package info.nametake.dao;

import info.nametake.exception.AnnotationException;
import info.nametake.stmt.StatementBuilder;
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
    private StatementBuilder<T> stmtBuilder;
    private StatementExecutor<T> stmtExecutor;

    public DaoImpl(Connection connection, Class<T> clazz) throws AnnotationException {
        this.connection = connection;
        this.clazz = clazz;
        this.tableInfo = new TableInfo<T>(clazz);
        this.stmtBuilder = new StatementBuilder<T>(connection, tableInfo);
        this.stmtExecutor = StatementExecutor.createStatementExecutor(connection, tableInfo);
    }

    public List<T> select(T data) {
        return null;
    }

    public T selectById(int id) {
        return null;
    }

    public List<T> selectAll() throws SQLException {
        PreparedStatement ps = stmtBuilder.getSelectAllStatement();
        return stmtExecutor.execute(ps);
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
