package code.be.codebe.application.service;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {
  @Test
  void generateSecretKey() {
    SecretKey secretKey = Jwts.SIG.HS256.key().build();
    String hexBinary = DatatypeConverter.printHexBinary(secretKey.getEncoded());
    System.out.printf("key = [%s]\n", hexBinary);
  }
}