package jpa.imform.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MemberListDto {

  private String name;
  private String email;
  private String phone;
  private List<BoardSimpleDto> boards;// <- 이거의 문제 해결

  public MemberListDto(String name, String email, String phone, List<BoardSimpleDto> boards) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.boards = boards;
  }
}
