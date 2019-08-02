package org.quickstart.yaml.snakeyaml.model.out;

import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
public class Tcp {
    private Boolean tcpKeepAlive;
    private Boolean tcpNoDelay;
    private Integer writeBufferHighWaterMark;
    private Integer writeBufferLowWaterMark;
    private Boolean autoRead;
}
