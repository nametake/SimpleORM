package info.nametake.dao;

import info.nametake.exception.AnnotationException;

import java.sql.Connection;

/**
 * Created by nameki-shogo on 2016/05/25.
 */
public abstract class BaseDao<T> implements Dao<T> {
    protected final Connection connection;
    protected final Class<T> clazz;
    protected TableInfo<T> tableInfo;

    public BaseDao(Connection connection, Class<T> clazz) throws AnnotationException {
        this.connection = connection;
        this.clazz = clazz;
        this.tableInfo = new TableInfo<T>(clazz);
    }

}
