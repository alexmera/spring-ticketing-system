package spring.ticketing.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:persistence.properties")
public class DataSourceConfig {

  @Autowired
  private Environment environment;

  @Bean
  public HikariConfig hikariConfig() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(environment.getProperty("db.jdbcUrl"));
    config.setUsername(environment.getProperty("db.username"));
    config.setPassword(environment.getProperty("db.password"));
    config.setDriverClassName(environment.getProperty("db.driverClassName"));
    return config;
  }

  @Bean
  public DataSource dataSource() {
    return new HikariDataSource(hikariConfig());
  }

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }
}
