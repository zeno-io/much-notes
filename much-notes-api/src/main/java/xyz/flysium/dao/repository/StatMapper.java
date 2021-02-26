package xyz.flysium.dao.repository;

import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import xyz.flysium.dao.vo.AccountDayStatDO;
import xyz.flysium.dao.vo.AccountMonthStatDO;
import xyz.flysium.dao.vo.AccountYearStatDO;

@Mapper
public interface StatMapper {

  @Select(value = {
    "<script>",
    "select SUM(IF(type=0,money,0)) as sum_spend,",
    " SUM(IF(type=1,money,0)) as sum_income, ",
    " time ",
    " from user_account_record ",
    " where account_book_id IN ",
    "   <foreach item='item' collection='accountBookIds' separator=',' open='(' close=')' index='' >"
      + "  #{item}"
      + "</foreach>",
    " <if test='days != null and days.size() > 0'>",
    "  AND time IN ",
    "   <foreach item='item' collection='days' separator=',' open='(' close=')' index='' >"
      + "  #{item}"
      + "</foreach>",
    " </if>",
    " GROUP BY time",
    "</script>"
  })
  @Results({
    @Result(column = "sum_spend", property = "sumSpend", jdbcType = JdbcType.INTEGER),
    @Result(column = "sum_income", property = "sumIncome", jdbcType = JdbcType.INTEGER),
    @Result(column = "time", property = "time", jdbcType = JdbcType.DATE)
  })
  List<AccountDayStatDO> selectAccountDayStatByAccountBookIdList(
    @Param("accountBookIds") List<Long> accountBookIds,
    @Param("days") List<LocalDate> days);

  @Select(value = {
    "<script>",
    "select SUM(IF(type=0,money,0)) as sum_spend,",
    " SUM(IF(type=1,money,0)) as sum_income ",
    " from user_account_record ",
    " where account_book_id IN ",
    "   <foreach item='item' collection='accountBookIds' separator=',' open='(' close=')' index='' >"
      + "  #{item}"
      + "</foreach>",
    " AND time_year= #{year,jdbcType=BIGINT}",
    " AND time_month= #{monthOfYear,jdbcType=BIGINT}",
    "</script>"
  })
  @Results({
    @Result(column = "sum_spend", property = "sumSpend", jdbcType = JdbcType.INTEGER),
    @Result(column = "sum_income", property = "sumIncome", jdbcType = JdbcType.INTEGER)
  })
  AccountMonthStatDO selectAccountMonthStatByAccountBookIdList(
    @Param("accountBookIds") List<Long> accountBookIds,
    @Param("year") Integer year, @Param("monthOfYear") Integer monthOfYear);

  @Select(value = {
    "<script>",
    "select ",
    " sum(sum_spend) as sum_spend, ",
    " sum(sum_income) as sum_income, ",
    " time_month as time_month ",
    " from (",
    "  select SUM(money) as sum_spend,"
      + "    0 as sum_income, "
      + "    time_month "
      + "    from user_account_record"
      + "    where account_book_id IN ",
    "   <foreach item='item' collection='accountBookIds' separator=',' open='(' close=')' index='' >"
      + "  #{item}"
      + "</foreach>"
      + "    AND type=0"
      + "    AND time_year= #{year,jdbcType=BIGINT}"
      + "    group by time_month",
    "  union ",
    "  select 0 as sum_spend,"
      + "    SUM(money) as sum_income, "
      + "    time_month "
      + "    from user_account_record"
      + "    where account_book_id IN ",
    "   <foreach item='item' collection='accountBookIds' separator=',' open='(' close=')' index='' >"
      + "  #{item}"
      + "</foreach>"
      + "    AND type=1"
      + "    AND time_year= #{year,jdbcType=BIGINT}"
      + "    group by time_month",
    ") T",
    " GROUP BY time_month ",
    " ORDER BY time_month ASC",
    "</script>"
  })
  @Results({
    @Result(column = "sum_spend", property = "sumSpend", jdbcType = JdbcType.INTEGER),
    @Result(column = "sum_income", property = "sumIncome", jdbcType = JdbcType.INTEGER),
    @Result(column = "time_month", property = "month", jdbcType = JdbcType.INTEGER)
  })
  List<AccountYearStatDO> selectAccountYearStatByAccountBookIdList(
    @Param("accountBookIds") List<Long> accountBookIds,
    @Param("year") Integer year);

}
