package code.be.codebe.domain.repository;

import code.be.codebe.domain.model.User;

import java.util.Optional;

public interface UserRepository {
  Optional<User> findByUsername(String username);
}
