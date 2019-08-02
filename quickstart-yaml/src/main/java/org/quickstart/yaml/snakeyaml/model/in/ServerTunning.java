package org.quickstart.yaml.snakeyaml.model.in;

import lombok.Getter;
import lombok.Setter;

/**
 */

@Getter
@Setter
public class ServerTunning {
    private Tcp tcp;
    private Connection connection;
    private Http http;
    private Thread thread;
}
