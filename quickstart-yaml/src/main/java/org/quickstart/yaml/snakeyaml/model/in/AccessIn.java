package org.quickstart.yaml.snakeyaml.model.in;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 */
@Getter@Setter
public class AccessIn {
  List<Provider> providers;
  DataProtocol dataProtocol;
  DataCrypto dataCrypto;
  ServerTunning serverTunning;

  DataRestful dataRestful;

}
