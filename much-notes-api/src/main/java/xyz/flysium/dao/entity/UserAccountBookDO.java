package xyz.flysium.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class UserAccountBookDO implements Serializable {

  private Long id;

  private Long uid;

  private String name;

  private Byte type;

  private Long creator;

  private Long updater;

  private Date createTime;

  private Date updateTime;

  private String remark;

  private static final long serialVersionUID = 1L;

  public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public Long getCreator() {
    return creator;
  }

  public void setCreator(Long creator) {
    this.creator = creator;
  }

  public Long getUpdater() {
    return updater;
  }

  public void setUpdater(Long updater) {
    this.updater = updater;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(getClass().getSimpleName());
      sb.append(" [");
      sb.append("Hash = ").append(hashCode());
      sb.append(", id=").append(id);
      sb.append(", uid=").append(uid);
      sb.append(", name=").append(name);
      sb.append(", type=").append(type);
      sb.append(", creator=").append(creator);
      sb.append(", updater=").append(updater);
      sb.append(", createTime=").append(createTime);
      sb.append(", updateTime=").append(updateTime);
      sb.append(", remark=").append(remark);
      sb.append(", serialVersionUID=").append(serialVersionUID);
      sb.append("]");
      return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserAccountBookDO other = (UserAccountBookDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
          && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
          && (this.getName() == null ? other.getName() == null
          : this.getName().equals(other.getName()))
          && (this.getType() == null ? other.getType() == null
          : this.getType().equals(other.getType()))
          && (this.getCreator() == null ? other.getCreator() == null
          : this.getCreator().equals(other.getCreator()))
          && (this.getUpdater() == null ? other.getUpdater() == null
          : this.getUpdater().equals(other.getUpdater()))
          && (this.getCreateTime() == null ? other.getCreateTime() == null
          : this.getCreateTime().equals(other.getCreateTime()))
          && (this.getUpdateTime() == null ? other.getUpdateTime() == null
          : this.getUpdateTime().equals(other.getUpdateTime()))
          && (this.getRemark() == null ? other.getRemark() == null
          : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
      result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
      result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
      result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
      result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
      result = prime * result + ((getUpdater() == null) ? 0 : getUpdater().hashCode());
      result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
      result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
      result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
      return result;
    }
}
