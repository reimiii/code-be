package code.be.codebe.application.service;

import code.be.codebe.adapters.dto.request.AuthRequest;
import code.be.codebe.adapters.dto.request.RegisterRequest;
import code.be.codebe.adapters.dto.response.AuthResponse;
import code.be.codebe.domain.model.Role;
import code.be.codebe.domain.model.User;
import code.be.codebe.infrastructure.persistence.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service @Transactional
@RequiredArgsConstructor
public class AuthService {

  private final JpaUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

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
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(),
              request.getPassword()
          )
      );
    } catch (AuthenticationException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "username or password wrong");
    }

    var user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "username or password wrong"));

    var token = jwtService.generateToken(user);

    return AuthResponse.builder().token(token).build();
  }
}
