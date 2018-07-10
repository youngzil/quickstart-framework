package org.quickstart.json.jackson.v2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

import org.quickstart.json.jackson.v2.entity.Car;
import org.quickstart.json.jackson.v2.entity.User;
import org.quickstart.json.jackson.v2.entity.Car.Owner;

/**
 * Created by GatsbyNewton on 2016/4/15.
 */
public class ObjectMapperDemo {

    public static void readFromString() {
        String carJson = "{ \"brand\" : \"Mercedes\"," + "  \"doors\" : 5," + "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," + "  \"nestedObject\" : { \"field\" : \"value\" } }";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON from a Reader instance.
            // Reader reader = new StringReader(carJson);
            // JsonNode node = objectMapper.readValue(reader, JsonNode.class);
            JsonNode node = objectMapper.readValue(carJson, JsonNode.class);

            JsonNode brandNode = node.get("brand");
            String brand = brandNode.asText();
            System.out.println("brand = " + brand);

            JsonNode doorsNode = node.get("doors");
            Integer doors = doorsNode.asInt();
            System.out.println("doors = " + doors);

            JsonNode owners = node.get("owners");
            JsonNode johnNode = owners.get(0);
            String owner = johnNode.asText();
            System.out.println("john = " + owner);

            JsonNode nestNode = node.get("nestedObject");
            JsonNode fieldNode = nestNode.get("field");
            String field = fieldNode.asText();
            System.out.println("field = " + field);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {

        try {
            String path = "F:\\Codes\\IDEA\\Tools\\data\\json\\car.json";
            // Read JSON from an local file.
            // File file = new File(path);
            // Read JSON from an InputStream.
            InputStream input = new FileInputStream(path);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readValue(input, JsonNode.class);

            JsonNode brandNode = node.get("brand");
            String brand = brandNode.asText();
            System.out.println("brand = " + brand);

            JsonNode doorsNode = node.get("doors");
            Integer doors = doorsNode.asInt();
            System.out.println("doors = " + doors);

            JsonNode ownerNode = node.get("owner");
            JsonNode nameNode = ownerNode.get("first");
            String first = nameNode.asText();
            System.out.println("first = " + first);

            JsonNode comsNode = node.get("component");
            JsonNode comNode = comsNode.get(0);
            String component = comNode.asText();
            System.out.println("component = " + component);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToString() {
        Car car = new Car("BMW", 4, new Car.Owner("Gatsby", "Newton"), new String[] {"engine", "brake"});

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(car);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readToPOJO() throws IOException {

        String json = "{ \"name\": \"Gatsby\"," + "  \"gender\": \"MALE\"," + "  \"age\": 24" + "}";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            User user = objectMapper.readValue(json, User.class);
            System.out.print(user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile() throws IOException {
        Car car = new Car("BMW", 4, new Car.Owner("Gatsby", "Newton"), new String[] {"engine", "brake"});

        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(new File("F:\\Codes\\IDEA\\Tools\\data\\json\\out\\out.json"));
            objectMapper.writeValue(outputStream, car);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // ObjectMapperDemo.readFromString();
        // ObjectMapperDemo.readFromFile();
        ObjectMapperDemo.readToPOJO();
        // ObjectMapperDemo.writeToString();
        // ObjectMapperDemo.writeToFile();
    }
}
