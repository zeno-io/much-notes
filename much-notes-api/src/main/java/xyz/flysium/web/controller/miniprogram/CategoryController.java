package xyz.flysium.web.controller.miniprogram;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.flysium.dao.entity.CategoryDO;
import xyz.flysium.dto.CategoryDTO;
import xyz.flysium.dto.CategoryListDTO;
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
  public ResultResponse<CategoryListDTO> getList() {
    List<CategoryDO> categoryList = categoryService.getCategoryList();

    CategoryListDTO dto = new CategoryListDTO();
    dto.setZcCategory(
      categoryList.stream().filter(category -> category.getType() == 0)
        .map(category -> dozerBeanMapper.map(category, CategoryDTO.class))
        .collect(Collectors.toList()));
    dto.setSrCategory(
      categoryList.stream().filter(category -> category.getType() == 1)
        .map(category -> dozerBeanMapper.map(category, CategoryDTO.class))
        .collect(Collectors.toList()));
    return ResultResponse.success(dto);
  }

  @PostMapping("/getUnTypeList")
  @ApiOperation("记账中的事项分类(不区分类型的)")
  public ResultResponse<List<CategoryDTO>> getUnTypeList() {
    List<CategoryDO> categoryList = categoryService.getCategoryList();
    return ResultResponse.success(categoryList.stream()
      .map(category -> dozerBeanMapper.map(category, CategoryDTO.class))
      .collect(Collectors.toList()));
  }

}
