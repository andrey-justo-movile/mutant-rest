package com.justo.mutant.parser;

import java.io.IOException;

public interface Mapper {

    /**
     * Perform a complex type conversion for the provided object into the informed class
     *
     * @param obj   The object to be converted
     * @param clazz The target conversion class
     * @param <T>   Generic target type
     * @return An Object of type <T>
     */
    public <T> T convert(Object obj, Class<T> clazz);

    /**
     * Perform a complex type conversion for the provided object into the informed class
     *
     * @param obj           The object to be converted
     * @param typeReference The target conversion type reference
     * @param <T>           Generic target type
     * @return An Object of type <T>
     */
    public <T> T convert(Object obj, Object typeReference);

    /**
     * Read json and convert it into the requested object
     *
     * @param value JSON content
     * @param clazz The target conversion class
     * @param <T>   Generic target type
     * @return Requested object populated with JSON data
     */
    public <T> T readJson(String value, Class<T> clazz);

    /**
     * Read json and convert it into the requested object
     *
     * @param value         JSON content
     * @param typeReference The target conversion type reference
     * @param <T>           Generic target type
     * @return Requested object populated with JSON data
     */
    public <T> T readJson(String value, Object typeReference);

    /**
     * Convert the object into JSON
     *
     * @param obj Object to parse
     * @return JSON data
     */
    public String toJson(Object obj);

    /**
     * Deep copy object by serializing and deserializing the object
     *
     * @param obj   Object to copy
     * @param clazz Object class destination
     * @param <T>   Object class
     * @return Deep copy of 'obj'
     * @throws IOException
     */
    public <T> T copy(T obj, Class<T> clazz) throws IOException;

    /**
     * Convert the object into pretty JSON
     *
     * @param obj Object to parse
     * @return JSON data
     */
    public String toPrettyJson(Object obj);

}
