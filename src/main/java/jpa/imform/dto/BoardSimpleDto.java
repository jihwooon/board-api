package jpa.imform.dto;

import lombok.Getter;

@Getter
public class BoardSimpleDto {
  private String content;
  private String title;
  private String name;

  public BoardSimpleDto(String content, String title, String name) {
    this.content = content;
    this.title = title;
    this.name = name;
  }
}
