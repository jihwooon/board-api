package jpa.imform.controller;

import jpa.imform.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BoardController.class)
@DisplayName("BoardController")
class BoardControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BoardService boardService;

}

