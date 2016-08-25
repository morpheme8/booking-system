package pl.morpheme.bookingsystem.converter;

/**
 * Created by sylwek on 2016-05-04.
 */
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateConverter() {
        this.formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public LocalDate convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        return LocalDate.parse(source, formatter);
    }

}
