package spring.ticketing.model.data;

import javax.annotation.Nonnull;
import lombok.Data;
import spring.ticketing.model.Client;
import spring.ticketing.model.jpa.AppUserJpa;

@Data
public class ClientData implements Client {

  private Integer id;

  @Nonnull
  private AppUserJpa appUser;

  @Nonnull
  private String clientName;

  public ClientData() {
  }

  public ClientData(@Nonnull Integer id, @Nonnull AppUserJpa appUser, @Nonnull String clientName) {
    this.id = id;
    this.appUser = appUser;
    this.clientName = clientName;
  }

  public ClientData(@Nonnull AppUserJpa appUser, @Nonnull String clientName) {
    this.appUser = appUser;
    this.clientName = clientName;
  }
}
