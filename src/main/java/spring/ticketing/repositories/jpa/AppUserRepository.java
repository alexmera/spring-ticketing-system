package spring.ticketing.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.ticketing.model.jpa.AppUserJpa;

public interface AppUserRepository extends JpaRepository<AppUserJpa, Integer> {

}
