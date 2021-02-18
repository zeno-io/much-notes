package xyz.flysium;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test Configuration for mvc
 *
 * @author zeno
 */
public abstract class BaseMvcTest extends BaseTest {

  @Autowired
  protected WebApplicationContext wac;

  protected MockMvc mockMvc;

  @BeforeEach
  public void setUpMvc() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

}
