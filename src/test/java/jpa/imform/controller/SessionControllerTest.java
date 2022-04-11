package jpa.imform.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@MockBean(JpaMetamodelMappingContext.class)
class SessionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void login() throws Exception {
    mockMvc.perform(post("/session"))
        .andExpect(status().isCreated());
  }

}
