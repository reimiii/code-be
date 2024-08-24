package code.be.codebe.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${jwt.secret.key}")
  private String secretKey;

  public String extractUsername(String token) {
    return null;
  }
}
