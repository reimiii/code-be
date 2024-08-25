package code.be.codebe.adapters.controller;

import code.be.codebe.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController @RequestMapping("/api/v1/home")
public class HomeController {

  @GetMapping
  public ResponseEntity<Map<String, String>> home() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = (User) auth.getPrincipal();


    return ResponseEntity.ok(Map.of("name", user.getName()));
  }
}
