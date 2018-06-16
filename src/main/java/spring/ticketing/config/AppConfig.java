package spring.ticketing.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import spring.ticketing.repositories.jdbc.AppUserJdbcDao;
import spring.ticketing.services.AppUserSpringService;

@Configuration
@ComponentScan(basePackageClasses = {AppUserJdbcDao.class, AppUserSpringService.class})
public class AppConfig {

  @Bean
  public JdbcOperations jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public SimpleJdbcInsertOperations appUserJdbcInsert(JdbcTemplate jdbcTemplate) {
    return new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("app_user")
        .usingGeneratedKeyColumns("id");
  }
}
