package org.quickstart.json.jackson.v1.annotation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class DateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date arg0, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        gen.writeString(format.format(arg0));
    }

}
