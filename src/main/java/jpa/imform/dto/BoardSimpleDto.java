package jpa.imform.dto;

import lombok.Data;

@Data
public class BoardSimpleDto {
  private Long boardId;
  private String content;
  private String title;
  private String name;

  public BoardSimpleDto(Long boardId, String content, String title, String name) {
    this.content = content;
    this.title = title;
    this.name = name;
  }
}
