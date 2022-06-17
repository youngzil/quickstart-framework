package org.quickstart.json.jsonpath;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.quickstart.json.fastjson2.Fastjson2Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonPathTest {

    @Test
    public void testJsonPath() throws IOException {

        InputStream is = Fastjson2Test.class.getClassLoader().getResourceAsStream("json/JsonPathDemo.json");
        String json = IOUtils.toString(is, "UTF-8");
        List<String> authors = JsonPath.read(json, "$.store.book[*].author");

        System.out.println(authors);


        // Set a value

            String newJson = JsonPath.parse(json).set("$['store']['book'][0]['author']", "Paul").jsonString();

    }

}
