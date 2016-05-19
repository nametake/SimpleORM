package info.nametake.dao;

import info.nametake.db.DatabaseTable;

import java.sql.Connection;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class DaoFacotry {
    public static <D extends Dao<?>, T> D createDao(Connection connection, Class<T> clazz) {
        DatabaseTable databaseTable = clazz.getAnnotation(DatabaseTable.class);
        return null;
    }
}
