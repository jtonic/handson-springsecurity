package ro.handson.springsecurity.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created by jtonic on 11/4/13.
 */
@Configuration
public class DatasourceConfig {

    private Logger LOG = Logger.getLogger(DatasourceConfig.class);

    private String jdbcDriverClassname;
    private Integer maxPoolSize;
    private Integer minPoolSize;
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;

    @Autowired
    public void config(Environment env) {
        jdbcDriverClassname = env.getRequiredProperty("jdbc.driver");
        jdbcUrl = env.getRequiredProperty("jdbc.url");
        jdbcUsername = env.getRequiredProperty("jdbc.username");
        jdbcPassword = env.getRequiredProperty("jdbc.password");
        maxPoolSize = env.getRequiredProperty("jdbc.pool.maxPoolSize", Integer.class);
        minPoolSize = env.getRequiredProperty("jdbc.pool.minPoolSize", Integer.class);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(destroyMethod = "close")
    public javax.sql.DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        try {
            ds.setDriverClassName(jdbcDriverClassname);
            ds.setUrl(jdbcUrl);
            ds.setUsername(jdbcUsername);
            ds.setPassword(jdbcPassword);

            ds.setInitialSize(minPoolSize);
            ds.setMaxActive(maxPoolSize);
            ds.setMaxIdle(maxPoolSize);
            ds.setMinIdle(0);

            ds.setTestOnBorrow(true);
            ds.setTestOnReturn(true);
            ds.setTestWhileIdle(true);
            ds.setTimeBetweenEvictionRunsMillis(1_800_000);
            ds.setNumTestsPerEvictionRun(3);
            ds.setMinEvictableIdleTimeMillis(1_800_000);
            ds.setValidationQuery("SELECT version();");
            LOG.info("C3po ComboPooledDataSource has successfully configured.");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException("An Error occurred while setting the jdbc driver class for DBCP.");
        }
        return ds;
    }

}
