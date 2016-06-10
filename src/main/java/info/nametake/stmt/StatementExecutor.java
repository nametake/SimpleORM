package info.nametake.stmt;

import info.nametake.dao.TableInfo;
import info.nametake.db.DatabaseField;
import info.nametake.exception.AnnotationException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
        TableInfo<T> tableInfo = new TableInfo<T>(clazz);
        return new StatementExecutor<T>(connection, tableInfo);
    }

    /**
     * StatementExecutorを生成する static ファクトリー
     * @param connection
     * @param tableInfo
     * @param <T>
     * @return
     * @throws AnnotationException
     */
    public static <T> StatementExecutor<T> createStatementExecutor(Connection connection, TableInfo<T> tableInfo)
            throws AnnotationException {
        return new StatementExecutor<T>(connection, tableInfo);
    }

    // 渡されたコネクション
    private Connection connection = null;
    // 渡されたテーブル情報
    private TableInfo<T> tableInfo = null;

    private StatementExecutor(Connection connection, TableInfo<T> tableInfo) throws AnnotationException {
        this.connection = connection;
        this.tableInfo = tableInfo;
    }

    /**
     * 渡されたPreparedStatementを実行する。
     * @param ps
     * @return モデルのリスト
     */
    public List<T> execute(PreparedStatement ps) throws SQLException{
        List<T> list = new ArrayList<T>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            T row = convertResultSetToModel(tableInfo.getClazz(), rs);
            list.add(row);
        }
        return list;
    }

    /**
     * 渡されたSQLを実行する。
     * @param sql
     * @return モデルのリスト
     */
    public List<T> execute(String sql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        return execute(ps);
    }

    public int update(PreparedStatement ps) throws SQLException {
        return ps.executeUpdate();
    }

    public int insertAutoIncrementedId(PreparedStatement ps) throws SQLException {
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        throw new SQLException();
    }

    /**
     * 渡されたResultSetの1行をモデルにして返す。
     * @param clazz
     * @param resultSet
     * @return
     */
    private T convertResultSetToModel(Class<T> clazz, ResultSet resultSet) throws SQLException {
        T instance = null;
        try {
            instance = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                DatabaseField dbf = field.getAnnotation(DatabaseField.class);
                if (dbf == null) {
                    continue;
                }
                String fieldName = dbf.columnName();
                field.set(instance, resultSet.getObject(fieldName));
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
