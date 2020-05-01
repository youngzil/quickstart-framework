package org.quickstart.javase.java7.domain;

import java.util.Random;

public class Trade {
    private long id = -1;
    private String status = null;

    public Trade() {
        setId(new Random(100000).nextLong());
        setStatus("NEW");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
