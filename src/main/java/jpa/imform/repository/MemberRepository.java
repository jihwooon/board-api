package jpa.imform.repository;

import jpa.imform.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

  @Query(value = "select m from Member m")
  List<Member> findAllWithDevelop();

  @Query(value = "select m from Member m where m.id = :id")
  Member findIdWithDevelop(@Param("id") final Long id);

}
