package com.quickstart.test.jms.springjms;

import javax.jms.Destination;

public interface ConsumerService {

    public void receive(Destination destination) throws Exception;
}
