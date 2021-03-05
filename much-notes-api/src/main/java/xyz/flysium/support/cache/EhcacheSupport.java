package xyz.flysium.support.cache;

import java.io.File;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import xyz.flysium.constant.MuchNotesConstants;

/**
 * Support for {@link CacheManager}
 *
 * @author zeno
 */
public final class EhcacheSupport {

  private EhcacheSupport() {
  }

  private static final File DIRECTORY = new File(MuchNotesConstants.DIRECTORY_VAR_CACHE);

  static {
    if (!DIRECTORY.exists()) {
      DIRECTORY.mkdirs();
    }
  }

  // 初始化管理器
  private static final CacheManager CACHE_MANAGER = CacheManagerBuilder.newCacheManagerBuilder()
    .with(CacheManagerBuilder.persistence(DIRECTORY))
    .build(true);

  public static CacheManager getCacheManager() {
    return CACHE_MANAGER;
  }

}
