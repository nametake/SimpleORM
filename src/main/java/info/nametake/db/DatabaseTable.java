package info.nametake.db;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface DatabaseTable {
    String tableName();
    Class<?> daoClass() default Void.class;
}
