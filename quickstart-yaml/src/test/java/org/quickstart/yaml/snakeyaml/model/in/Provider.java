package org.quickstart.yaml.snakeyaml.model.in;

import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
public class Provider {
    private String code;
    private String protocol;
    private String data;
    private String host;
    private String port;
}
