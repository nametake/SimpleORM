package info.nametake.stmt;

import info.nametake.dao.DataType;
import info.nametake.dao.TableInfo;
import info.nametake.db.DatabaseField;
import info.nametake.sqlbuilder.SQLBuilder;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * Created by shogo on 2016/05/22.
 */
public class StatementBuilder<T> {
    private Connection connection;
    private TableInfo<T> tableInfo;
    private SQLBuilder sqlBuilder;

    public StatementBuilder(Connection connection, TableInfo<T> tableInfo) {
        this.connection = connection;
        this.tableInfo = tableInfo;
        this.sqlBuilder = new SQLBuilder(tableInfo);
    }

    public PreparedStatement getSelectAllStatement() throws SQLException {
        String sql = sqlBuilder.selectAll();
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }

    public PreparedStatement getSelectByIdStatement(int id) throws SQLException {
        String sql = sqlBuilder.selectById(id);
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }

    public PreparedStatement getSelectByFieldStatement(String fieldName) throws SQLException {
        String sql = sqlBuilder.selectByField(fieldName);
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }

    public PreparedStatement getInsertStatement(T data) throws SQLException {
        String sql = sqlBuilder.insert();
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        return setValues(ps, data);
    }

    public PreparedStatement getUpdateStatement(T data) throws SQLException {
        String sql = sqlBuilder.update();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps = setValues(ps, data);
        ps = setPrimaryKeyValue(ps, data, tableInfo.getNotAutoUpdateFieldNames().size() + 1);
        return ps;
    }

    public PreparedStatement getDeleteStatement(T data) throws SQLException {
        String sql = sqlBuilder.delete();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps = setPrimaryKeyValue(ps, data, 1);
        return ps;

    }

    private PreparedStatement setValues(PreparedStatement ps, T data) throws SQLException {
        // loop
        for (Field field : tableInfo.getClazz().getDeclaredFields()) {
            field.setAccessible(true);
            DatabaseField df = field.getAnnotation(DatabaseField.class);

            // 何番目のデータかを取得
            int order = tableInfo.getNotAutoUpdateFieldNames().indexOf(df.columnName());
            if (order == -1) {
                continue;
            }
            order += 1;

            // データを取得
            Object value = null;
            try {
                value = field.get(data);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            // データをセット
            if (value == null) {
                // TODO:キモいのでリファクタリング
                // NULL データを挿入
                if (df.dataType().equals(DataType.INT)) {
                    ps.setNull(order, Types.INTEGER);
                } else if (df.dataType().equals(DataType.STRING)) {
                    ps.setNull(order, Types.VARCHAR);
                } else if (df.dataType().equals(DataType.DATETIME)) {
                    ps.setNull(order, Types.DATE);
                } else {
                    ps.setNull(order, Types.VARCHAR);
                }
            } else if (df.dataType().equals(DataType.INT)) {
                ps.setInt(order, (Integer) value);
            } else if (df.dataType().equals(DataType.STRING)) {
                ps.setString(order, (String) value);
            } else if (df.dataType().equals(DataType.DATETIME)) {
                ps.setDate(order, new Date( ((Timestamp) value).getTime()));
            } else {
                ps.setObject(order, value);
            }
        }
        return ps;
    }

    private PreparedStatement setPrimaryKeyValue(PreparedStatement ps, T data, int parametarIndex) throws SQLException {
        Field primaryKeyField =tableInfo.getPrimaryField();
        primaryKeyField.setAccessible(true);
        Object keyData = null;
        try {
            keyData = primaryKeyField.get(data);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ps.setObject(parametarIndex, keyData);
        return ps;
    }
}
