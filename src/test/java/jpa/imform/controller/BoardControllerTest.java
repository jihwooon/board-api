package jpa.imform.controller;

import jpa.imform.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BoardControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private BoardController boardController;

  @MockBean
  private BoardService boardService;

  @Test
  void list() throws Exception {
    mockMvc.perform(get("/boards")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(boardService).getBoards();
  }

  @Test
  void detail() throws Exception {
    mockMvc.perform(get("/boards/1")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());

    verify(boardService).getBoard(1L);
  }

}
