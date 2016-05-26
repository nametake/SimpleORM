package info.nametake.dao;

import info.nametake.BaseDBTest;
import info.nametake.exception.AnnotationException;
import info.nametake.models.SampleModel;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

/**
 * Created by nameki-shogo on 2016/05/26.
 */
public class SmapleDaoCreatedTest extends BaseDBTest {

    @Test
    public void testCreatedOriginDao() throws AnnotationException, SQLException {
        SampleModel model = new SampleModel();
        Dao<SampleModel> sampleModelDao = DaoFacotry.createDao(con, SampleModel.class);
        assertThat(sampleModelDao, instanceOf(SampleDao.class));
        sampleModelDao.select(model);
        assertThat(123, is(sampleModelDao.update(model)));
        assertThat(456, is(sampleModelDao.insert(model)));
        assertThat(789, is(sampleModelDao.delete(model)));
    }
}
