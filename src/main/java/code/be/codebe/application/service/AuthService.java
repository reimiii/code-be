package code.be.codebe.application.service;

import code.be.codebe.adapters.dto.request.AuthRequest;
import code.be.codebe.adapters.dto.request.RegisterRequest;
import code.be.codebe.adapters.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AuthService {
  public AuthResponse register(RegisterRequest request) {
    return null;
  }

  public AuthResponse authenticate(AuthRequest request) {
    return null;
  }
}
