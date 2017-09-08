package com.justo.mutant.parser.serializer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DoubleSerializer extends JsonSerializer<Double> {

    private static final DecimalFormat FORMATTER;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');

        FORMATTER = new DecimalFormat("0.00", symbols);
    }

    @Override
    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (value == null) {
            jgen.writeNull();
        } else {
            jgen.writeNumber(FORMATTER.format(value));
        }
    }

}
