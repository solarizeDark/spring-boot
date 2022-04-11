package ru.fedusiv.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.hateoas.Link;

import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class TemplateJsonSerializer {

    static Class[] primitiveWrappers =
            {
                Boolean.class, Character.class, Byte.class, Short.class,
                Integer.class, Long.class, Float.class, Double.class
            };

    public static <T> void serializeRec(T entity, JsonGenerator generator, SerializerProvider serializerProvider, int level)
            throws IOException, IllegalAccessException, NoSuchFieldException {

        generator.writeStartObject();
        Class<?> type = entity.getClass();

        Field f = entity.getClass().getDeclaredField("links");

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

            else if (field.getName().equals("links")) {
                for (Link link : (ArrayList<Link>) field.get(entity)) {
                    generator.writeStartObject("_links");
                    generator.writeStartObject(link.getRel().value());
                    generator.writeStringField("href", link.getHref());
                    generator.writeEndObject();
                }
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
