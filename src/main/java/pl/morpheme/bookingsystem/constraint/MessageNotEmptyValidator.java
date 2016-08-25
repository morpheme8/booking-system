package pl.morpheme.bookingsystem.constraint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by sylwek on 2016-08-22.
 */
@Component
public class MessageNotEmptyValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return SimpleMailMessage.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "message.text");
    }

}
