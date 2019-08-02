package org.quickstart.yaml.snakeyaml.model;

import java.util.List;

/**
 */
public class FilterSetting {
    private List<String> scanPackages;
    private ScanRedis scanRedis;

    public List<String> getScanPackages() {
        return scanPackages;
    }

    public void setScanPackages(List<String> scanPackages) {
        this.scanPackages = scanPackages;
    }

    public ScanRedis getScanRedis() {
        return scanRedis;
    }

    public void setScanRedis(ScanRedis scanRedis) {
        this.scanRedis = scanRedis;
    }
}
