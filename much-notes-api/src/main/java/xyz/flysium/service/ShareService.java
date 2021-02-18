package xyz.flysium.service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.stereotype.Component;
import xyz.flysium.constant.MuchNotesConstants;
import xyz.flysium.support.cache.EhcacheSupport;

/**
 * 分享
 *
 * @author zeno
 */
@Component
public class ShareService {

  private static final Cache<String, Long> KEY_TO_ACCOUNT_BOOK_ID = EhcacheSupport.getCacheManager()
    .createCache("keyToAccountBookId", CacheConfigurationBuilder
      .newCacheConfigurationBuilder(String.class, Long.class,
        ResourcePoolsBuilder.newResourcePoolsBuilder()
          .heap(1000L, EntryUnit.ENTRIES)
          .offheap(10L, MemoryUnit.MB)
          .disk(100L, MemoryUnit.MB, true)
      )
      .withSizeOfMaxObjectGraph(1000L)
      .withSizeOfMaxObjectSize(100L, MemoryUnit.KB)
      // 失效时间
      .withExpiry(ExpiryPolicyBuilder
        .timeToLiveExpiration(
          Duration.of(MuchNotesConstants.WX_TOKEN_TIME_OUT, ChronoUnit.SECONDS)))
      .build());

  public Long getAccountBookId(String key) {
    return KEY_TO_ACCOUNT_BOOK_ID.get(key);
  }

  public String update(Long accountBookId) {
    String key = generate(accountBookId);
    KEY_TO_ACCOUNT_BOOK_ID.put(key, accountBookId);
    return key;
  }

  private String generate(Long accountBookId) {
    return DigestUtils
      .md5Hex(UUID.randomUUID() + Long.toString(accountBookId) + Instant.now().toEpochMilli());
  }

}
