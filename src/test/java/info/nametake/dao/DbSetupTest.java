package info.nametake.dao;

import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import org.junit.*;

import java.sql.*;

import static org.hamcrest.core.Is.is;
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

    private static String dropTableSql = "DROP TABLE IF EXISTS USER;";
    private static String createTableSql = "CREATE TABLE IF NOT EXISTS " +
            "USER (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(12), PASS VARCHAR(12));";


    // データベースのデータ
    private static String username = "Taro";
    private static String password = "rota";


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
        stmt.execute(dropTableSql);
        stmt.execute(createTableSql);

        stmt.close();
    }


    /**
     * データベースへのコネクションを閉じる。
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

    /**
     * Insert test
     * @throws SQLException
     */
    @Test
    public void testInsert() throws SQLException {
        // Create insert sql
        String sql = "INSERT INTO USER (NAME, PASS) VALUES('taro', 'taro')";
        // Create stmt
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();

        // Execute
        int result = ps.executeUpdate();

        // Test
        assertThat(1, is(result));
    }

    /**
     * Select test
     * @throws SQLException
     */
    @Test
    public void testSelect() throws SQLException {
        String sql = "SELECT * FROM USER WHERE ID = 1";
        // Create stmt
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        // Test
        while (rs.next()) {
            assertThat(1, is(rs.getInt("ID")));
            assertThat("taro", is(rs.getString("NAME")));
            assertThat("taro", is(rs.getString("PASS")));
        }
    }

    /**
     * Delete test
     * @throws SQLException
     */
    @Test
    public void testDelete() throws SQLException {
        // Create delete sql
        String sql = "DELETE FROM USER WHERE ID = 1";
        // Create stmt
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();

        // Execute
        int result = ps.executeUpdate();

        // Test
        assertThat(0, is(result));
    }

}
