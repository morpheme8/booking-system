package pl.morpheme.bookingsystem.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by sylwek on 2016-05-19.
 *
 * Bez tego, w bazie danych daty będą zapisywane jako tinyblob
 * http://stackoverflow.com/questions/23890687/hibernate-4-with-java-time-localdate-and-date-construct
 * http://www.sothawo.com/2015/01/using-the-new-java8-date-time-classes-with-hibernate/
 * */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}