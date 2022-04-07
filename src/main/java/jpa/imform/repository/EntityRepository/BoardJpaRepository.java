package jpa.imform.repository.EntityRepository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardJpaRepository {

  @PersistenceContext
  private EntityManager em;

  public List<Board> findAllByMember(final Member member) {
    return em.createQuery(
        "select b from Board b where b.member = :member", Board.class)
        .setParameter("member", member)
        .getResultList();
  }

  public Board fineOne(final Long id) {
    return em.find(Board.class, id);
  }

}
