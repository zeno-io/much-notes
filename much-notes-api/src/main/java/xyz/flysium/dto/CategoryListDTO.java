package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel("分类信息")
public class CategoryListDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private List<CategoryDTO> zcCategory;

  @ApiModelProperty
  private List<CategoryDTO> srCategory;

  public List<CategoryDTO> getZcCategory() {
    return zcCategory;
  }

  public void setZcCategory(List<CategoryDTO> zcCategory) {
    this.zcCategory = zcCategory;
  }

  public List<CategoryDTO> getSrCategory() {
    return srCategory;
  }

  public void setSrCategory(List<CategoryDTO> srCategory) {
    this.srCategory = srCategory;
  }
}
