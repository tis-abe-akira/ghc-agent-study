package com.nablarch.example.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import nablarch.core.text.FormatterUtil;

/**
 * JSONシリアライズ時に日付形式の値を{@link FormatterUtil} を使ってフォーマットするクラス。
 *
 * @author Sioiri Hisaaki
 */
public class DateSerializer extends JsonSerializer {

    @Override
    public void serialize(final Object o, final JsonGenerator generator, final SerializerProvider provider) throws
            IOException,
            JsonProcessingException {

        if (o == null) {
            generator.writeNull();
        } else {
            generator.writeString(FormatterUtil.format("dateTime", o));
        }
    }
}
