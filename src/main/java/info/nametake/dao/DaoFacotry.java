package info.nametake.dao;

import info.nametake.db.DatabaseTable;
import info.nametake.exception.AnnotationException;

import java.sql.Connection;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class DaoFacotry {
    public static <D extends Dao<?>, T> D createDao(Connection connection, Class<T> clazz) throws AnnotationException {
        DatabaseTable databaseTable = clazz.getAnnotation(DatabaseTable.class);
        if (databaseTable == null) {
            throw new AnnotationException();
        }
        return null;
    }
}
