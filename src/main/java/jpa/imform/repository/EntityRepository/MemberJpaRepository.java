package jpa.imform.repository.EntityRepository;

import jpa.imform.domain.Member;
import jpa.imform.dto.BoardSimpleDto;
import jpa.imform.dto.MemberSimpleDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberJpaRepository {

  @PersistenceContext
  EntityManager em;

  public List<Member> findAll() {
    return em.createQuery("select m from Member m", Member.class)
        .getResultList();
  }

  public Member fineOne(final Long id) {
    return em.find(Member.class, id);
  }

  public long count() {
    return em.createQuery("select count(m) from Member m", Long.class)
        .getSingleResult();
  }

//  public List<MemberSimpleDto> findListSimpleBoardDtos() {
//    List<MemberSimpleDto> result = findSimpleMember();
//    result.forEach(o -> {
//      List<BoardSimpleDto> boardSimple = findSimpleBoard(o.getBoardId());
//      o.setBoardSimpleDtos(boardSimple);
//    });
//    return result;
//  }

//  private List<BoardSimpleDto> findSimpleBoard(Long boardId) {
//    return em.createQuery("SELECT new jpa.imform.dto.BoardSimpleDto(b.member.id ,b.content, b.title, m.name) " +
//        " FROM Board b" +
//        " JOIN b.member m " +
//        " where b.member.id = :boardId", BoardSimpleDto.class)
//        .setParameter("boardId", boardId)
//        .getResultList();
//  }
//
//  private List<MemberSimpleDto> findSimpleMember() {
//    return em.createQuery( " SELECT new jpa.imform.dto.MemberSimpleDto(m.name, m.email) " +
//        " FROM Member m", MemberSimpleDto.class)
//        .getResultList();
//  }

}
