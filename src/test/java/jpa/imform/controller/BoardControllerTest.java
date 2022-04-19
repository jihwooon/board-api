package jpa.imform.controller;

import jpa.imform.service.MemberBoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("BoardController")
@WebMvcTest(BoardController.class)
class BoardControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MemberBoardService memberBoardService;

  @BeforeEach
  void setUp() {

  }

}
