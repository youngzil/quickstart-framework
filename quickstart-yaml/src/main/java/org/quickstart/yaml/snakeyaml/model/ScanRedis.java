package org.quickstart.yaml.snakeyaml.model;

/**
 */
public class ScanRedis {
    private String keyPrefix;
    private Integer  pollingInterval;

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public Integer getPollingInterval() {
        return pollingInterval;
    }

    public void setPollingInterval(Integer pollingInterval) {
        this.pollingInterval = pollingInterval;
    }
}
