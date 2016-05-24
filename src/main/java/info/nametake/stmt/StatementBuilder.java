package info.nametake.stmt;

import info.nametake.dao.DataType;
import info.nametake.dao.TableInfo;
import info.nametake.db.DatabaseField;
import info.nametake.sqlbuilder.SQLBuilder;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public PreparedStatement getInsertStatement(T data) throws SQLException {
        String sql = sqlBuilder.insert();
        PreparedStatement ps = connection.prepareStatement(sql);

        return setValues(ps, data);
    }

    private PreparedStatement setValues(PreparedStatement ps, T data) throws SQLException {
        // loop
        for (Field field : tableInfo.getClazz().getDeclaredFields()) {
            field.setAccessible(true);
            DatabaseField df = field.getAnnotation(DatabaseField.class);
            // データを取得
            Object value = null;
            try {
                value = field.get(data);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            // 何番目のデータ化を取得
            int order = tableInfo.getNotAutoUpdateFieldNames().indexOf(df.columnName());
            if (order == -1) {
                continue;
            }
            // タイプによってセットメソッドを切り替え
            order += 1;
            if (df.dataType().equals(DataType.INT)) {
                ps.setInt(order, (Integer) value);
            } else if (df.dataType().equals(DataType.STRING)) {
                ps.setString(order, (String) value);
            } else if (df.dataType().equals(DataType.DATETIME)) {
                ps.setDate(order, new Date((Long) value));
            }
        }
        return ps;
    }
}
