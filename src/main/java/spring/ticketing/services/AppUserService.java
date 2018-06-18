package spring.ticketing.services;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Service;
import spring.ticketing.model.AppUser;

@Service
public interface AppUserService {

  @Nonnull
  List<AppUser> allUsers();

  @Nonnull
  Optional<AppUser> findUserById(Integer id);

  @Nonnull
  AppUser createUser(AppUser appUser, String password);

  @Nonnull
  AppUser updateUser(AppUser appUser);

  @Nonnull
  AppUser deleteUser(Integer id);

}
