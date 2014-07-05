package com.sematext.solr.redis.command;

import org.apache.solr.common.params.SolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisCommands;
import java.util.Map;

public final class ZRevrangeByScore implements Command<JedisCommands> {
  private static final Logger log = LoggerFactory.getLogger(ZRevrangeByScore.class);

  @Override
  public Map<String, Float> execute(final JedisCommands client, final String key, final SolrParams params) {
    final String min = ParamUtil.getStringByName(params, "min", "-inf");
    final String max = ParamUtil.getStringByName(params, "max", "+inf");

    log.debug("Fetching ZREVRANGEBYSCORE from Redis for key: {} ({}, {})", key, min, max);

    return ResultUtil.tupleIteratorToMap(client.zrevrangeByScoreWithScores(key, max, min));
  }
}
