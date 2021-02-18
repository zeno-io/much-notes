package xyz.flysium.support.mybatis;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import org.apache.ibatis.session.RowBounds;

/**
 * Query Support for {@link RowBounds}
 *
 * @author zeno
 */
public final class QuerySupport {

  private QuerySupport() {
  }

  /**
   * 分批查询所有的结果
   *
   * @param pageQueryFunction 分页查询函数
   * @param <T>               数据类型
   * @return 查询的所有结果
   */
  public static <T> List<T> queryAll(Function<RowBounds, List<T>> pageQueryFunction) {
    return queryAll(pageQueryFunction, 500, Integer.MAX_VALUE);
  }

  /**
   * 分批查询所有的结果
   *
   * @param pageQueryFunction 分页查询函数
   * @param sizePreBatch      每次查询的记录数
   * @param <T>               数据类型
   * @return 查询的所有结果
   */
  public static <T> List<T> queryAll(Function<RowBounds, List<T>> pageQueryFunction,
    int sizePreBatch) {
    return queryAll(pageQueryFunction, sizePreBatch, Integer.MAX_VALUE);
  }

  /**
   * 分批查询所有的结果
   *
   * @param pageQueryFunction 分页查询函数
   * @param sizePreBatch      每次查询的记录数
   * @param maxSize           最大记录数
   * @param <T>               数据类型
   * @return 查询的所有结果
   */
  public static <T> List<T> queryAll(Function<RowBounds, List<T>> pageQueryFunction,
    int sizePreBatch, int maxSize) {
    if (sizePreBatch < 100) {
      sizePreBatch = 100;
    }
    if (maxSize < 1) {
      maxSize = 1;
    }
    RowBounds rowBounds = new RowBounds(0, sizePreBatch);
    List<T> res = pageQueryFunction.apply(rowBounds);
    int hashcode = res.isEmpty() ? 0 : res.get(0).hashCode();
    List<T> ans = new LinkedList<>(res);
    while (!res.isEmpty() && ans.size() < maxSize) {
      rowBounds = new RowBounds(newOffset(sizePreBatch, rowBounds), sizePreBatch);
      res = pageQueryFunction.apply(rowBounds);
      // avoid the pageQueryFunction without RowBounds
      if ((res.isEmpty() ? 0 : res.get(0).hashCode()) == hashcode) {
        break;
      }
      ans.addAll(res);
    }
    return ans;
  }

  private static int newOffset(int sizePreBatch, RowBounds rowBounds) {
    return (int) Math.min((long) rowBounds.getOffset() + sizePreBatch, Integer.MAX_VALUE);
  }

}
