package org.quickstart.yaml.snakeyaml.model.out;

import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
public class Connection {
    private Integer maxRequestsPerConnection;
    private Integer perServerWaterline;
    private Integer maxConnectionPerHost;
}
