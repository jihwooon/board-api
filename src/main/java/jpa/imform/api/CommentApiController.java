package jpa.imform.api;
import jpa.imform.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comment/api")
@RestController
@RequiredArgsConstructor
public class CommentApiController {

  public final CommentRepository commentRepository;


}

