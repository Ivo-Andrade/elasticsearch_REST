package test.elasticsearch_rest.demo.config;

import br.com.looplex.ElasticsearchSecurityDAOService;
import br.com.looplex.data_access_object.DataAccessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import test.elasticsearch_rest.demo.dao.user.UserDao;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public DataAccessObjectService newDataAccessObjectService(@Autowired @Qualifier("http-final") UserDao userDao){
        return new DataAccessObjectService(userDao);
    }

    @Bean
    public ElasticsearchSecurityDAOService newElasticsearchSecurityDAOService() {
        return new ElasticsearchSecurityDAOService();
    }

}
