package xyz.flysium.controller;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import xyz.flysium.BaseMvcTest;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.web.controller.IndexController;

/**
 * Test for {@link IndexController}
 *
 * @author zeno
 */
@SuppressWarnings("unchecked")
class IndexControllerTest extends BaseMvcTest {

  @Test
  void index() throws Exception {
    String content = this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andReturn().getResponse().getContentAsString();
    ResultResponse<Map<String, Object>> response = objectMapper
      .readValue(content, ResultResponse.class);
    Assertions.assertTrue(response.isSuccess());
  }

}
