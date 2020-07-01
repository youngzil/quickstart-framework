package org.quickstart.yaml.snakeyaml.model.in;

import lombok.Getter;
import lombok.Setter;

/**
 */

@Getter
@Setter
public class Http {
    private Integer httpRequestReadTimeout;

    private Integer maxInitialLineLength;
    private Integer maxHeaderSize;
    private Integer maxChunkSize;
    private Integer maxExpiryDelta;
}
