package spring.ticketing.web.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import spring.ticketing.model.AppUser;
import spring.ticketing.model.AppUserRol;
import spring.ticketing.model.Client;

@Data
public class ClientCommand implements Client {

  private Integer id;

  @Valid
  @NotNull
  private AppUserCommand appUser;

  @NotEmpty
  private String clientName;

  @Data
  static class AppUserCommand implements AppUser {

    @NotNull
    private Integer id;

    private String userName;

    private String userEmail;

    private AppUserRol rol;
  }
}
