package ru.fedusiv.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TemplateJsonSerializer {

    static Class[] primitiveWrappers =
            {
                Boolean.class, Character.class, Byte.class, Short.class,
                Integer.class, Long.class, Float.class, Double.class
            };

    public static <T> void serializeRec(T entity, JsonGenerator generator, SerializerProvider serializerProvider, int level)
            throws IOException, IllegalAccessException {

        generator.writeStartObject();
        Class<?> type = entity.getClass();

        for (Field field : type.getDeclaredFields()) {

            field.setAccessible(true);

            Class<?> fieldType = field.getType();

            if (fieldType.equals(String.class)) {
                generator.writeStringField(field.getName(), (String) field.get(entity));
            }

            else if (fieldType.equals(Long.class) || fieldType.equals(Integer.class)) {
                generator.writeStringField(field.getName(), String.valueOf(field.get(entity)));
            }

            else if (fieldType.equals(LocalDate.class)) {
                generator.writeStringField(field.getName(), field.get(entity).toString());
            }

            else if (Arrays.stream(primitiveWrappers).noneMatch(primitive -> primitive == fieldType)) {
                if (level == 0) {
                    generator.writeFieldName(field.getName());
                    serializeRec( field.get(entity), generator, serializerProvider, ++level);
                }
            }

        }

        generator.writeEndObject();
    }

}
