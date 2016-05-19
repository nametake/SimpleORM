package info.nametake.dao;

import info.nametake.BaseDBTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class DbSetupTest extends BaseDBTest {

    /**
     * Insert test
     *
     * @throws SQLException
     */
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
     *
     * @throws SQLException
     */
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
     *
     * @throws SQLException
     */
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
