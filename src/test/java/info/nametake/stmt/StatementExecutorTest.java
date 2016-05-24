package info.nametake.stmt;

import info.nametake.BaseDBTest;
import info.nametake.dao.TableInfo;
import info.nametake.exception.AnnotationException;
import info.nametake.models.NotAnnotationModel;
import info.nametake.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by shogo on 2016/05/20.
 */
public class StatementExecutorTest extends BaseDBTest {
    private TableInfo<User> tableInfo;
    private StatementBuilder<User> stmtBuilder;
    private StatementExecutor<User> stmtExecutor;

    @Before
    public void createStmtExecutor() throws AnnotationException {
        tableInfo = new TableInfo<User>(User.class);
        stmtBuilder = new StatementBuilder<User>(con, tableInfo);
        stmtExecutor = StatementExecutor.createStatementExecutor(con, User.class);
    }

    @After
    public void releaseStmtExecutor() {
        tableInfo = null;
        stmtBuilder = null;
        stmtExecutor = null;
    }


    @Test(expected = AnnotationException.class)
    public void testCheckModelDataFormat() throws AnnotationException {
        StatementExecutor<NotAnnotationModel> stmte
                = StatementExecutor.createStatementExecutor(con, NotAnnotationModel.class);
    }

    @Test
    public void testSelectAll() throws AnnotationException, SQLException {
        StatementExecutor<User> stmte
                = StatementExecutor.createStatementExecutor(con, User.class);
        List<User> list = stmte.execute(stmtBuilder.getSelectAllStatement());
        for (User user: list) {
            assertThat(user, instanceOf(User.class));
        }
    }

    @Test
    public void testSelectById() throws AnnotationException, SQLException {
        int id = 1;
        StatementExecutor<User> stmte
                = StatementExecutor.createStatementExecutor(con, User.class);
        List<User> list = stmte.execute(stmtBuilder.getSelectByIdStatement(id));
        assertThat(1, is(list.size()));
        User user = list.get(0);
        assertThat(user, instanceOf(User.class));
        assertThat(id, is(user.getId()));
    }


}