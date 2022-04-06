package jpa.imform.repository;

import jpa.imform.domain.Member;
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
////      " FROM Member m GROUP BY m.name" +
////      " ORDER BY m.id")
////  List<Member> findAllWithDevelop();

}
