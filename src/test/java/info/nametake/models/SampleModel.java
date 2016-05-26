package info.nametake.models;

import info.nametake.dao.SampleDao;
import info.nametake.db.DatabaseField;
import info.nametake.db.DatabaseTable;

import static info.nametake.dao.DataType.INT;
import static info.nametake.dao.DataType.STRING;

/**
 * Created by nameki-shogo on 2016/05/26.
 */
@DatabaseTable(tableName = "Sample", daoClass = SampleDao.class)
public class SampleModel {
    @DatabaseField(columnName = "ID",
            dataType = INT,
            primaryKey = true,
            autoIncrement = true)
    private int id;

    @DatabaseField(columnName = "NAME", dataType = STRING)
    private String name;

    @DatabaseField(columnName = "PASS", dataType = STRING)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
