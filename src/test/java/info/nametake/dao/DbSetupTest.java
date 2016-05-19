package info.nametake.dao;

import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.sun.glass.ui.EventLoop;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertThat;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class DbSetupTest {

    private static String url = "jdbc:h2:./testdb";
    private static String user = "sa";
    private static String pass = "";
    private static Destination destination = new DriverManagerDestination(url, user, pass);

    private static Connection con;

    private static String dropTable = "DROP TABLE IF EXISTS USER;";
    private static String createTable = "CREATE TABLE IF NOT EXISTS USER (ID INT PRIMARY KEY, NAME VARCHAR(12), PASSWORD VARCHAR(12));";


    /**
     * テスト用のテーブルの作成。
     * @throws SQLException
     */
    @BeforeClass
    public static void initialize() throws SQLException {
        // コネクションを生成
        con = destination.getConnection();

        // テーブルの生成
        Statement stmt = con.createStatement();
        stmt.execute(dropTable);
        stmt.execute(createTable);

        stmt.close();
    }


    /**
     * データベースへのコネクションを閉じる。
     * @throws SQLException
     */
    @AfterClass
    public static void destructor() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute(dropTable);
        con.close();
    }


    @Test
    public void createConnection() {
    }

    @Test
    public void setupTestDb() {
//        try {
//            Statement stmt = con.createStatement();
//
//            stmt.execute("CREATE TABLE IF NOT EXISTS USER (ID INT PRIMARY KEY, NAME VARCHAR(12), PASSWORD VARCHAR(12));");
//            stmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

}
