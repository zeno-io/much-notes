package xyz.flysium.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

/**
 * 线程池分配器
 *
 * @author zeno
 */
@Component
public class ThreadPoolExecutorAllocator {

  // 必须是2的次幂
  private static final int CACHE_CAPACITY = 256;

  // 阻塞队列的长度
  private static final int QUEUE_CAPACITY = 256;

  // 公共线程池阻塞队列的长度
  private static final int COMMON_QUEUE_CAPACITY = 512;

  // 存储应用ID对应的线程池
  private static final Map<Long, GroupThreadPoolExecutor> CACHED = new ConcurrentHashMap<>(
    CACHE_CAPACITY);

  // 锁粒度细化
  private static final Object[] LOCKS = new Object[CACHE_CAPACITY];

  private static final Map<Long, Integer> ID_TO_INDEX = new ConcurrentHashMap<>(CACHE_CAPACITY);

  private static final AtomicInteger ATOMIC_INDEX = new AtomicInteger(1);

  private static final Long COMMON_APP_ID = -1L;

  private static final GroupThreadPoolExecutor COMMON_THREAD_POOL_EXECUTOR = new GroupThreadPoolExecutor(
    COMMON_APP_ID, 8, 8, 60, TimeUnit.SECONDS,
    COMMON_QUEUE_CAPACITY <= 0 ? new LinkedBlockingDeque<>()
      : new ArrayBlockingQueue<>(COMMON_QUEUE_CAPACITY),
    new CustomizableThreadFactory("group-executor-common-"), new CallerRunsPolicy());

  public ThreadPoolExecutorAllocator() {
    CACHED.putIfAbsent(COMMON_APP_ID, COMMON_THREAD_POOL_EXECUTOR);
    for (int i = 0; i < LOCKS.length; i++) {
      LOCKS[i] = new Object();
    }
  }

  public static ThreadPoolExecutor getExecutorFor(Long key) {
    return SpringContextUtils.getBean(ThreadPoolExecutorAllocator.class).getExecutor(key);
  }

  /**
   * 执行所有函数，不考虑返回计算结果
   *
   * @param groupId   分组ID
   * @param suppliers 执行函数
   * @param <R>       返回结果类型
   * @return 返回 CompletableFuture
   */
  @SafeVarargs
  public static <R> List<R> allOf(Long groupId, Supplier<R>... suppliers) {
    assert suppliers.length > 0;
    final ThreadPoolExecutor threadPoolExecutor = getExecutorFor(groupId);
    CompletableFuture<?>[] completableFutures = new CompletableFuture<?>[suppliers.length];
    BlockingQueue<R> res = new ArrayBlockingQueue<>(suppliers.length);
    for (int i = 0; i < suppliers.length; i++) {
      completableFutures[i] = CompletableFuture.supplyAsync(suppliers[i], threadPoolExecutor)
        .whenComplete((v, e) -> {
          try {
            if (e == null) {
              res.put(v);
            }
          } catch (InterruptedException e1) {
            e1.printStackTrace();
          }
        });
    }
    CompletableFuture.allOf(completableFutures).join();
    List<R> list = new ArrayList<>(suppliers.length);
    while (!res.isEmpty()) {
      list.add(res.poll());
    }
    return list;
  }

  /**
   * 获取分组ID对应的线程池，如果线程池分配已满，则使用公共线程池
   *
   * @param groupId 分组ID
   * @return 分配的线程池
   */
  public ThreadPoolExecutor getExecutor(Long groupId) {
    final int index = indexFor(groupId);
    GroupThreadPoolExecutor threadPoolExecutor = CACHED.get(groupId);
    if (threadPoolExecutor == null) {
      synchronized (LOCKS[index]) {
        threadPoolExecutor = CACHED.get(groupId);
        if (threadPoolExecutor == null) {
          threadPoolExecutor = new GroupThreadPoolExecutor(groupId, 2, 4, 60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
            new CustomizableThreadFactory("group-executor-" + groupId + "-"),
            new CallerRunsPolicy());
        }
      }
    }
    if (!Objects.equals(threadPoolExecutor.getGroupId(), groupId)) {
      return COMMON_THREAD_POOL_EXECUTOR;
    }
    return threadPoolExecutor;
  }

  protected int indexFor(Long groupId) {
    Integer index = ID_TO_INDEX.get(groupId);
    if (index != null && index >= CACHE_CAPACITY) {
      index = indexOf(index, CACHE_CAPACITY);
      return index;
    }
    if (index == null) {
      index = ATOMIC_INDEX.getAndIncrement();
      ID_TO_INDEX.putIfAbsent(groupId, index);
    }
    if (index >= CACHE_CAPACITY) {
      index = indexOf(index, CACHE_CAPACITY);
    }
    return index;
  }

  // 要求 length 长度为2的次幂
  protected final int indexOf(int index, int length) {
    return index & (length - 1);
  }

  public static class GroupThreadPoolExecutor extends ThreadPoolExecutor {

    private final Long groupId;

    private static final Logger LOGGER = LoggerFactory
      .getLogger(GroupThreadPoolExecutor.class);

    public GroupThreadPoolExecutor(Long groupId, int corePoolSize, int maximumPoolSize,
      long keepAliveTime,
      TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
      RejectedExecutionHandler handler) {
      super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
      this.groupId = groupId;
    }

    public Long getGroupId() {
      return groupId;
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
      if (t != null) {
        if (groupId != null) {
          LOGGER.error("Execute Group: {}, Exception :", groupId, t);
        } else {
          LOGGER.error("Execute Exception :", t);
        }
      }
    }
  }

}
