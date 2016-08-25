package pl.morpheme.bookingsystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.morpheme.bookingsystem.domain.User;
import pl.morpheme.bookingsystem.service.UserService;

/**
 * Created by sylwek on 2016-05-02.
*/
@Component
public class UserNameToUser  implements Converter<String, User>{

    @Autowired
    UserService userService;

    public User convert(String element){
        Integer id = Integer.parseInt((String) element);
        User user = userService.findById(id);
        if(user != null){
            System.out.println("Profile : " + user);
            return user;
        }

        return null;

    }




}
