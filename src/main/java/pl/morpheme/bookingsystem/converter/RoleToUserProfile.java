package pl.morpheme.bookingsystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.morpheme.bookingsystem.domain.UserProfile;

import org.springframework.core.convert.converter.Converter;
import pl.morpheme.bookingsystem.service.UserProfileService;

/**
 * Created by sylwek on 2016-05-24.
 */
@Component
public class RoleToUserProfile implements Converter<Object, UserProfile> {

    @Autowired
    UserProfileService userProfileService;

    public UserProfile convert(Object element) {
        if (element == null && element.getClass() == UserProfile.class){
            return null;
        }
        String type = element.toString();
        UserProfile profile = userProfileService.findByType(type);
        return profile;
    }
}
