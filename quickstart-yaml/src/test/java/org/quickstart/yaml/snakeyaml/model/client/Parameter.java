package org.quickstart.yaml.snakeyaml.model.client;

/**
 */
public class Parameter {
    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
    private Boolean testOnBorrow;
    private Boolean needWriteSyn;
    private Integer connectionTimeout;

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getNeedWriteSyn() {
        return needWriteSyn;
    }

    public void setNeedWriteSyn(Boolean needWriteSyn) {
        this.needWriteSyn = needWriteSyn;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}
