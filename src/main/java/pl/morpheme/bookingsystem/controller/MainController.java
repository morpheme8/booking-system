package pl.morpheme.bookingsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.morpheme.bookingsystem.domain.*;
import pl.morpheme.bookingsystem.service.BookingService;
import pl.morpheme.bookingsystem.service.RoomDescImgService;
import pl.morpheme.bookingsystem.service.UserPrincipalService;
import pl.morpheme.bookingsystem.service.UserProfileService;
import pl.morpheme.bookingsystem.service.UserService;
import pl.morpheme.bookingsystem.service.VerificationTokenService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by sylwek on 2016-04-29.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    BookingService bookingService;
    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
    @Autowired
    VerificationTokenService verificationTokenService;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    UserPrincipalService userPrincipalService;
    @Autowired
    RoomDescImgService roomDescImgService;

    static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        model.addAttribute("users", userService.findAllUser());
        model.addAttribute("bookings", bookingService.findAllBookings());
        model.addAttribute("loggedinuser", userPrincipalService.changeEmailPrincipalIntoUser().getFirstName());
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (userPrincipalService.isCurrentAuthenticationAnonymous()) {
            return "login";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Tymczasowo zapisujem usera, ale z domyślnym statusem "inactive". Aby dokończyć proces rejestracji
     * użytkownik będzie musiał potwierdzić swoją tożsamość. W tym celu wysłany zostaje email
     * z tokenem do weryfikacji.
     **/
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult result, ModelMap model) {
      /*  if (result.hasErrors()) {
            return "userform";
        }*/
        Set<UserProfile> profiles = new HashSet<UserProfile>();
        profiles.add(userProfileService.findByType("user"));
        user.setUserProfiles(profiles);
        user.setState(State.INACTIVE.getState());
        userService.saveUser(user);

        sendEmailVerificationToken(user);
        model.addAttribute("email", user.getEmail());
        return "regConf";
    }

    @RequestMapping(value = "/registrationConfirm/{token}", method = RequestMethod.GET)
    public String registrationConfirm(@PathVariable String token, ModelMap model, RedirectAttributes redirectAttrs) {
        VerificationToken tokenToCheck = verificationTokenService.findByToken(token);
        if (tokenToCheck == null) {
            String message = "Podany token nie istnieje, zarejestruj się w celu uzyskania dostępu do serwisu";
            model.addAttribute("message", message);
            return "regfail";
        } else if (verificationTokenService.isTokenExpired(token)) {
            redirectAttrs.addFlashAttribute("user", tokenToCheck.getUser());
            verificationTokenService.delete(tokenToCheck.getToken());
            return "redirect:/register/resendtoken";
        }
        User user = tokenToCheck.getUser();
        user.setState(State.ACTIVE.getState());
        userService.updateUser(user);
        verificationTokenService.delete(tokenToCheck.getToken());
        String success = "Użytkownik " + user.getFirstName() + " z tokenem: " + tokenToCheck.getToken() +
                " został pomyślnie zweryfikowany. Konto zostało aktywowane. ";
        model.addAttribute("success", success);
        return "regSuccess";
    }

    @RequestMapping(value = "/register/resendtoken", method = RequestMethod.GET)
    public String resendToken(User user, ModelMap model) {
        sendEmailVerificationToken(user);
        String invalid = "Token weryfikacji stracił ważność";
        model.addAttribute("invalid", invalid);
        model.addAttribute("email", user.getEmail());
        return "regConf";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage() {
        return "403";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    public String showPictureMenu(ModelMap model) {
        List<Integer> roomDescImgIds = new LinkedList<Integer>();
        for (RoomDescImg r: roomDescImgService.findAll()) {
            roomDescImgIds.add(r.getId());
        }
        model.addAttribute("roomDescImgIds", roomDescImgIds);
        return "pictureMenu";
    }

    /**
     * Ponieważ zdecydowałem się przechowywać obrazy w wybranym miejscu na lokalnym dysku,
     * napotkałem problem braku możliwości korzystania z lokalnych zasobów przez przeglądarkę.
     * Aby wyświetlić w jsp zapisany w ten sposób plik jpg najpierw muszę zapisać go w tablicy ByteArray,
     * a potem stringiem podać go w formie: <img src="data:image/jpg;base64, ${image}" >
     **/
    @RequestMapping(value = "/picture/{id}", method = RequestMethod.GET)
    public String showSinglePicture(@PathVariable Integer id, ModelMap model) throws IOException {
        RoomDescImg roomDescToShow = roomDescImgService.findById(id);
        File sourceImage = new File(roomDescToShow.getImageUrl());
        BufferedImage image = ImageIO.read(sourceImage);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( image, "jpg", baos );
        byte[] imageInByteArray = baos.toByteArray();
        baos.close();
        String b64 = DatatypeConverter.printBase64Binary(imageInByteArray);

        model.addAttribute("image", b64);
        model.addAttribute("descript", roomDescToShow.getDescription());
        return "singlePicture";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactInfo() {
        return "contact";
    }

    private void sendEmailVerificationToken(User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setUser(user);
        verificationTokenService.save(verificationToken);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Weryfikacja procesu rejestracji");
        message.setText("Aby dokończyć proces rejestracji kliknij w link! Token rejestracji zachowa ważność " +
               "do " + verificationToken.getExpiryDate() + ", http://localhost:8080/registrationConfirm/"
                + verificationToken.getToken());
        mailSender.send(message);
    }

    @ModelAttribute("loggedUserProfile")
    public String getLoggedUserProfile() {
        return userPrincipalService.getLoggedUserProfile();
    }



}