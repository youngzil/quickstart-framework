/**
 * 项目名称：quickstart-json 
 * 文件名：JacksonMain.java
 * 版本信息：
 * 日期：2017年12月14日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.jackson.v1;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;
import org.quickstart.json.jackson.v1.annotation.Account;
import org.quickstart.json.jackson.v1.pojo.Name;
import org.quickstart.json.jackson.v1.pojo.User;
import org.quickstart.json.jackson.v1.pojo.User.Gender;

/**
 * JacksonMain
 * 
 * @author：yangzl@asiainfo.com
 * @2017年12月14日 下午10:50:00
 * @since 1.0
 */
public class JacksonMain {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // 1.1 Simple Data Binding
        simpleDataBinding(mapper);

        // 1.2 Full Data Binding
        fullDataBinding(mapper);

        // 1.3 Data Binding with Generics
        dataBindingWithGenerics(mapper);

        // 1.4 Data Binding with Annotation
        dataBindingWithAnnotation(mapper);

        // 2.1 Tree Model
        treeModel(mapper);

        // 2.2 Construct a Tree
        constructTreeModel(mapper);

        // 3.1 Streaming API (write Json)
        streamingAPIWrite();

        // 3.2 Streaming API (read Json)
        streamingAPIRead();
    }

    @SuppressWarnings("unchecked")
    public static void simpleDataBinding(ObjectMapper mapper) throws Exception {
        // json -> Map
        Map<String, Object> userDataRead = mapper.readValue(new File("user.json"), Map.class);
        System.out.println("simpleDataBinding(): " + userDataRead);

        // Map -> json
        Map<String, Object> userData = new HashMap<String, Object>();
        Map<String, String> nameStruct = new HashMap<String, String>();
        nameStruct.put("first", "Joe");
        nameStruct.put("last", "Sixpack");
        userData.put("name", nameStruct);
        userData.put("gender", "MALE");
        userData.put("verified", Boolean.FALSE);
        userData.put("userImage", "Rm9vYmFyIQ==");
        mapper.writeValue(new File("user-modified.json"), userData);
    }

    public static void fullDataBinding(ObjectMapper mapper) throws Exception {
        // json -> Object
        User user = mapper.readValue(new File("user.json"), User.class);
        System.out.println("fullDataBinding(): " + user);

        // Object -> json
        mapper.writeValue(new File("user-modified.json"), user);
    }

    public static void dataBindingWithGenerics(ObjectMapper mapper) throws Exception {
        // json -> Map
        Map<String, Name> genericData = mapper.readValue(new File("generic.json"), new TypeReference<Map<String, Name>>() {});
        System.out.println("dataBindingWithGenerics():" + genericData);
    }

    public static void dataBindingWithAnnotation(ObjectMapper mapper) throws Exception {
        // json -> Object
        Account account = mapper.readValue(new File("account.json"), Account.class);
        System.out.println("dataBindingWithAnnotation(): " + account);

        account.setGmtCreate(new Date());
        // Object -> json
        mapper.writeValue(new File("account.json"), account);
    }

    public static void treeModel(ObjectMapper mapper) throws Exception {
        // can either use mapper.readTree(JsonParser), or bind to JsonNode
        JsonNode rootNode = mapper.readValue(new File("user.json"), JsonNode.class);

        // ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
        JsonNode nameNode = rootNode.path("name");
        String lastName = nameNode.path("last").getTextValue();
        if ("xmler".equalsIgnoreCase(lastName)) {
            ((ObjectNode) nameNode).put("last", "Jsoner");
        }
        // write it out
        mapper.writeValue(new File("user-modified.json"), rootNode);
    }

    public static void constructTreeModel(ObjectMapper mapper) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode userOb = objectMapper.createObjectNode();
        ObjectNode nameOb = userOb.putObject("name");
        nameOb.put("first", "Thomas");
        nameOb.put("last", "Zhou");
        userOb.put("gender", User.Gender.MALE.toString());
        userOb.put("verified", false);
        userOb.put("userImage", "Foobar!".getBytes());
        // write it out
        mapper.writeValue(new File("user-modified.json"), userOb);
    }

    public static void streamingAPIRead() throws Exception {
        JsonFactory f = new JsonFactory();
        JsonGenerator g = f.createJsonGenerator(new File("user.json"), JsonEncoding.UTF8);

        g.writeStartObject();
        g.writeObjectFieldStart("name");
        g.writeStringField("first", "Thomas");
        g.writeStringField("last", "Zhou");
        g.writeEndObject(); // for field 'name'
        g.writeStringField("gender", Gender.MALE.name());
        g.writeBooleanField("verified", false);
        g.writeFieldName("userImage"); // no 'writeBinaryField' (yet?)
        byte[] binaryData = "Foobar!".getBytes();
        g.writeBinary(binaryData);
        g.writeEndObject();
        g.close(); // important: will force flushing of output, close underlying output stream
    }

    public static void streamingAPIWrite() throws Exception {
        JsonFactory f = new JsonFactory();
        JsonParser jp = f.createJsonParser(new File("user.json"));
        User user = new User();
        jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jp.getCurrentName();
            jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
            if ("name".equals(fieldname)) { // contains an object
                Name name = new Name();
                while (jp.nextToken() != JsonToken.END_OBJECT) {
                    String namefield = jp.getCurrentName();
                    jp.nextToken(); // move to value
                    if ("first".equals(namefield)) {
                        name.setFirst(jp.getText());
                    } else if ("last".equals(namefield)) {
                        name.setLast(jp.getText());
                    } else {
                        throw new IllegalStateException("Unrecognized field '" + fieldname + "'!");
                    }
                }
                user.setName(name);
            } else if ("gender".equals(fieldname)) {
                user.setGender(User.Gender.valueOf(jp.getText()));
            } else if ("verified".equals(fieldname)) {
                user.setVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);
            } else if ("userImage".equals(fieldname)) {
                user.setUserImage(jp.getBinaryValue());
            } else {
                throw new IllegalStateException("Unrecognized field '" + fieldname + "'!");
            }
        }
        jp.close(); // ensure resources get cleaned up timely and properly
        System.out.println("streamingAPIWrite(): " + user);
    }
}
