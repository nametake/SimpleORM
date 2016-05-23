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
        sb.append(" ");
        sb.append("FROM ");
        sb.append(tableInfo.getTableName());
        String s = new String(sb);

        // Get select all sql
        String selectAllSql = sqlBuilder.selectAll();

        System.out.println("Create:" + s);
        System.out.println("Get   :" + selectAllSql);
        assertThat(selectAllSql, is(s));
    }

    @Test
    public void testSelectById() {

    }

    @After
    public void tearDown() throws Exception {
        tableInfo = null;
        sqlBuilder = null;
    }
}