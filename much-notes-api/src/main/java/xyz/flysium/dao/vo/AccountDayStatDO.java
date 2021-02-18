package xyz.flysium.dao.vo;

import java.util.Date;

public class AccountDayStatDO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Long sumSpend;
  private Long sumIncome;
  private Date time;

  public Long getSumSpend() {
    return sumSpend;
  }

  public void setSumSpend(Long sumSpend) {
    this.sumSpend = sumSpend;
  }

  public Long getSumIncome() {
    return sumIncome;
  }

  public void setSumIncome(Long sumIncome) {
    this.sumIncome = sumIncome;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }
}
