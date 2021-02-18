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
import xyz.flysium.dto.UserInfo;
import xyz.flysium.support.cache.EhcacheSupport;

/**
 * Token
 *
 * @author zeno
 */
@Component
public class TokenService {

  private static final Cache<String, UserInfo> TOKEN_TO_USER_INFO = EhcacheSupport.getCacheManager()
    .createCache("tokenToUserInfo", CacheConfigurationBuilder
      .newCacheConfigurationBuilder(String.class, UserInfo.class,
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

  private static final Cache<Long, String> UID_TO_TOKEN = EhcacheSupport.getCacheManager()
    .createCache("uidToToken", CacheConfigurationBuilder
      .newCacheConfigurationBuilder(Long.class, String.class,
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

  public UserInfo getUserInfo(String token) {
    return TOKEN_TO_USER_INFO.get(token);
  }

  public String update(Long uid, String unionId, UserInfo userInfo) {
    String token = generate(unionId);
    TOKEN_TO_USER_INFO.put(token, userInfo);
    //设置之前先清除掉之前的token
    String originToken = UID_TO_TOKEN.get(uid);
    if (originToken != null) {
      TOKEN_TO_USER_INFO.remove(originToken);
    }
    UID_TO_TOKEN.put(uid, token);
    return token;
  }

  private String generate(String unionId) {
    return DigestUtils
      .md5Hex(UUID.randomUUID() + unionId + Instant.now().toEpochMilli());
  }

}
