package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwsztar.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByLogin(String login);
}
