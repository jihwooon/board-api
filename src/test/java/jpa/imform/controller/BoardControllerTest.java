package jpa.imform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jpa.imform.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private BoardService boardService;

  @BeforeEach
  public void setUp(WebApplicationContext context) {

  }

}

