package jpa.imform.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(of = "boardId")
public class MemberSimpleDto {

  private Long boardId;
  private String name;
  private String email;
  private List<BoardSimpleDto> boardSimpleDtos;

  public MemberSimpleDto(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public MemberSimpleDto(Long boardId, String name, String email, List<BoardSimpleDto> boardSimpleDtos) {
    this.name = name;
    this.email = email;
    this.boardSimpleDtos = boardSimpleDtos;
  }

}
