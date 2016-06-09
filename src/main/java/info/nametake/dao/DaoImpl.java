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
class DaoImpl<T> extends BaseDao<T> {

    private StatementBuilder<T> stmtBuilder;
    private StatementExecutor<T> stmtExecutor;

    public DaoImpl(Connection connection, Class<T> clazz) throws AnnotationException {
        super(connection, clazz);
        this.tableInfo = new TableInfo<T>(clazz);
        this.stmtBuilder = new StatementBuilder<T>(connection, tableInfo);
        this.stmtExecutor = StatementExecutor.createStatementExecutor(connection, tableInfo);
    }

    public List<T> select(T data) {
        return null;
    }

    public T selectById(int id) throws SQLException {
        PreparedStatement ps = stmtBuilder.getSelectByIdStatement(id);
        List<T> list = stmtExecutor.execute(ps);
        if (list.size() != 1) {
            throw new  SQLException();
        }

        return list.get(0);
    }

    public List<T> selectByField(String fieldName, Object value) throws SQLException {
        PreparedStatement ps = stmtBuilder.getSelectByFieldStatement(fieldName, value);
        List<T> list = stmtExecutor.execute(ps);
        if (list.size() < 1) {
            throw new  SQLException();
        }

        return list;
    }

    public List<T> selectAll() throws SQLException {
        PreparedStatement ps = stmtBuilder.getSelectAllStatement();
        return stmtExecutor.execute(ps);
    }

    public int update(T data) throws SQLException {
        PreparedStatement ps = stmtBuilder.getUpdateStatement(data);
        return stmtExecutor.update(ps);
    }

    public int insert(T data) throws SQLException {
        PreparedStatement ps = stmtBuilder.getInsertStatement(data);
        return stmtExecutor.insertAutoIncrementedId(ps);
    }

    public int delete(T data) throws SQLException {
        PreparedStatement ps = stmtBuilder.getDeleteStatement(data);
        return stmtExecutor.update(ps);
    }

    public int deleteById(int id) throws SQLException {
        PreparedStatement ps = stmtBuilder.getDeleteByIdStatement(id);
        return stmtExecutor.update(ps);
    }

    public int deleteByField(String fieldName, Object value) throws SQLException {
        PreparedStatement ps = stmtBuilder.getDeleteByFieldStatement(fieldName, value);
        return stmtExecutor.update(ps);
    }

}
