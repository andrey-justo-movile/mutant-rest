package com.justo.mutant.parser;

import java.io.IOException;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.justo.mutant.parser.serializer.DoubleSerializer;


public class JacksonMapper implements Mapper {

    /**
     * The singleton Mapper
     */
    private static final Mapper SHARED_MAPPER;

    static {
        SHARED_MAPPER = new JacksonMapper(standardConfiguration(new ObjectMapper()));
    }

    /**
     * Configure an ObjectMapper to the project's standards
     *
     * @param objectMapper An instance of ObjectMapper to be configured
     * @return A Mapper instance already configured
     */
    public final static ObjectMapper standardConfiguration(ObjectMapper objectMapper) {
        objectMapper.registerModule(new ParameterNamesModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new SimpleModule().addSerializer(Double.class, new DoubleSerializer()));

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

        objectMapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);

        return objectMapper;
    }

    /**
     * Provide a singleton Mapper configured to the project's standards
     *
     * @return A singleton Mapper instance
     */
    public static Mapper standardMapper() {
        return SHARED_MAPPER;
    }

    /**
     * Provide a new customized version of the Mapper
     *
     * @param customizer The customization method to the ObjectMapper
     * @return A customized instance of Mapper
     */
    public static Mapper customMapper(Consumer<ObjectMapper> customizer) {
        ObjectMapper objectMapper = standardConfiguration(new ObjectMapper());
        customizer.accept(objectMapper);

        return new JacksonMapper(objectMapper);
    }

    /**
     * The wrapped ObjectMapper instance
     */
    private final ObjectMapper OBJECT_MAPPER;

    /**
     * Constructs a wrapped and already configured ObjectMapper
     *
     * @param mapper The configured ObjectMapper
     */
    private JacksonMapper(ObjectMapper mapper) {
        this.OBJECT_MAPPER = mapper;
    }

    /**
     * Perform a complex type conversion for the provided object into the informed class
     *
     * @param obj   The object to be converted
     * @param clazz The target conversion class
     * @param <T>   Generic target type
     * @return An Object of type <T>
     */
    public <T> T convert(Object obj, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(obj, clazz);
    }

    /**
     * Perform a complex type conversion for the provided object into the informed class
     *
     * @param obj           The object to be converted
     * @param typeReference The target conversion type reference
     * @param <T>           Generic target type
     * @return An Object of type <T>
     */
    public <T> T convert(Object obj, TypeReference<T> typeReference) {
        return OBJECT_MAPPER.convertValue(obj, typeReference);
    }

    /**
     * Read json and convert it into the requested object
     *
     * @param value JSON content
     * @param clazz The target conversion class
     * @param <T>   Generic target type
     * @return Requested object populated with JSON data
     */
    public <T> T readJson(String value, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(value, clazz);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read json string", e);
        }
    }

    /**
     * Read json and convert it into the requested object
     *
     * @param value         JSON content
     * @param typeReference The target conversion type reference
     * @param <T>           Generic target type
     * @return Requested object populated with JSON data
     */
    public <T> T readJson(String value, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(value, typeReference);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read json string", e);
        }
    }

    /**
     * Convert the object into JSON
     *
     * @param obj Object to parse
     * @return JSON data
     */
    public String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to write json string", e);
        }
    }

    /**
     * Deep copy object by serializing and deserializing the object
     *
     * @param obj   Object to copy
     * @param clazz Object class destination
     * @param <T>   Object class
     * @return Deep copy of 'obj'
     * @throws IOException
     */
    public <T> T copy(T obj, Class<T> clazz) throws IOException {
        return readJson(toJson(obj), clazz);
    }

    /**
     * Convert the object into pretty JSON
     *
     * @param obj Object to parse
     * @return JSON data
     */
    public String toPrettyJson(Object obj) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to write pretty json string", e);
        }
    }

    @Override
    public <T> T convert(Object obj, Object typeReference) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public <T> T readJson(String value, Object typeReference) {
        throw new UnsupportedOperationException("Method not implemented");
    }

}
