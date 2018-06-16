package spring.ticketing.repositories.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;
import spring.ticketing.model.AppUser;
import spring.ticketing.model.AppUserRol;
import spring.ticketing.model.data.AppUserData;
import spring.ticketing.repositories.AppUserDao;

@Repository
public class AppUserJdbcDao implements AppUserDao {

  private static final ResultSetExtractor<AppUser> rsExtractor = (rs) ->
      new AppUserData(
          rs.getInt("id"),
          rs.getString("user_name"),
          rs.getString("user_email"),
          AppUserRol.valueOf(rs.getString("rol"))
      );
  private static final RowMapper<AppUser> rowMapper = (rs, rowNum) -> rsExtractor.extractData(rs);
  private final JdbcOperations jdbc;
  private final SimpleJdbcInsertOperations jdbcInsert;

  public AppUserJdbcDao(
      JdbcOperations jdbc,
      @Qualifier("appUserJdbcInsert") SimpleJdbcInsertOperations jdbcInsert
  ) {
    this.jdbc = jdbc;
    this.jdbcInsert = jdbcInsert;
  }

  @Override
  public AppUser create(AppUser entity, String rawPassword) {
    Map<String, Object> args = new HashMap<>();
    args.put("user_name", entity.getUserName());
    args.put("user_email", entity.getUserEmail());
    args.put("rol", entity.getRol().name());
    // FIXME DANGER!!!!!!!!!
    args.put("password", rawPassword);

    Number id = jdbcInsert.executeAndReturnKey(args);
    return getOne(id.intValue());
  }

  @Override
  public AppUser getOne(Integer id) {
    return findById(id).orElseThrow(() ->
        new NoSuchElementException("AppUser not found - id: " + id)
    );
  }

  @Override
  public Optional<AppUser> findById(Integer id) {
    AppUser appUser = jdbc.query("SELECT * FROM app_user WHERE id = ?", rsExtractor, id);
    if (appUser != null) {
      return Optional.of(appUser);
    }
    return Optional.empty();
  }

  @Override
  public List<AppUser> all() {
    return jdbc.query("SELECT * FROM app_user", rowMapper);
  }

  @Override
  public AppUser update(AppUser entity) {
    String query = "UPDATE app_user SET user_name = ?, user_email = ?, rol = ? WHERE id = ?";
    int num = jdbc.update(
        query,
        entity.getUserName(),
        entity.getUserEmail(),
        entity.getRol().name(),
        entity.getId()
    );
    if (num == 1) {
      return getOne(entity.getId());
    }
    throw new IllegalStateException(
        "The update operation must affect exactly one row. Rows affected " + num
    );
  }

  @Override
  public AppUser delete(Integer id) {
    AppUser appUser = getOne(id);
    String query = "DELETE FROM app_user WHERE id = ?";
    int num = jdbc.update(query, id);
    if (num == 1) {
      return appUser;
    }
    throw new IllegalStateException(
        "The delete operation must affect exactly one row. Rows affected " + num
    );
  }
}
