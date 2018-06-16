package spring.ticketing.services;

import java.util.List;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Service;
import spring.ticketing.model.AppUser;

@Service
public interface AppUserService {

  @Nonnull
  List<AppUser> allUsers();

}
