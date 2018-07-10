package com.quickstart.xml.bean;

import java.util.ArrayList;
import java.util.List;

public class SchemaElement {

    private String name;

    private boolean required = true;

    private List<SchemaElement> elements = new ArrayList<SchemaElement>();

    private List<SchemaElement> attributes = new ArrayList<SchemaElement>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public List<SchemaElement> getElements() {
        return elements;
    }

    public void setElements(List<SchemaElement> elements) {
        this.elements = elements;
    }

    public List<SchemaElement> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<SchemaElement> attributes) {
        this.attributes = attributes;
    }

}
