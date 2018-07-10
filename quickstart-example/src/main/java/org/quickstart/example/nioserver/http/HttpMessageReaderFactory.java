package org.quickstart.example.nioserver.http;

import org.quickstart.example.nioserver.IMessageReader;
import org.quickstart.example.nioserver.IMessageReaderFactory;

/**
 * Created by jjenkov on 18-10-2015.
 */
public class HttpMessageReaderFactory implements IMessageReaderFactory {

    public HttpMessageReaderFactory() {}

    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
