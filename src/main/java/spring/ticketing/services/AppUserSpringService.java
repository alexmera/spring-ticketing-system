package spring.ticketing.services;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Service;
import spring.ticketing.model.AppUser;
import spring.ticketing.repositories.AppUserDao;

@Service
public class AppUserSpringService implements AppUserService {

  private final AppUserDao appUserDao;

  public AppUserSpringService(AppUserDao appUserDao) {
    this.appUserDao = appUserDao;
  }

  @Nonnull
  @Override
  public List<AppUser> allUsers() {
    return appUserDao.all();
  }

  @Nonnull
  @Override
  public Optional<AppUser> findUserById(Integer id) {
    return appUserDao.findById(id);
  }

  @Nonnull
  @Override
  public AppUser createUser(AppUser appUser, String password) {
    return appUserDao.create(appUser, password);
  }

  @Nonnull
  @Override
  public AppUser updateUser(AppUser appUser) {
    return appUserDao.update(appUser);
  }

  @Nonnull
  @Override
  public AppUser deleteUser(Integer id) {
    return appUserDao.delete(id);
  }
}
