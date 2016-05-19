package info.nametake.db;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface DatabaseTable {
    String columnName();
    boolean primaryKey() default false;
}
