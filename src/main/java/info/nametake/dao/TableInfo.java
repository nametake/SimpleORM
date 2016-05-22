package info.nametake.dao;

import info.nametake.db.DatabaseColumn;
import info.nametake.db.DatabaseTable;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by shogo on 2016/05/22.
 */
public class TableInfo<T> {
    private final Class<T> clazz;

    // Table info
    private final String tableName;
    private List<String> columnNames;
    private String primaryKey;

    public TableInfo(Class<T> clazz) {
        this.clazz = clazz;

        // Set table name
        tableName = clazz.getAnnotation(DatabaseTable.class).tableName();
        // Set column name
        primaryKey = null;
        for (Field field : clazz.getDeclaredFields()) {
            DatabaseColumn dc = field.getAnnotation(DatabaseColumn.class);
            columnNames.add(dc.columnName());
        }

    }
}
