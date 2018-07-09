package org.quickstart.json.jackson.v2;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.quickstart.json.jackson.v2.entity.Car;
import org.quickstart.json.jackson.v2.entity.User;
import org.quickstart.json.jackson.v2.entity.Car.Owner;

/**
 * Created by GatsbyNewton on 2016/4/15.
 */
public class JsonParseDemo {

    public static void readFromFile() {
        String path = "F:\\Codes\\IDEA\\Tools\\data\\json\\user.json";
        // String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        JsonFactory jsonFactory = new JsonFactory();
        User user = new User();

        try {
            JsonParser parser = jsonFactory.createParser(new File(path));
            parser.nextToken();
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = parser.getCurrentName();
                parser.nextToken();
                if (fieldName.equals("name")) {
                    System.out.println(fieldName + ":");
                    while (parser.nextToken() != JsonToken.END_OBJECT) {
                        parser.nextToken();
                        String field = parser.getCurrentName();
                        System.out.println("\t" + field + ": " + parser.getValueAsString());
                    }
                } else {
                    System.out.println(fieldName + ": " + parser.getText());
                }
            }
            parser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Write simple variables to file
    public static void writeSimpleToFile() {

        JsonFactory jsonFactory = new JsonFactory();
        OutputStream outputStream = null;
        // Car car = new Car("BMW", 4, new Car.Owner("Gatsby", "Newton"), new String[]{"engine", "brake"});

        try {
            outputStream = new FileOutputStream("F:\\Codes\\IDEA\\Tools\\data\\json\\out\\generate_simple.json");
            JsonGenerator generator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
            generator.writeStartObject();

            generator.writeStringField("brand", "Mercedes");
            generator.writeNumberField("doors", 5);
            generator.writeObjectFieldStart("owner");
            generator.writeStringField("first", "Gatsby");
            generator.writeStringField("last", "Newton");
            generator.writeEndObject();

            generator.writeArrayFieldStart("component");
            generator.writeString("engine");
            generator.writeString("brake");
            generator.writeEndArray();

            // Write a object.
            // generator.writeObjectField("owner", new Car.Owner("Gatsby", "Newton"));
            // generator.writeObjectField("component", new String[]{"engine", "brake"});

            generator.writeEndObject();

            generator.flush();
            generator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Write objects to file.
    public static void writeComplexToFile() {

        // If you use JsonFactory jsonFactory = new JsonFactory(), it shows the error:
        // java.lang.IllegalStateException: No ObjectCodec defined for the generator,
        // can only serialize simple wrapper types (type passed edu.wzm.jackson.Car$Owner)
        JsonFactory jsonFactory = new ObjectMapper().getJsonFactory();

        OutputStream outputStream = null;
        // Car car = new Car("BMW", 4, new Car.Owner("Gatsby", "Newton"), new String[]{"engine", "brake"});

        try {
            outputStream = new FileOutputStream("F:\\Codes\\IDEA\\Tools\\data\\json\\out\\generate_complex.json");
            JsonGenerator generator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
            generator.writeStartObject();

            generator.writeStringField("brand", "Mercedes");
            generator.writeNumberField("doors", 5);

            // Write a object.
            generator.writeObjectField("owner", new Car.Owner("Gatsby", "Newton"));
            generator.writeObjectField("component", new String[] {"engine", "brake"});

            generator.writeEndObject();

            generator.flush();
            generator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JsonParseDemo.readFromFile();
        // JsonParseDemo.writeSimpleToFile();
        // JsonParseDemo.writeComplexToFile();
    }
}
