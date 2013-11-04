package ro.handson.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import ro.handson.springsecurity.controllers.ApplicationController;
import ro.handson.springsecurity.services.UserService;

/**
 * Created by jtonic on 11/4/13.
 */
@Configuration
@ImportResource("classpath:/META-INF/spring/spring-config.xml")
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
