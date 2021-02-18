package xyz.flysium.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.flysium.config.ApplicationProperties;
import xyz.flysium.dto.ResultResponse;

/**
 * Index.
 *
 * @author zeno
 */
@RestController
@RequestMapping("/")
@Api(value = "Index", tags = {"首页"})
public class IndexController {

  @Autowired
  private ApplicationProperties applicationProperties;

  @GetMapping("/")
  @ApiOperation("首页")
  public ResultResponse<Map<String, String>> index() {
    Map<String, String> res = new HashMap<>(16);
    res.put("Product", applicationProperties.getProduct());
    res.put("Version", applicationProperties.getVersion());
    res.put("Company", applicationProperties.getCompany());
    res.put("ToYou", applicationProperties.getToYou());
    return ResultResponse.success(res);
  }

}
