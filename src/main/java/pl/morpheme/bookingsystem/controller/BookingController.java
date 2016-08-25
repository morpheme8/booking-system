package pl.morpheme.bookingsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.morpheme.bookingsystem.constraint.AfterArrival;
import pl.morpheme.bookingsystem.domain.Booking;
import pl.morpheme.bookingsystem.domain.Room;
import pl.morpheme.bookingsystem.service.BookingService;
import pl.morpheme.bookingsystem.service.DateService;
import pl.morpheme.bookingsystem.service.RoomService;
import pl.morpheme.bookingsystem.service.UserPrincipalService;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sylwek on 2016-06-01.
 */
@Controller
//Konieczne jeżeli flashattriubty wysyłane są do innego kontrolera:
//@SessionAttributes({"bookingFlashAtt", "availableRoomsFlashAtt"})
public class BookingController {

    @Autowired
    UserPrincipalService userPrincipalService;
    @Autowired
    RoomService roomService;
    @Autowired
    DateService dateService;
    @Autowired
    BookingService bookingService;
    @Autowired
    AfterArrival afterArrival;

    static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

  @InitBinder("booking")
    protected void setBinder(WebDataBinder binder) {
      binder.addValidators(afterArrival);
  }

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public String findRoom(ModelMap model) {
            model.addAttribute("booking", new Booking());
            getTodayTomorrow(model);
            return "findRoom";
        }


    /**
     * W ten sposób, rozwiązałem problem przesyłania formularzy pomiędzy kilkoma plikami jsp.
     * Konstrukcja opiera się na redirectie i parametrze "_book" dopisanym do rządanego adresu.
     * Rozważam rozbudowanie funkcjonalności w oparciu o Spring Webflow, ale póki co poniższe
     * podejście do tematu wydaje się być wystarczające. Problemy pojawiły się przy obsłudze
     * wyjątków.
     * */
    @RequestMapping(value = {"/booking"}, method = RequestMethod.POST )
    public String findRoomPost(@Valid Booking booking, BindingResult result, ModelMap model,
                               RedirectAttributes redirectAttrs) {

       if (result.hasErrors()) {
            getTodayTomorrow(model);
            return "findRoom";
       }

            List<Room> rightBedsNo = roomService.findRoomByNoOfBeds(booking.getBeds());
            List<Room> availableRooms = dateService.checkWhatIsAvailable(
                    booking.getArrivalDate(), booking.getDepartureDate(), rightBedsNo);

            if (availableRooms.isEmpty()) {
                redirectAttrs.addFlashAttribute("message", "Brak dostępnych pokojów spełniających podane warunki");
            }
            redirectAttrs.addFlashAttribute("bookingFlashAtt", booking);
            redirectAttrs.addFlashAttribute("availableRoomsFlashAtt", availableRooms);
            return "redirect:/booking?_book";
    }

    /**
     * W przydku gdy dostępnych jest kilka pokojów tego samego typu, użytkownik dostanie
     * do wyboru listę bez duplikatów.
     **/
    @RequestMapping(value = {"/booking"}, params = "_book", method = RequestMethod.GET)
    public String saveBooking(ModelMap model, RedirectAttributes redirectAttrs) {


        model.addAttribute("booking", model.get("bookingFlashAtt"));
        List<Room> availableRooms = (List<Room>) model.get("availableRoomsFlashAtt");
        List<String> roomTypesWithoutDuplicates = new LinkedList<String>();
        HashMap<String, Integer> roomsWithoutDuplicates = new HashMap<String, Integer>();
        for (Room r : availableRooms) {
            if (!roomTypesWithoutDuplicates.contains(r.getType())) {
                roomTypesWithoutDuplicates.add(r.getType());
                roomsWithoutDuplicates.put(r.getType(), r.getId());
            }
        }
        model.addAttribute("rooms", roomsWithoutDuplicates);
        redirectAttrs.addFlashAttribute("roomsFlash", roomsWithoutDuplicates);
        model.addAttribute("message", model.get("message"));

        return "proposedRoom";

    }

    @RequestMapping(value = {"/booking"}, params = "_book", method = RequestMethod.POST)
    public String saveBookingPost(@Valid Booking booking, BindingResult result, ModelMap model) {
        booking.setUser(userPrincipalService.changeEmailPrincipalIntoUser());
        bookingService.saveBooking(booking);
        model.addAttribute("success", "Rezerwacja o id: " + booking.getId() + " w dniach: "
                + booking.getArrivalDate() + " - " + booking.getDepartureDate() + " dla użytkownika: "
                + (userPrincipalService.changeEmailPrincipalIntoUser().getFirstName()) + ", zakończona powodzeniem!!");
        return "regSuccess";
    }





    public void getTodayTomorrow(ModelMap model) {
        model.addAttribute("today", LocalDate.now().toString())
                .addAttribute("tomorrow", LocalDate.now().plusDays(1).toString());
    }

    @ModelAttribute("loggedUserProfile")
    public String getLoggedUserProfile() {
        return userPrincipalService.getLoggedUserProfile();
    }

}