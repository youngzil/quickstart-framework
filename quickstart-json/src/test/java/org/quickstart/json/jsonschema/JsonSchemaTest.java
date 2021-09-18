package org.quickstart.json.jsonschema;

import com.github.fge.jsonschema.core.report.ProcessingReport;
import org.junit.Test;

import static org.quickstart.json.jsonschema.JsonValidateUtil.validatorJsonSchema;

public class JsonSchemaTest {

    @Test
    public void testschema() throws Exception {
        String data = ReadJsonFile.readJsonFileAsString("/json/testString.json");
        String schema = ReadJsonFile.readJsonFileAsString("/json/testSchema.json");
        ProcessingReport processingReport = validatorJsonSchema(schema, data);
        boolean success = processingReport.isSuccess();
        System.out.println(success);
        //        如下方法可以用来接口自动化
        //        Assert.assertTrue(report.isSuccess());
    }

}
