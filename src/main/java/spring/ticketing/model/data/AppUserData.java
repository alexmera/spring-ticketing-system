package spring.ticketing.model.data;

import javax.annotation.Nonnull;
import lombok.Data;
import spring.ticketing.model.AppUser;
import spring.ticketing.model.AppUserRol;

@Data
public class AppUserData implements AppUser {

  private Integer id;

  @Nonnull
  private String userName;

  @Nonnull
  private String userEmail;

  @Nonnull
  private AppUserRol rol;

  public AppUserData() {
  }

  public AppUserData(
      @Nonnull Integer id,
      @Nonnull String userName,
      @Nonnull String userEmail,
      @Nonnull AppUserRol rol
  ) {
    this.id = id;
    this.userName = userName;
    this.userEmail = userEmail;
    this.rol = rol;
  }

  public AppUserData(
      @Nonnull String userName,
      @Nonnull String userEmail,
      @Nonnull AppUserRol rol
  ) {
    this.userName = userName;
    this.userEmail = userEmail;
    this.rol = rol;
  }
}
