package info.nametake.dao;

import info.nametake.db.DatabaseField;
import info.nametake.db.DatabaseTable;
import info.nametake.exception.AnnotationException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shogo on 2016/05/22.
 */
public class TableInfo<T> {
    private final Class<T> clazz;

    // Table info
    private final DatabaseTable table;
    private List<DatabaseField> fields;
    private DatabaseField primaryField;

    public TableInfo(Class<T> clazz) throws AnnotationException {
        this.clazz = clazz;

        // Set table name
        table = clazz.getAnnotation(DatabaseTable.class);
        if (table == null) {
            throw new AnnotationException();
        }
        // Set field
        primaryField = null;
        fields = new ArrayList<DatabaseField>();
        for (Field field : clazz.getDeclaredFields()) {
            DatabaseField dbf = field.getAnnotation(DatabaseField.class);
            fields.add(dbf);
            if (dbf.primaryKey()) {
                primaryField = dbf;
            }
        }
    }

    public String getTableName() {
        return table.tableName();
    }

    public List<String> getFieldNames() {
        List<String> list = new ArrayList<String>();
        for (DatabaseField field: fields) {
            list.add(field.columnName());
        }
        return list;
    }


}
