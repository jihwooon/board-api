package jpa.imform.domain;

import jpa.imform.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(name = "member_name")
  private String name;

  @Column(name = "member_password")
  private String password;

  @Column(name = "member_birth")
  private String birth;

  @Column(name = "member_email")
  private String email;

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Board> boards = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  public void addComment(Comment comment) {
    this.comments.add(comment);
    comment.setMember(this);
  }

  @Builder
  public Member(Long id, String name, String password, String birth, String email, Address address) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.birth = birth;
    this.email = email;
    this.address = address;
  }

  public void change(Member update) {
    this.name = update.getName();
    this.password = update.getPassword();
    this.birth = update.getBirth();
    this.email = update.getEmail();
  }

  public void changeRequest(MemberDto.UpdateMemberRequest request) {
    this.name = request.getName();
    this.password = request.getPassword();
    this.birth = request.getBirth();
    this.email = request.getEmail();
  }

}
