package org.quickstart.yaml.snakeyaml.model.in;

import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
public class Tcp {
    private Integer soBacklog;
    private Integer soLinger;
    private Boolean tcpNodelay;
    private Boolean soKeepalive;
    private Integer tcpDeferAccept;
}
