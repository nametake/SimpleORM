package info.nametake.dao;

import info.nametake.models.User;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class DaoTest extends BaseDBTest {


    private static Dao<User> userDao = null;
    private User user = null;

    @BeforeClass
    public static void createDao() {
        userDao = DaoManager.createDao(con, User.class);
    }

    @AfterClass
    public static void deleteDao() {
        userDao = null;
    }

    /**
     * ユーザインスタンスの作成
     */
    @Before
    public void createUser() {
        user = new User();
        user.setId(10);
        user.setName("Taro");
        user.setPassword("pass");
    }

    /**
     * ユーザインスタンスを削除
     */
    @After
    public void deleteUser() {
        user = null;
    }

    @Test
    public void testCreateDao() {
        Dao<User> userDao = DaoManager.createDao(con, User.class);
        assertThat(userDao, instanceOf(Dao.class));
    }

    @Test
    public void testSelect() throws SQLException {
        int id = 1;
        User user = userDao.selectById(id);
        if (user == null) {
            fail("Failed selectById id 1");
            return;
        }
        assertThat(id, is(user.getId()));
    }

    @Test
    public void testInsert() throws SQLException {
        int result = userDao.insert(user);
        assertThat(1, is(result));
    }

    @Test
    public void testDelete() throws SQLException {
        int result = userDao.insert(user);
        assertThat(0, is(result));
    }

    @Test
    public void testSelectAll() throws SQLException{
        List<User> users = userDao.selectAll();
        if (users == null) {
            fail("selectAll() result is null.");
            return;
        }
        // もし長さが0なら失敗
        assertThat(0, is(not(users.size())));
        //  Userクラスのインスタンス化を設定
        for (User user: users) {
            assertThat(user, instanceOf(User.class));
        }
    }

}