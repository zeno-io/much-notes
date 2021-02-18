package xyz.flysium;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test Configuration, which mock jdbc, redis, etc.
 *
 * @author zeno
 */
@SpringBootTest(classes = MuchNotesApplication.class)
@Transactional
@Rollback(value = true)
public abstract class BaseTest {

  protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

  protected ObjectMapper objectMapper;

  @BeforeEach
  void setUpObjectMapper() throws InterruptedException {
    objectMapper = new ObjectMapper();
  }

}
