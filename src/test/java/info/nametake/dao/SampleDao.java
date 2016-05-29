package info.nametake.dao;

import info.nametake.exception.AnnotationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nameki-shogo on 2016/05/26.
 */
public class SampleDao<T> extends BaseDao<T>{

    public SampleDao(Connection connection, Class<T> clazz) throws AnnotationException {
        super(connection, clazz);
    }

    @Override
    public List<T> select(T data) {
        System.out.println("Call SampleDao select.");
        return null;
    }

    @Override
    public T selectById(int id) throws SQLException {
        return null;
    }

    public T selectByField(String fieldName, Object value) throws SQLException {
        return null;
    }

    @Override
    public List<T> selectAll() throws SQLException {
        return null;
    }

    @Override
    public int update(T data) throws SQLException {
        return 123;
    }

    @Override
    public int insert(T data) throws SQLException {
        return 456;
    }

    @Override
    public int delete(T data) throws SQLException {
        return 789;
    }

    @Override
    public int deleteById(int id) throws SQLException {
        return 0;
    }
}
