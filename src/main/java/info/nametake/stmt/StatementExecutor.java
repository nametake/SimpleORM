package info.nametake.stmt;

import java.sql.Connection;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class StatementExecutor<T> {
    private Connection connection = null;
    private Class<T> clazz = null;

    public StatementExecutor(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.clazz = clazz;
    }
}
