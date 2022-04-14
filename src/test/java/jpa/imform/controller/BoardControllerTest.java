package jpa.imform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jpa.imform.domain.Board;
import jpa.imform.service.BoardService;
import jpa.imform.service.impl.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
@DisplayName("BoardController")
class BoardControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BoardService boardService;

  @MockBean
  private AuthenticationService authenticationService;

  @Autowired
  private ObjectMapper objectMapper;

  private Long memberId;


  @BeforeEach
  void setUp() throws Exception {
    Board board = Board.builder()
        .title("Rutaceae")
        .content("Dasyurus viverrinus")
        .build();

//    this.memberId = objectMapper.readValue(,Long.class);

  }

  @Test
  void list() throws Exception {
    mockMvc.perform(get("member/{memberId}/board", memberId)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());
  }

}
