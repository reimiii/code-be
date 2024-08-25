package code.be.codebe.adapters.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class RegisterRequest {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  private String name;
}
