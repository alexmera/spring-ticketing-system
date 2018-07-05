package spring.ticketing.web;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import spring.ticketing.config.AppConfig;
import spring.ticketing.config.DataSourceConfig;
import spring.ticketing.config.JpaConfig;
import spring.ticketing.config.MvcConfig;
import spring.ticketing.config.WebSecurityConfig;

public class AppDispatcherServletInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{
        AppConfig.class,
        DataSourceConfig.class,
        JpaConfig.class,
        WebSecurityConfig.class
    };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{MvcConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  protected Filter[] getServletFilters() {
    CharacterEncodingFilter encodingFilter =
        new CharacterEncodingFilter("UTF-8", true);
    return new Filter[]{encodingFilter};
  }
}
