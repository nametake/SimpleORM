package info.nametake.models;

import info.nametake.db.DatabaseField;
import info.nametake.db.DatabaseTable;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
@DatabaseTable(tableName = "user")
public class User {
    @DatabaseField(columnName = "id", primaryKey = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "password")
    private String password;


}

