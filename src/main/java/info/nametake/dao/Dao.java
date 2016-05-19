package info.nametake.dao;

import java.util.List;

/**
 * Created by shogo on 2016/05/19.
 */
public interface Dao<T> {
    public List<T> select(T data);
    public T selectById(int id);
    public List<T> selectAll();
    public int update(T data);
    public int insert(T data);
    public int delete(T data);
}
