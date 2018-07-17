package spring.ticketing.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@EnableJdbcHttpSession
public class SessionConfig {

  /**
   * DataSource especifico para spring session.
   * <p></p>
   * Solo es requerido si se utiliza un DataSource diferente al utilizado por la aplicación.
   */
  @Bean
  @SpringSessionDataSource
  public DataSource sessionDataSource(HikariConfig hikariConfig) {
    return new HikariDataSource(hikariConfig);
  }
}
