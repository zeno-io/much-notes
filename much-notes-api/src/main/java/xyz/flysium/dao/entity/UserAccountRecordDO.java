package xyz.flysium.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class UserAccountRecordDO implements Serializable {

  private Long id;

  private Long money;

  private Byte type;

  private Integer accountType;

  private Long accountBookId;

  private Long uid;

  private Date time;

  private Integer timeYear;

  private Integer timeMonth;

  private Integer timeDay;

  private String categoryName;

  private Long categoryId;

  private Long creator;

  private Long updater;

  private Date createTime;

  private Date updateTime;

  private String remark;

  private Byte isDeleted;

  private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

  public Long getAccountBookId() {
    return accountBookId;
  }

  public void setAccountBookId(Long accountBookId) {
    this.accountBookId = accountBookId;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Integer getTimeYear() {
        return timeYear;
    }

    public void setTimeYear(Integer timeYear) {
        this.timeYear = timeYear;
    }

    public Integer getTimeMonth() {
        return timeMonth;
    }

    public void setTimeMonth(Integer timeMonth) {
        this.timeMonth = timeMonth;
    }

    public Integer getTimeDay() {
        return timeDay;
    }

    public void setTimeDay(Integer timeDay) {
        this.timeDay = timeDay;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
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

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", money=").append(money);
      sb.append(", type=").append(type);
      sb.append(", accountType=").append(accountType);
      sb.append(", accountBookId=").append(accountBookId);
      sb.append(", uid=").append(uid);
      sb.append(", time=").append(time);
      sb.append(", timeYear=").append(timeYear);
      sb.append(", timeMonth=").append(timeMonth);
      sb.append(", timeDay=").append(timeDay);
      sb.append(", categoryName=").append(categoryName);
      sb.append(", categoryId=").append(categoryId);
      sb.append(", creator=").append(creator);
      sb.append(", updater=").append(updater);
      sb.append(", createTime=").append(createTime);
      sb.append(", updateTime=").append(updateTime);
      sb.append(", remark=").append(remark);
      sb.append(", isDeleted=").append(isDeleted);
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
        UserAccountRecordDO other = (UserAccountRecordDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
          && (this.getMoney() == null ? other.getMoney() == null
          : this.getMoney().equals(other.getMoney()))
          && (this.getType() == null ? other.getType() == null
          : this.getType().equals(other.getType()))
          && (this.getAccountType() == null ? other.getAccountType() == null
          : this.getAccountType().equals(other.getAccountType()))
          && (this.getAccountBookId() == null ? other.getAccountBookId() == null
          : this.getAccountBookId().equals(other.getAccountBookId()))
          && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
          && (this.getTime() == null ? other.getTime() == null
          : this.getTime().equals(other.getTime()))
          && (this.getTimeYear() == null ? other.getTimeYear() == null
          : this.getTimeYear().equals(other.getTimeYear()))
          && (this.getTimeMonth() == null ? other.getTimeMonth() == null
          : this.getTimeMonth().equals(other.getTimeMonth()))
          && (this.getTimeDay() == null ? other.getTimeDay() == null
          : this.getTimeDay().equals(other.getTimeDay()))
          && (this.getCategoryName() == null ? other.getCategoryName() == null
          : this.getCategoryName().equals(other.getCategoryName()))
          && (this.getCategoryId() == null ? other.getCategoryId() == null
          : this.getCategoryId().equals(other.getCategoryId()))
          && (this.getCreator() == null ? other.getCreator() == null
          : this.getCreator().equals(other.getCreator()))
          && (this.getUpdater() == null ? other.getUpdater() == null
          : this.getUpdater().equals(other.getUpdater()))
          && (this.getCreateTime() == null ? other.getCreateTime() == null
          : this.getCreateTime().equals(other.getCreateTime()))
          && (this.getUpdateTime() == null ? other.getUpdateTime() == null
          : this.getUpdateTime().equals(other.getUpdateTime()))
          && (this.getRemark() == null ? other.getRemark() == null
          : this.getRemark().equals(other.getRemark()))
          && (this.getIsDeleted() == null ? other.getIsDeleted() == null
          : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
      result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
      result = prime * result + ((getAccountType() == null) ? 0 : getAccountType().hashCode());
      result = prime * result + ((getAccountBookId() == null) ? 0 : getAccountBookId().hashCode());
      result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
      result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
      result = prime * result + ((getTimeYear() == null) ? 0 : getTimeYear().hashCode());
      result = prime * result + ((getTimeMonth() == null) ? 0 : getTimeMonth().hashCode());
      result = prime * result + ((getTimeDay() == null) ? 0 : getTimeDay().hashCode());
      result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
      result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
      result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
      result = prime * result + ((getUpdater() == null) ? 0 : getUpdater().hashCode());
      result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
      result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
      result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
      result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
      return result;
    }
}
