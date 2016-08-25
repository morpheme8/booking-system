package pl.morpheme.bookingsystem.constraint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.morpheme.bookingsystem.domain.RoomDescImg;

/**
 * Created by sylwek on 2016-06-20.
 */
@Component
public class ImageFileValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return RoomDescImg.class.isAssignableFrom(clazz);    }

    public void validate(Object target, Errors errors) {
        RoomDescImg imageFile = (RoomDescImg) target;
        if (imageFile.getImage() != null) {
            if (imageFile.getImage().getSize() == 0) {
                errors.rejectValue("image", "missing.file");
            }
        }
    }

}
