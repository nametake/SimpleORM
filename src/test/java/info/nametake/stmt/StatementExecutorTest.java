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
import static org.hamcrest.core.IsNot.not;
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

    @Test
    public void testSelectByField() throws AnnotationException, SQLException {
        // Create User
        String n = "Shimamura Uduki";
        String p = "Ganbarimasu!";
        User insertUser = new User();
        insertUser.setName(n);
        insertUser.setPassword(p);

        // Insert
        StatementExecutor<User> stmte
                = StatementExecutor.createStatementExecutor(con, User.class);
        int id = stmte.insertAutoIncrementedId(stmtBuilder.getInsertStatement(insertUser));


        List<User> list = null;
        User user = null;
        // NAME フィールドが一致するかを確認
        list = stmte.execute(stmtBuilder.getSelectByFieldStatement(fieldNameName, n));
        assertThat(1, is(list.size()));
        user = list.get(0);
        assertThat(user, instanceOf(User.class));
        assertThat(n, is(user.getName()));

        // NAME フィールドが一致するかを確認
        list = stmte.execute(stmtBuilder.getSelectByFieldStatement(fieldNamePass, p));
        assertThat(1, is(list.size()));
        user = list.get(0);
        assertThat(user, instanceOf(User.class));
        assertThat(p, is(user.getPassword()));

    }

    @Test
    public void testInsert() throws Exception {
        String username = "hoge";
        String password = "ghmcka";
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        StatementExecutor<User> stmte
                = StatementExecutor.createStatementExecutor(con, User.class);
        int id = stmte.insertAutoIncrementedId(stmtBuilder.getInsertStatement(user));

        User selectUser = stmte.execute(stmtBuilder.getSelectByIdStatement(id)).get(0);

        assertThat(username, is(selectUser.getName()));
        assertThat(password, is(selectUser.getPassword()));
    }

    @Test
    public void testUpdate() throws Exception {
        // StatementExecutorを作成
        StatementExecutor<User> stmte
                = StatementExecutor.createStatementExecutor(con, User.class);
        // ユーザIDが1のユーザをSelect
        User selectUser = stmte.execute(stmtBuilder.getSelectByIdStatement(2)).get(0);
        selectUser.setName("foo");
        selectUser.setPassword("foobar");

        // Update
        int result = stmte.update(stmtBuilder.getUpdateStatement(selectUser));
        assertThat(1, is(result));
        int id = selectUser.getId();

        // アップデート
        User updatedUser = stmte.execute(stmtBuilder.getSelectByIdStatement(id)).get(0);

        assertThat(selectUser.getName(), is(updatedUser.getName()));
        assertThat(selectUser.getPassword(), is(updatedUser.getPassword()));

    }

    @Test
    public void testDelete() throws Exception {
        // StatementExecutorを作成
        StatementExecutor<User> stmte
                = StatementExecutor.createStatementExecutor(con, User.class);
        // ユーザIDが1のユーザをSelect
        User selectUser = stmte.execute(stmtBuilder.getSelectByIdStatement(2)).get(0);

        // アップデート
        int result = stmte.update(stmtBuilder.getDeleteStatement(selectUser));

        assertThat(1, is(result));
    }



    @Test
    public void testDeleteById() throws Exception {
        StatementExecutor<User> stmte
                = StatementExecutor.createStatementExecutor(con, User.class);
        int result = stmte.update(stmtBuilder.getDeleteByIdStatement(1));
        assertThat(1, is(result));
    }

    @Test
    public void testDeleteByField() throws Exception {
        StatementExecutor<User> stmte
                = StatementExecutor.createStatementExecutor(con, User.class);
        int result = stmte.update(stmtBuilder.getDeleteByFieldStatement("NAME", "taro"));
        assertThat(0, is( not(result)));
    }

    @Test
    public void testCountByField() throws Exception {
        StatementExecutor<User> stmte
                = StatementExecutor.createStatementExecutor(con, User.class);
        int result = stmte.aggregateExecute(stmtBuilder.getCountByFieldStatement("NAME", "taro"));
        assertThat(3, is(result));
    }
}