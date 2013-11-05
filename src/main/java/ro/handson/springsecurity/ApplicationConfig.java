package ro.handson.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import ro.handson.springsecurity.config.DatasourceConfig;
import ro.handson.springsecurity.controllers.ApplicationController;
import ro.handson.springsecurity.services.UserService;

/**
 * Created by jtonic on 11/4/13.
 */
@Configuration
@Import({DatasourceConfig.class})
@ImportResource("classpath:/META-INF/spring/spring-config.xml")
@PropertySource("classpath:datasource.properties")
public class ApplicationConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public ApplicationController applicationController() {
        return new ApplicationController();
    }

}
