package info.nametake.models;

import info.nametake.db.DatabaseColumn;
import info.nametake.db.DatabaseTable;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
@DatabaseTable(tableName = "user")
public class User {
    @DatabaseColumn(columnName = "id", primaryKey = true)
    private int id;

    @DatabaseColumn(columnName = "name")
    private String name;

    @DatabaseColumn(columnName = "password")
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

