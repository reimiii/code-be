package code.be.codebe.infrastructure.persistence;

import code.be.codebe.domain.model.User;
import code.be.codebe.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends UserRepository, JpaRepository<User, String> {

  @Override
  Optional<User> findByUsername(String username);

}
