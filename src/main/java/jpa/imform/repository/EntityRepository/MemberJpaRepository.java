package jpa.imform.repository.EntityRepository;

import jpa.imform.domain.Member;
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
}
