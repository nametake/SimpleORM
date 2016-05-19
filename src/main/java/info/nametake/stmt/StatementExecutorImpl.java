package info.nametake.stmt;

import info.nametake.stmt.StatementExecutor;

import java.sql.Connection;

/**
 * Created by shogo on 2016/05/20.
 */
public class StatementExecutorImpl<T> implements StatementExecutor<T>{
    protected Connection connection;
    protected Class<T> clazz;

    public StatementExecutorImpl(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.clazz = clazz;
    }
}
