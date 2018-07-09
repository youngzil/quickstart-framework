package org.quickstart.json.jackson.v1.annotation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;

public class DateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext arg1) throws IOException, JsonProcessingException {
        String str = parser.getText();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {

        }

        return date;
    }

}
