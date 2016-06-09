package info.nametake.dao;

import info.nametake.BaseDBTest;
import info.nametake.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nameki-shogo on 2016/06/07.
 */
public class DaoFacotryTestTime extends BaseDBTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    public static void timeTest10MillionCreateDaoTest() throws Exception {
        int count =            10000000;
        Dao<User> userDao = null;

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            userDao = DaoFacotry.createDao(con, User.class);
        }
        long end = System.currentTimeMillis();

        System.out.println("Create Dao Time: " + (end - start)  + "ms");
    }

    public static void main(String[] args) throws Exception {
        timeTest10MillionCreateDaoTest();
    }

}