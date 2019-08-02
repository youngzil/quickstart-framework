package org.quickstart.yaml.snakeyaml.model.client;

import java.util.List;

/**
 */
public class RedisConfig {
    private Parameter parameter;
    private List<Router> routers;
    private List<ServerGroup> serverGroups;

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public List<Router> getRouters() {
        return routers;
    }

    public void setRouters(List<Router> routers) {
        this.routers = routers;
    }

    public List<ServerGroup> getServerGroups() {
        return serverGroups;
    }

    public void setServerGroups(List<ServerGroup> serverGroups) {
        this.serverGroups = serverGroups;
    }
}
