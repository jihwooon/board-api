package jpa.imform.repository;

import jpa.imform.domain.Address;
import jpa.imform.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void db_test_1() {
    Member member1 = Member.builder()
        .name("안지환")
        .email("jihwooon@gmail.com")
        .birth(920303)
        .password("1234")
        .address(new Address("서울", "경기","1234"))
        .build();

    Member saveId = memberRepository.save(member1);

    assertThat(saveId.getName()).isEqualTo("안지환");
    assertThat(saveId.getEmail()).isEqualTo("jihwooon@gmail.com");
    assertThat(saveId.getBirth()).isEqualTo(920303);
    assertThat(saveId.getPassword()).isEqualTo("1234");

  }

  @Test
  public void db_test_2() {
    Member member2 = Member.builder()
        .name("홍길동")
        .email("hong@gmail.com")
        .birth(810502)
        .password("1234")
        .address(new Address("서울", "경기","1234"))
        .build();

    Member saveId = memberRepository.save(member2);

    assertThat(saveId.getName()).isEqualTo("홍길동");
    assertThat(saveId.getEmail()).isEqualTo("hong@gmail.com");
    assertThat(saveId.getBirth()).isEqualTo(810502);
    assertThat(saveId.getPassword()).isEqualTo("1234");
  }
}
