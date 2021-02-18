package xyz.flysium.dao.vo;

public class AccountMonthStatDO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Long sumSpend;
  private Long sumIncome;

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

}
