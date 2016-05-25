package info.nametake.dao;

import info.nametake.exception.AnnotationException;

import java.sql.Connection;

/**
 * Created by nameki-shogo on 2016/05/25.
 */
public abstract class BaseDao<T> implements Dao<T> {
    private Connection connection;
    private Class<T> clazz;
    private TableInfo<T> talbeInfo;

    public BaseDao(Connection connection, Class<T> clazz) throws AnnotationException {
        this.connection = connection;
        this.clazz = clazz;
        this.talbeInfo = new TableInfo<T>(clazz);
    }

}
