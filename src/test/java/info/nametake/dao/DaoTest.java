package info.nametake.dao;

import info.nametake.models.User;
import org.junit.*;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by nameki-shogo on 2016/05/19.
 */
public class DaoTest extends BaseDBTest {


    Dao<User> userDao = null;
    User user = null;

    @BeforeClass
    public void createDao() {
        userDao = DaoManager.createDao(con, User.class);
    }

    @AfterClass
    public void deleteDao() {
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
    public void testInsert() throws SQLException {
        fail();

    }

    @Test
    public void testDelete() throws SQLException {
        fail();

    }

    @Test
    public void testSelect() throws SQLException {
        fail();
    }
}