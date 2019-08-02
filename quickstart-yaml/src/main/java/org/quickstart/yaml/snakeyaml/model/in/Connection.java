package org.quickstart.yaml.snakeyaml.model.in;

import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
public class Connection {
    private Integer maxConnections;
    private Integer maxRequestsPerConnection;
    private Integer idleTimeout;
    private Integer connCloseDelay;
    private Integer maxRequestsPerConnectionInBrownout;
}
