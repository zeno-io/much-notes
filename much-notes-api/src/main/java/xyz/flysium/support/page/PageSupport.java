package xyz.flysium.support.page;

import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.apache.ibatis.session.RowBounds;

/**
 * Query Support for {@link PageInfo}
 *
 * @author zeno
 */
public final class PageSupport {

  private PageSupport() {
  }

  /**
   * 转换为 {@link RowBounds}
   *
   * @param pageNumber 当前第几页
   * @param pageSize   每页记录数
   * @return RowBounds
   */
  public static RowBounds toRowBounds(Integer pageNumber, Integer pageSize) {
    int num = pageNumber == null ? 1 : pageNumber;
    int size = pageSize == null ? 20 : pageSize;
    return new RowBounds((num - 1) * size, size);
  }

  /**
   * 转换
   *
   * @param page       源数据
   * @param pageNumber 当前第几页
   * @param pageSize   每页记录数
   * @param total      总记录数
   * @param <T>        对象类型
   * @return 结果
   */
  public static <T> PageInfo<T> transform(List<T> page, int pageNumber, int pageSize, long total) {
    Objects.requireNonNull(page);
    PageInfo<T> res = new PageInfo<>();
    res.setList(page);
    res.setPageSize(pageSize);
    res.setPageNum(pageNumber);
    res.setTotal(total);
    res.calcByNavigatePages(pageNumber);
    return res;
  }

  /**
   * 转换
   *
   * @param page    源数据
   * @param mapping Function
   * @param <S>     源对象类型
   * @param <T>     目标对象类型
   * @return 结果
   */
  public static <S, T> PageInfo<T> transform(PageInfo<S> page, Function<List<S>, List<T>> mapping) {
    Objects.requireNonNull(page);
    Objects.requireNonNull(mapping);

    PageInfo<T> res = new PageInfo<>();
    res.setList(mapping.apply(page.getList()));
    res.setPageSize(page.getPageSize());
    res.setPageNum(page.getPageNum());
    res.setPages(page.getPages());
    res.setStartRow(page.getStartRow());
    res.setEndRow(page.getEndRow());
    res.setTotal(page.getTotal());
    res.calcByNavigatePages(page.getNavigatePages());
    return res;
  }

  /**
   * 转换
   *
   * @param page    源数据
   * @param mapping Function
   * @param <S>     源对象类型
   * @param <T>     目标对象类型
   * @return 结果
   */
  public static <S, T> PageInfo<T> transformTo(PageInfo<S> page, Function<S, T> mapping) {
    return transform(page, (Function<List<S>, List<T>>) (sl) -> {
      List<T> tl = new ArrayList<>(sl.size());
      sl.forEach(s -> {
          T t = mapping.apply(s);
          tl.add(t);
        }
      );
      return tl;
    });
  }

}
