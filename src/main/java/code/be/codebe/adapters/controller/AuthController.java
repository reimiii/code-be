package code.be.codebe.adapters.controller;

import code.be.codebe.adapters.dto.request.AuthRequest;
import code.be.codebe.adapters.dto.response.AuthResponse;
import code.be.codebe.adapters.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor @RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(
      @RequestBody RegisterRequest request
  ) {

  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthResponse> authenticate(
      @RequestBody AuthRequest request
  ) {
    // meh
  }
}
