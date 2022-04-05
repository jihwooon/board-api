package jpa.imform.domain;

import jpa.imform.dto.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String password;

  private String phone;

  private String email;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Board> boards = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  public void addComment(Comment comment) {
    this.comments.add(comment);
    comment.setMember(this);
  }

  @Builder
  public Member(String name, String password, String phone, String email) {
    this.name = name;
    this.password = password;
    this.phone = phone;
    this.email = email;
  }

  public void changeRequest(MemberDto.UpdateMemberRequest request) {
    this.name = request.getName();
    this.password = request.getPassword();
    this.phone = request.getPhone();
    this.email = request.getEmail();
  }

}
