package org.quickstart.json.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Iterator;


// @Slf4j
public class JsonValidateUtil {
    private final static JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

    /**
     * 校验JSON
     *
     * @param schema json模式数据（可以理解为校验模板）
     * @param data 需要验证Json数据
     * @return
     */
    public static ProcessingReport validatorJsonSchema(String schema, String data) throws IOException {
        ProcessingReport processingReport = null;

        JsonNode jsonSchema = JsonLoader.fromString(schema);
        JsonNode jsonData = JsonLoader.fromString(data);
        processingReport = factory.byDefault().getValidator().validateUnchecked(jsonSchema, jsonData);
        boolean success = processingReport.isSuccess();
        if (!success) {
            Iterator<ProcessingMessage> iterator = processingReport.iterator();
            while (iterator.hasNext()) {
                // log.error(String.valueOf(iterator.next()));
                System.out.println(String.valueOf(iterator.next()));
            }
        }
        return processingReport;
    }
}
