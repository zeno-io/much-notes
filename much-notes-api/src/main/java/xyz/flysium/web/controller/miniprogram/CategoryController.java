package xyz.flysium.web.controller.miniprogram;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.flysium.dao.entity.CategoryDO;
import xyz.flysium.dto.CategoryDTO;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.service.CategoryService;

/**
 * 事项分类
 *
 * @author zeno
 */
@RestController
@RequestMapping("/mp/category")
@Api(value = "CategoryApi", tags = {"分类"})
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  private final Mapper dozerBeanMapper = DozerBeanMapperBuilder.buildDefault();

  @PostMapping("/getList")
  @ApiOperation("记账中的事项分类")
  public ResultResponse<Map<String, List<CategoryDTO>>> getList() {
    List<CategoryDO> categoryList = categoryService.getCategoryList();

    Map<String, List<CategoryDTO>> res = new HashMap<>(8);
    res.put("zcCategory",
      categoryList.stream().filter(categoryDO -> categoryDO.getType() == 0)
        .map(categoryDO -> dozerBeanMapper.map(categoryDO, CategoryDTO.class))
        .collect(Collectors.toList()));
    res.put("srCategory",
      categoryList.stream().filter(categoryDO -> categoryDO.getType() == 1)
        .map(categoryDO -> dozerBeanMapper.map(categoryDO, CategoryDTO.class))
        .collect(Collectors.toList()));
    return ResultResponse.success(res);
  }

  @PostMapping("/getUnTypeList")
  @ApiOperation("记账中的事项分类(不区分类型的)")
  public ResultResponse<List<CategoryDTO>> getUnTypeList() {
    List<CategoryDO> categoryList = categoryService.getCategoryList();
    return ResultResponse.success(categoryList.stream()
      .map(categoryDO -> dozerBeanMapper.map(categoryDO, CategoryDTO.class))
      .collect(Collectors.toList()));
  }

}
