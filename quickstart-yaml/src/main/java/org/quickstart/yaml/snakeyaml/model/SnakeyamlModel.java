package org.quickstart.yaml.snakeyaml.model;


import org.quickstart.yaml.snakeyaml.model.cache.CacheConfig;
import org.quickstart.yaml.snakeyaml.model.client.RedisConfig;
import org.quickstart.yaml.snakeyaml.model.in.AccessIn;
import org.quickstart.yaml.snakeyaml.model.out.AccessOut;

/**
 */
public class SnakeyamlModel {
    private AccessIn accessIn;
    private AccessOut accessOut;
    private Boolean stateContentEnabled;
    private RedisConfig redisConfig;
    private FilterSetting filterSetting;
    private CacheConfig cacheConfig;
    private RSAInit rsaInit;

    public Boolean getStateContentEnabled() {
        return stateContentEnabled;
    }

    public void setStateContentEnabled(Boolean stateContentEnabled) {
        this.stateContentEnabled = stateContentEnabled;
    }

    public AccessIn getAccessIn() {
        return accessIn;
    }

    public void setAccessIn(AccessIn accessIn) {
        this.accessIn = accessIn;
    }

    public AccessOut getAccessOut() {
        return accessOut;
    }

    public void setAccessOut(AccessOut accessOut) {
        this.accessOut = accessOut;
    }

    public FilterSetting getFilterSetting() {
        return filterSetting;
    }

    public void setFilterSetting(FilterSetting filterSetting) {
        this.filterSetting = filterSetting;
    }

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

	public CacheConfig getCacheConfig() {
		return cacheConfig;
	}


	public void setCacheConfig(CacheConfig cacheConfig) {
		this.cacheConfig = cacheConfig;
	}

    public RSAInit getRsaInit() {
        return rsaInit;
    }

    public void setRsaInit(RSAInit rsaInit) {
        this.rsaInit = rsaInit;
    }
}
