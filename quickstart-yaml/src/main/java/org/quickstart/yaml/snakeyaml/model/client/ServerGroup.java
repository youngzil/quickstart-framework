package org.quickstart.yaml.snakeyaml.model.client;

import java.util.List;

/**
 */
public class ServerGroup {
     private String code;
     private Boolean isCluster;
     private String requirePass;
     private List<String> addresses;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsCluster() {
        return isCluster;
    }

    public void setIsCluster(Boolean isCluster) {
        this.isCluster = isCluster;
    }

    public String getRequirePass() {
        return requirePass;
    }

    public void setRequirePass(String requirePass) {
        this.requirePass = requirePass;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
