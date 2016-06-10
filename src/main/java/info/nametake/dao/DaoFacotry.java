package info.nametake.dao;

import info.nametake.db.DatabaseTable;
import info.nametake.exception.AnnotationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


public class DaoFacotry {

    private static HashMap daoCache = new HashMap();

    public static <D extends Dao<?>, T> D createDao(Connection connection, Class<T> clazz) throws AnnotationException, SQLException {

        // Check decorated DatabaseTalbe annotation
        DatabaseTable databaseTable = clazz.getAnnotation(DatabaseTable.class);
        if (databaseTable == null) {
            throw new AnnotationException();
        }

        // Cache check
        if (daoCache.containsKey(clazz)) {
            return (D) daoCache.get(clazz);
        }

        Dao<?> dao = null;
        if (databaseTable.daoClass() == Void.class) {
            // Create Dao
            dao = new DaoImpl<T>(connection, clazz);
        } else {
            Class<?> daoClass = databaseTable.daoClass();
            Object[] argments = new Object[] {connection, clazz};

            Constructor<?> daoConstructor = findConstructor(daoClass, argments);
            if (daoConstructor == null) {
                throw new SQLException();
            }
            try {
                dao = (Dao<?>) daoConstructor.newInstance(argments);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        daoCache.put(clazz, dao);
        return (D) dao;
    }

    private static Constructor<?> findConstructor(Class<?> daoClass, Object[] params) {
        for (Constructor<?> constructor : daoClass.getConstructors()) {
            Class<?>[] paramsTypes = constructor.getParameterTypes();
            if (paramsTypes.length == params.length) {
                boolean match = true;
                for (int i = 0; i < paramsTypes.length; i++) {
                    if (!paramsTypes[i].isAssignableFrom(params[i].getClass())) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return constructor;
                }
            }
        }
        return null;
    }
}
