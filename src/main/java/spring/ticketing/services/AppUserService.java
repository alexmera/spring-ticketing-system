package spring.ticketing.services;

import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import spring.ticketing.model.AppUser;
import spring.ticketing.model.AppUserRol;

public interface AppUserService {

  @Nonnull
  <T extends AppUser> List<T> allUsers();

  @Nonnull
  <T extends AppUser> Optional<T> findUserById(Integer id);

  @Nonnull
  AppUser createUser(AppUser appUser, String password);

  @Nonnull
  AppUser updateUser(AppUser appUser);

  @Nonnull
  AppUser deleteUser(Integer id);

  @Nonnull
  AppUser findUserByUserName(String userName);

  @Nonnull
  <T extends AppUser> List<T> findUserByRol(AppUserRol rol);

}
