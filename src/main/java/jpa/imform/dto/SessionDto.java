package jpa.imform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class SessionDto {

  @Getter
  @Builder
  @AllArgsConstructor
  public static class SessionResponse {
    private String accessToken;
  }
}
