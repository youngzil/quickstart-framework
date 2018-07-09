package org.quickstart.json.jackson.v1.annotation;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @JsonProperty(value = "myId")
    private int id;

    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date gmtCreate;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: " + id + ",");
        sb.append("gmtCreate: " + gmtCreate + " ");
        return sb.toString();
    }
}
