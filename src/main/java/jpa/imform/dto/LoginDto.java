package jpa.imform.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDto {

  @Getter
  public static class LoginRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotBlank(message = "패스워드를 입력해주세요")
    @Size(min = 4, max = 1024)
    private String password;
  }

  @Getter
  public static class LoginResponse {
    private String accessToken;

    public LoginResponse(String accessToken) {
      this.accessToken = accessToken;
    }

    public static LoginResponse of(String accessToken) {
      return new LoginResponse(accessToken);
    }

  }
}
