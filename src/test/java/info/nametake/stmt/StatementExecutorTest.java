package info.nametake.stmt;

import com.sun.tools.corba.se.idl.constExpr.Not;
import info.nametake.BaseDBTest;
import info.nametake.exception.AnnotationException;
import info.nametake.models.NotAnnotationModel;
import info.nametake.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by shogo on 2016/05/20.
 */
public class StatementExecutorTest extends BaseDBTest {
    private StatementExecutor<User> stmtExecutor;

    @Before
    public void createStmtExecutor() throws AnnotationException {
        stmtExecutor = StatementExecutor.createStatementExecutor(con, User.class);
    }

    @After
    public void releaseStmtExecutor() {
        stmtExecutor = null;
    }


    @Test(expected = AnnotationException.class)
    public void testCheckModelDataFormat() throws AnnotationException {
        StatementExecutor<NotAnnotationModel> stmte
                = StatementExecutor.createStatementExecutor(con, NotAnnotationModel.class);
    }
}