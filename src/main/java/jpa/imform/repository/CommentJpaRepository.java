package jpa.imform.repository;

import jpa.imform.domain.Board;
import jpa.imform.domain.Comment;
import jpa.imform.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentJpaRepository {

  @PersistenceContext
  private EntityManager em;

  public List<Comment> findAllByMemberAndBoard(final Member member,
                                               final Board board) {
    return em.createQuery("select c from Comment c " +
        "where c.member = :member and c.board = :board")
        .setParameter("member", member)
        .setParameter("board", board)
        .getResultList();
  }

  public Comment findone(final Long id) {
    return em.find(Comment.class, id);
  }
}
