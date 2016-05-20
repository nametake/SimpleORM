package info.nametake.stmt;

import info.nametake.db.DatabaseTable;
import info.nametake.exception.AnnotationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class StatementExecutor<T> {

    /**
     * StatementExecutorを生成する static ファクトリー
     * @param connection
     * @param clazz
     * @param <T>
     * @return 指定したクラスの StatementExecutor のインスタンス
     * @throws AnnotationException
     */
    public static <T> StatementExecutor<T> createStatementExecutor(Connection connection, Class<T> clazz)
            throws AnnotationException {
        return new StatementExecutor<T>(connection, clazz);
    }

    // 渡されたコネクション
    private Connection connection = null;
    // 格納するモデルのクラス
    private Class<T> clazz = null;

    private StatementExecutor(Connection connection, Class<T> clazz) throws AnnotationException {
        this.connection = connection;
        this.clazz = clazz;

        if (this.clazz.getAnnotation(DatabaseTable.class) == null) {
            throw new AnnotationException();
        }
    }

    /**
     * 渡されたSQLを実行する。
     * @param sql
     * @return モデルのリスト
     */
    public List<T> executeQuery(String sql) throws SQLException{
        return null;
    }

    /**
     * 渡されたSQLを実行する。
     * @param sql
     * @return (1) SQL データ操作言語(DML) 文の場合は行数、(2) 何も返さない SQL 文の場合は 0

     */
    public int executeUpdate(String sql, T data) throws SQLException {
        return 0;
    }

    /**
     * 渡されたResultSetの1行をモデルにして返す。
     * @param clazz
     * @param resultSet
     * @return
     */
    private T convertResultSetToModel(Class<T> clazz, ResultSet resultSet) {
        return null;
    }

}
