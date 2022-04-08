package jpa.imform.repository.JpaRepository;

import jpa.imform.domain.Member;
import jpa.imform.dto.MemberSimpleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

  @Query(value = "select m from Member m")
  List<Member> findAllWithDevelop();

  @Query(value = "select m from Member m where m.id = :id")
  Optional<Member> findIdWithDevelop(@Param("id") Long id);

  @Query(value = "select count(m.name) from Member m")
  Long findIdWithMemberNameCount();

//  @Query(value = "SELECT m.name, COUNT(m.name) " +
//      " FROM Member m GROUP BY m.name" +
//      " ORDER BY m.id")
//  List<Member> findAllWithDevelop();

//  @Query(value = "select new jpa.imform.dto.MemberDto.ListMemberRequest(m.name, m.phone, m.email) FROM Member m")
//  List<Member> findListDto();

//  @Query(value = "select m.name, m.phone, m.email FROM Member m", nativeQuery = true)
//  List<Tuple> findListDto();

//  @Query(value = "select new jpa.imform.dto.MemberListDto(m.name, m.email, m.phone, m.boards) from Member m")
//  List<MemberListDto> findListDtobyBoards();

//  @Query(value = "SELECT new jpa.imform.dto.MemberSimpleDto.MemberSimpleDtoV1(m.name, m.email) FROM Member m") // inner 클래스로하면 Unable to locate class 오류 메시지 발생
//  List<MemberSimpleDto.MemberSimpleDtoV1> findListDto();

  @Query(value = "SELECT new jpa.imform.dto.MemberSimpleDto(m.name, m.email) FROM Member m")
  List<MemberSimpleDto> findListDto();

//  @Query(value = "select new jpa.imform.dto.MemberSimpleDto.MemberSimpleDtoV2(m.name, m.phone , m.email) from Member m")
//  List<MemberSimpleDto.MemberSimpleDtoV2> findListDto2();

}

