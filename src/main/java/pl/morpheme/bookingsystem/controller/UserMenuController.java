package pl.morpheme.bookingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.morpheme.bookingsystem.constraint.MessageNotEmptyValidator;
import pl.morpheme.bookingsystem.domain.Booking;
import pl.morpheme.bookingsystem.domain.User;
import pl.morpheme.bookingsystem.domain.UserProfile;
import pl.morpheme.bookingsystem.service.BookingService;
import pl.morpheme.bookingsystem.service.UserPrincipalService;
import pl.morpheme.bookingsystem.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sylwek on 2016-06-29.
 */
@Controller
@PropertySource(value = { "classpath:application.properties" })
public class UserMenuController {

    @Autowired
    UserService userService;
    @Autowired
    BookingService bookingService;
    @Autowired
    UserPrincipalService userPrincipalService;
    @Autowired
    private Environment environment;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MessageNotEmptyValidator messageNotEmptyValidator;


    @InitBinder("mailMessage")
    protected void setBinder(WebDataBinder binder) {
        binder.addValidators(messageNotEmptyValidator);
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String userMenu(ModelMap model) {
        User loggedInUser = userPrincipalService.changeEmailPrincipalIntoUser();
        List<Booking> bookings = bookingService.findAllBookingsForUser(loggedInUser);
        model.addAttribute("bookings", bookings);
        model.addAttribute("userName", loggedInUser.getFirstName());
        return "userMenu";
    }

    @RequestMapping(value = {"/user/delete_booking/{id}"}, method = RequestMethod.GET)
    public String deleteBooking(@PathVariable int id) {
        bookingService.deleteById(id);
        return "redirect:/user";
    }

    @RequestMapping(value = {"/user/ask_question/{id}"}, method = RequestMethod.GET)
    public String askQuestion(@PathVariable int id, ModelMap model) {
        model.addAttribute("bookingId", id);
        model.addAttribute("mailMessage", new SimpleMailMessage());
        return "sendQuestion";
    }

    @RequestMapping(value = {"/user/ask_question/{id}"}, method = RequestMethod.POST)
    public String sendQuestion(@ModelAttribute("mailMessage") @Valid SimpleMailMessage message,
                                BindingResult result, @PathVariable int id, ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("bookingId", id);
            return "sendQuestion";
        }

        String emailAdress = environment.getRequiredProperty("email.adress");
        message.setTo(emailAdress);
        message.setSubject("Zapytanie dotyczące rezerwacji o id: " + id);
        mailSender.send(message);        model.addAttribute("success",
                "Wysyłanie zapytanie dotyczęcego rezerwacji o id: " + id + " zakończone powodzeniem");
        return "regSuccess";
    }

    @ModelAttribute("loggedUserProfile")
    public String getLoggedUserProfile() {
        return userPrincipalService.getLoggedUserProfile();
    }

}
