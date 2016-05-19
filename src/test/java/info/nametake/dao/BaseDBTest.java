package info.nametake.dao;

import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


abstract public class BaseDBTest {
    private static String url = "jdbc:h2:./testdb";
    private static String user = "sa";
    private static String pass = "";
    protected static Destination destination = new DriverManagerDestination(url, user, pass);
    protected static Connection con;

    private static String dropTableSql = "DROP TABLE IF EXISTS USER;";
    private static String createTableSql = "CREATE TABLE IF NOT EXISTS " +
            "USER (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(12), PASS VARCHAR(12));";


    // データベースのデータ
    protected static String username = "Taro";
    protected static String password = "rota";


    /**
     * テスト用のテーブルの作成。
     *
     * @throws SQLException
     */
    @BeforeClass
    public static void initialize() throws SQLException {
        // コネクションを生成
        con = destination.getConnection();

        // テーブルの生成
        Statement stmt = con.createStatement();
        stmt.execute(dropTableSql);
        stmt.execute(createTableSql);

        stmt.close();
    }

    /**
     * データベースへのコネクションを閉じる。
     *
     * @throws SQLException
     */
    @AfterClass
    public static void destructor() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute(dropTableSql);
        con.close();
    }

    /**
     * 各テスト開始前にデータを2個セット
     */
    @Before
    public void insertData() {
        String taro = "INSERT INTO USER (NAME, PASS) VALUES('taro', 'taro')";
        try {
            PreparedStatement ps = con.prepareStatement(taro);
            ps.executeUpdate();
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 各テスト終了後にテーブルのデータを全削除
     */
    @After
    public void deleteData() {
        String sql = "DELETE FROM USER";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    abstract public void testInsert() throws SQLException;

    @Test
    abstract public void testDelete() throws SQLException;

    @Test
    abstract public void testSelect() throws SQLException;
}
