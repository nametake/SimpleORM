package info.nametake.db;

import info.nametake.dao.DataType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(FIELD)
@Retention(RUNTIME)
public @interface DatabaseField {
    String columnName();
    DataType dataType() default DataType.NONE;
    boolean primaryKey() default false;
    boolean autoIncrement() default false;
    boolean timeStamp() default false;
}
