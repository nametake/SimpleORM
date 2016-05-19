package info.nametake.dao;

import java.sql.Connection;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by shogo on 2016/05/19.
 */
public class DaoImpl<T> implements Dao<T> {

    private final Connection connection;
    private final Class<T> clazz;

    public DaoImpl(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.clazz = clazz;
    }

    public List<T> select(T data) {
        return null;
    }

    public T selectById(int id) {
        return null;
    }

    public List<T> selectAll() {
        return null;
    }

    public int update(T data) {
        return 0;
    }

    public int insert(T data) {
        return 0;
    }

    public int delete(T data) {
        return 0;
    }
}
