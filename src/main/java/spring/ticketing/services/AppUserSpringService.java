package spring.ticketing.services;

import java.util.List;
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
}
