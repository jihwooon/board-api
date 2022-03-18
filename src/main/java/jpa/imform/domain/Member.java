package jpa.imform.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  @Column(name = "member_name")
  private String name;

  @Column(name = "member_password")
  private String password;

  @Column(name = "member_birth")
  private Integer birth;

  @Column(name = "member_email")
  private String email;

  @Embedded
  @Column(name = "member_address")
  private Address address;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Board> boards = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Comment> comments = new ArrayList<>();

  @Builder
  public Member(String name, String password, Integer birth, String email) {
    this.name = name;
    this.password = password;
    this.birth = birth;
    this.email = email;
  }
}
