package info.nametake.models;

import info.nametake.dao.DataType;
import info.nametake.db.DatabaseField;
import info.nametake.db.DatabaseTable;
import org.relaxng.datatype.Datatype;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
@DatabaseTable(tableName = "user")
public class User {
    @DatabaseField(columnName = "id",
            dataType = DataType.INT,
            primaryKey = true)
    private int id;

    @DatabaseField(columnName = "name", dataType = DataType.STRING)
    private String name;

    @DatabaseField(columnName = "password", dataType = DataType.STRING)
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

