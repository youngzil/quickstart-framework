package org.quickstart.json.jackson.v1.pojo;

public class Name {
    private String _first, _last;

    public String getFirst() {
        return _first;
    }

    public String getLast() {
        return _last;
    }

    public void setFirst(String s) {
        _first = s;
    }

    public void setLast(String s) {
        _last = s;
    }

    public String toString() {
        return _first + " " + _last;
    }
}
