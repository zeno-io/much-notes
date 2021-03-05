package xyz.flysium.service;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.flysium.constant.enums.IsOrNot;
import xyz.flysium.dao.entity.CategoryDO;
import xyz.flysium.dao.entity.CategoryDOExample;
import xyz.flysium.dao.repository.CategoryDOMapper;
import xyz.flysium.support.mybatis.QuerySupport;

/**
 * 事项分类管理
 *
 * @author zeno
 */
@Service
public class CategoryService {

  @Autowired
  private CategoryDOMapper categoryMapper;

  public CategoryDO getCategoryById(Long categoryId) {
    if (categoryId == null) {
      return null;
    }
    CategoryDOExample example = new CategoryDOExample();
    example.createCriteria()
      .andIdEqualTo(categoryId)
      .andIsCustomEqualTo(IsOrNot.False.getKeyByte())
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    List<CategoryDO> list = categoryMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(list)) {
      return null;
    }
    return list.get(0);
  }

  public List<CategoryDO> getCategoryList() {
    CategoryDOExample example = new CategoryDOExample();
    example.createCriteria()
      .andIsCustomEqualTo(IsOrNot.False.getKeyByte())
      .andIsDeletedEqualTo(IsOrNot.False.getKeyByte());
    return QuerySupport.queryAll((rowBounds -> {
      return categoryMapper.selectByExampleWithRowbounds(example, rowBounds);
    }));
  }

}
