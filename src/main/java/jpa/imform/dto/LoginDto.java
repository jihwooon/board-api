package jpa.imform.dto;

import jpa.imform.domain.Member;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDto {

    @Getter
    @Builder
    public static class LoginInRequest {

        @NotBlank(message = "이메일을 입력해주세요")
        @Email(message = "이메일 양식을 지켜주세요")
        private String email;

        @NotBlank(message = "패스워드를 입력해주세요")
        @Size(min = 4, max = 1024)
        private String password;
    }

    @Getter
    @Builder
    public static class LoginInResponse {
        private String email;
        private String password;
        private String accessToken;

        public LoginInResponse(Member member, String accessToken) {
            this.email = member.getEmail();
            this.password = member.getPassword();
            this.accessToken = accessToken;
        }

        public static LoginDto.LoginInResponse of(final Member member, final String accessToken) {
            return new LoginInResponse(member, accessToken);
        }
    }
}
