package info.nametake.dao;

import info.nametake.BaseDBTest;
import info.nametake.exception.AnnotationException;
import info.nametake.models.NotAnnotationModel;
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
    public static void createDao() throws AnnotationException, SQLException {
        userDao = DaoFacotry.createDao(con, User.class);
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
        user.setName("Tarooooo");
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
    public void testCreateDao() throws AnnotationException, SQLException {
        System.out.println("CREATE DAO");
        Dao<User> userDao = DaoFacotry.createDao(con, User.class);
        assertThat(userDao, instanceOf(Dao.class));
    }

    @Test(expected = AnnotationException.class)
    public void testNotTableAnnotation() throws AnnotationException, SQLException {
        System.out.println("NOT TABLE ANNOTATION");
        Dao<User> userDao = DaoFacotry.createDao(con, NotAnnotationModel.class);
    }

    @Test
    public void testSelect() throws SQLException {
        System.out.println("SELECT");
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
        System.out.println("INSERT");
        int id = userDao.insert(user);
        User u = userDao.selectById(id);
        assertThat(user.getName(), is(u.getName()));
        assertThat(user.getPassword(), is(u.getPassword()));
    }

    @Test
    public void testDelete() throws SQLException {
        System.out.println("DELETE");
        User user = new User();
        user.setId(1);
        user.setName("Jiro");
        user.setPassword("1234");
        int result = userDao.delete(user);
        assertThat(1, is(result));
    }

    @Test
    public void testUpdate() throws SQLException {
        System.out.println("UPDATE");
        User user = new User();
        user.setId(2);
        user.setName("Jiro");
        user.setPassword("1234");
        int result = userDao.update(user);
        assertThat(1, is(result));
    }

    @Test
    public void testSelectAll() throws SQLException{
        System.out.println("SELECT ALL");
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