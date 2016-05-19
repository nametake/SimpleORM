package info.nametake.dao;

import java.sql.Connection;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class DaoManager {
    public static <D extends Dao<?>, T> D createDao(Connection connection, Class<T> clazz) {
        return null;
    }
}
