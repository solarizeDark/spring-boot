package ru.fedusiv.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import ru.fedusiv.entities.Group;

import java.io.IOException;

@JsonComponent
public class GroupJsonSerializer extends JsonSerializer<Group> {

    @Override
    public void serialize(Group group, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            TemplateJsonSerializer.serializeRec(group, jsonGenerator, serializerProvider, 0);
        } catch (IllegalAccessException e) {
           throw new IllegalArgumentException(e);
        }
    }

}
