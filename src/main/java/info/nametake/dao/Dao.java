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
     * 主キーを指定したSELECT文を実行
     * @param id: 主キー
     * @return ジェネリクスで指定したモデルのオブジェクト
     * @throws SQLException
     */
    public T selectById(int id) throws SQLException;

    /**
     * フィールド名と値を指定したSELECT文を実行
     * @param fieldName: カラム名
     * @param value: 値
     * @return ジェネリクスで指定したモデルのオブジェクト
     * @throws SQLException
     */
    public T selectByField(String fieldName, Object value) throws SQLException;

    /**
     * 対象とするモデルの全てのデータを取得
     * @return ジェネリクスで指定したモデルのリスト
     * @throws SQLException
     */
    public List<T> selectAll() throws SQLException;

    /**
     * モデルをUPDATE
     * @param data: UPDATEするモデルのインスタンス
     * @return 変更された行数
     * @throws SQLException
     */
    public int update(T data) throws SQLException;

    /**
     * モデルをINSERT
     * @param data: INSERTするモデルのインスタンス
     * @return AUTO_INCREMENTで自動割り振りされた主キー
     * @throws SQLException
     */
    public int insert(T data) throws SQLException;

    /**
     * モデルをDELETE
     * @param data: DELETEするモデルのインスタンス
     * @return 変更された行数
     * @throws SQLException
     */
    public int delete(T data) throws SQLException;

    /**
     * 未実装
     * @param id
     * @return
     * @throws SQLException
     */
    public int deleteById(int id) throws SQLException;
}
