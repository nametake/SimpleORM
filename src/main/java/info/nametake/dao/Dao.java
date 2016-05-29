package info.nametake.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by shogo on 2016/05/19.
 */
public interface Dao<T> {
    /**
     * 意味ないので削除予定
     * @param data
     * @return
     */
    public List<T> select(T data);

    /**
     * 主キーを指定してSelect
     * @param id
     * @return
     * @throws SQLException
     */
    public T selectById(int id) throws SQLException;
    public T selectByField(String fieldName, Object value) throws SQLException;
    public List<T> selectAll() throws SQLException;
    public int update(T data) throws SQLException;
    public int insert(T data) throws SQLException;
    public int delete(T data) throws SQLException;
    public int deleteById(int id) throws SQLException;
}
