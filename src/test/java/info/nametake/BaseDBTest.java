package info.nametake;

import com.ninja_squad.dbsetup.destination.Destination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import org.junit.*;

import java.sql.*;


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
    }

    /**
     * データベースへのコネクションを閉じる。
     *
     * @throws SQLException
     */
    @AfterClass
    public static void destructor() throws SQLException {
        con.close();
    }

    /**
     * 各テスト開始前にデータを2個セット
     */
    @Before
    public void insertData() throws SQLException {

        // テーブルの生成
        Statement stmt = con.createStatement();
        stmt.execute(dropTableSql);
        stmt.execute(createTableSql);

        stmt.close();

        String taro = "INSERT INTO USER (NAME, PASS) VALUES('taro', 'taro')";
        PreparedStatement ps = con.prepareStatement(taro);
        ps.executeUpdate();
        ps.executeUpdate();
        ps.executeUpdate();

        String select = "SELECT * FROM USER;";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(select);
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " : " + rs.getString("NAME") + " : " + rs.getString("PASS"));
        }
    }

    /**
     * 各テスト終了後にテーブルのデータを全削除
     */
    @After
    public void deleteData() throws SQLException {
        String select = "SELECT * FROM USER;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(select);
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " : " + rs.getString("NAME") + " : " + rs.getString("PASS"));
        }
        System.out.println();

        String sql = "DELETE FROM USER";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();

        stmt = con.createStatement();
        stmt.execute(dropTableSql);
    }


}
