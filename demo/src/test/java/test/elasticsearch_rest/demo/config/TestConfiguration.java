package test.elasticsearch_rest.demo.config;

import br.com.looplex.data_access_object.DataAccessObjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import test.elasticsearch_rest.demo.dao.user.UserDao;

public class TestConfiguration {

    UserDao userDao;

    @Test
    public void testNewDataAccessObjectService(){
        new DataAccessObjectService(userDao);
    }

}
