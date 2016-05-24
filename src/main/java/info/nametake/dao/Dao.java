package info.nametake.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by shogo on 2016/05/19.
 */
public interface Dao<T> {
    public List<T> select(T data);
    public T selectById(int id) throws SQLException;
    public List<T> selectAll() throws SQLException;
    public int update(T data) throws SQLException;
    public int insert(T data) throws SQLException;
    public int delete(T data) throws SQLException;
    public int deleteById(int id) throws SQLException;
}
