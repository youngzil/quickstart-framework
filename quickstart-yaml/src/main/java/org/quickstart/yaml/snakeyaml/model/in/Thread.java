package org.quickstart.yaml.snakeyaml.model.in;

import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter
@Setter
public class Thread {
    private Integer acceptor;
    private Integer worker;
    private boolean chooseThreadOfLeastConnections;
}
