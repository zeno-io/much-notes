package xyz.flysium.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("分类信息")
public class CategoryDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty
  private Long id;

  @ApiModelProperty
  private String name;

  @ApiModelProperty
  private String icon;

  @ApiModelProperty
  private Byte isCustom;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Byte getIsCustom() {
    return isCustom;
  }

  public void setIsCustom(Byte isCustom) {
    this.isCustom = isCustom;
  }

}
