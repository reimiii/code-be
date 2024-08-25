package code.be.codebe.adapters.controller;

import code.be.codebe.adapters.dto.request.AuthRequest;
import code.be.codebe.adapters.dto.response.AuthResponse;
import code.be.codebe.adapters.dto.request.RegisterRequest;
import code.be.codebe.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor @RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthResponse> authenticate(
      @RequestBody AuthRequest request
  ) {
    return ResponseEntity.ok(authService.authenticate(request));
  }
}
