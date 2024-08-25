package code.be.codebe.application.service;

import code.be.codebe.adapters.dto.request.AuthRequest;
import code.be.codebe.adapters.dto.request.RegisterRequest;
import code.be.codebe.adapters.dto.response.AuthResponse;
import code.be.codebe.domain.model.Role;
import code.be.codebe.domain.model.User;
import code.be.codebe.infrastructure.persistence.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class AuthService {

  private final JpaUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthResponse register(RegisterRequest request) {

    var user = new User();
    user.setName(request.getName());
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.USER);
    userRepository.save(user);

    var token = jwtService.generateToken(user);

    return AuthResponse.builder().token(token).build();
  }

  public AuthResponse authenticate(AuthRequest request) {
    return null;
  }
}
