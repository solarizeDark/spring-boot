package ru.fedusiv.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import ru.fedusiv.entities.Student;

import java.io.IOException;

//@JsonComponent
public class StudentJsonSerializer extends JsonSerializer<Student> {

    @Override
    public void serialize(Student student, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            TemplateJsonSerializer.serializeRec(student, jsonGenerator, serializerProvider, 0);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
