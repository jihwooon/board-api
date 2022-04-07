package jpa.imform.dto;

import java.time.LocalDateTime;

public interface CommentDtoImpl {
  String getContent();
  LocalDateTime getCreateDate();
  LocalDateTime getModifiedDate();
}
