package info.nametake.sqlbuilder;

import info.nametake.dao.TableInfo;
import info.nametake.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by shogo on 2016/05/22.
 */
public class SQLBuilderTest {

    private TableInfo<User> tableInfo;
    private SQLBuilder sqlBuilder;

    @Before
    public void setUp() throws Exception {
        tableInfo = new TableInfo<User>(User.class);
        sqlBuilder = new SQLBuilder(tableInfo);
    }

    @Test
    public void testSelectAll() {
        // Create sql
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ");
        sb.append(String.join(", ", tableInfo.getFieldNames()));
        sb.append(" FROM ");
        sb.append(tableInfo.getTableName());
        sb.append(" ;");
        String expected = new String(sb);

        // Get select all sql
        String actual = sqlBuilder.selectAll();

        System.out.println("Expected :" + expected);
        System.out.println("Actual   :" + actual);
        assertThat(expected, is(actual));
    }

    @Test
    public void testSelectById() {
        // ID
        int id = 1;

        // Create sql
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ");
        sb.append(String.join(", ", tableInfo.getFieldNames()));
        sb.append(" FROM ");
        sb.append(tableInfo.getTableName());
        sb.append(" WHERE ");
        sb.append(tableInfo.getPrimaryKey());
        sb.append(" = ");
        sb.append(id);
        sb.append(" ;");
        String expected = new String(sb);

        // Get select by id sql
        String actual = sqlBuilder.selectById(id);
        System.out.println("Expected :" + expected);
        System.out.println("Actual   :" + actual);
        assertThat(expected, is(actual));
    }

    @Test
    public void testDelete() {
        // Create sql
        StringBuffer sb = new StringBuffer();
        String expected = new String(sb);
    }

    @Test
    public void testUpdate() {
        // Create sql
        StringBuffer sb = new StringBuffer();
        String expected = new String(sb);
    }

    @Test
    public void testInsert() {
        // Create sql
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append(tableInfo.getTableName());
        sb.append(" (");
        sb.append(") SET ");
        String expected = new String(sb);
        System.out.println("Expected :" + expected);
    }

    @After
    public void tearDown() throws Exception {
        tableInfo = null;
        sqlBuilder = null;
    }
}