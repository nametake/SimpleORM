package info.nametake.sqlbuilder;

import info.nametake.dao.TableInfo;
import info.nametake.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shogo on 2016/05/22.
 */
public class SQLBuilderTest {

    private SQLBuilder sqlBuilder;

    @Before
    public void setUp() throws Exception {
        TableInfo<User> tableInfo = new TableInfo<User>(User.class);
        sqlBuilder = new SQLBuilder(tableInfo);
    }

    @Test
    public void testSelectAll() {
    }

    @After
    public void tearDown() throws Exception {
        sqlBuilder = null;
    }
}