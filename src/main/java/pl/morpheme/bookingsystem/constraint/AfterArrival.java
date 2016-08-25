package pl.morpheme.bookingsystem.constraint;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.morpheme.bookingsystem.domain.Booking;

import java.time.LocalDate;

/**
 * Created by sylwek on 2016-06-30.
 */
@Component
public class AfterArrival implements Validator {

    public boolean supports(Class<?> clazz) {
        return Booking.class.isAssignableFrom(clazz);    }

    public void validate(Object target, Errors errors) {
        Booking booking = (Booking) target;
        LocalDate aD = booking.getArrivalDate();
        LocalDate dD = booking.getDepartureDate();
        if ((aD != null) && (dD != null) ) {
            if (aD.isAfter(dD) || aD.isEqual(dD)) {
                errors.rejectValue("departureDate", "wrong.date");
            }

        }
    }

}
