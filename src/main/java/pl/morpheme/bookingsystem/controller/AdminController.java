package pl.morpheme.bookingsystem.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;

import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.morpheme.bookingsystem.constraint.ImageFileValidator;
import pl.morpheme.bookingsystem.domain.Room;
import pl.morpheme.bookingsystem.domain.RoomDescImg;
import pl.morpheme.bookingsystem.domain.RoomType;
import pl.morpheme.bookingsystem.domain.User;
import pl.morpheme.bookingsystem.domain.UserProfile;
import pl.morpheme.bookingsystem.domain.UserProfileType;
import pl.morpheme.bookingsystem.service.BookingService;
import pl.morpheme.bookingsystem.service.RoomDescImgService;
import pl.morpheme.bookingsystem.service.RoomService;
import pl.morpheme.bookingsystem.service.UserPrincipalService;
import pl.morpheme.bookingsystem.service.UserProfileService;
import pl.morpheme.bookingsystem.service.UserService;
import pl.morpheme.bookingsystem.service.VerificationTokenService;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by sylwek on 2016-06-09.
 */
@Controller
public class AdminController {

    @Autowired
    UserProfileService userProfileService;
    @Autowired
    UserService userService;
    @Autowired
    UserPrincipalService userPrincipalService;
    @Autowired
    BookingService bookingService;
    @Autowired
    VerificationTokenService verificationTokenService;
    @Autowired
    ImageFileValidator fileValidator;
    @Autowired
    RoomDescImgService roomDescImgService;
    @Autowired
    RoomService roomService;

    static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @InitBinder("roomDescImg")
    protected void initBind(WebDataBinder binder) {
        binder.addValidators(fileValidator);
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        model.addAttribute("users", userService.findAllUser());
        model.addAttribute("bookings", bookingService.findAllBookings());
        model.addAttribute("loggedinuser", userPrincipalService.changeEmailPrincipalIntoUser().getFirstName());
        return "indexAdmin";
    }

    @RequestMapping(value = "/admin/add-user", method = RequestMethod.GET)
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/admin/add-user", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult result, ModelMap model) {
      /*  if (result.hasErrors()) {
            return "userform";
        }*/
        Set<UserProfile> profiles = new HashSet<UserProfile>();
        profiles.add(userProfileService.findByType("user"));
        user.setUserProfiles(profiles);
        userService.saveUser(user);
        model.addAttribute("success", "Użytkownik " + user.getFirstName() + " " + user.getLastName()
                + " dodany pomyślnie");
        return "regSuccess";
    }

    @RequestMapping(value = "admin/delete-user-{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id, ModelMap model) {
        Integer idToDelete = Integer.parseInt(id);
        Integer idToCompare = userPrincipalService.changeEmailPrincipalIntoUser().getId();
        if (idToDelete == idToCompare) {
            String message = "Nie można usunąć aktualnie zalogowanego użytkownika.";
            model.addAttribute("message", message);
            return "regFail";
        }
        userService.deleteUserById(idToDelete);
        return "redirect:/admin";
    }

    @RequestMapping(value = {"admin/delete-booking-{id}"}, method = RequestMethod.GET)
    public String deleteBooking(@PathVariable int id) {
        bookingService.deleteById(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/change-user-{id}-profile", method = RequestMethod.GET)
    public String changeUserProfile(@PathVariable Integer id, ModelMap model) {
        getUserProfTypes(model);
        model.addAttribute("user", userService.findById(id));
        return "userProfile";
    }

    @RequestMapping(value = "/admin/change-user-{id}-profile", method = RequestMethod.POST)
    public String changeUserProfilePost(@Valid User user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            getUserProfTypes(model);
            return  "userProfile";
        }


        userService.updateUser(user);
        String success = "Uprawnienia użytkownika " + user.getFirstName() + " zmieniono pomyślnie na: "
                + user.getUserProfiles().toString();
    model.addAttribute("success", success);
        return "regSuccess";
    }

    @RequestMapping(value ="admin/change-user-{userId}-status", method = RequestMethod.GET)
    public String changeUserState(@PathVariable int userId, ModelMap model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("userState", user.getState());
        return "userStatus";
    }

    @RequestMapping(value ="admin/change-user-{userId}-status", method = RequestMethod.POST)
    public String changeUserStatePost(@Valid User user, BindingResult result, @PathVariable int userId, ModelMap model) {

        if (result.hasErrors()) {
            User u = userService.findById(userId);
            model.addAttribute("userState", u.getState());
            return "userStatus";
        }

        User userToUpdate = userService.findById(userId);
        userToUpdate.setState(user.getState());
        userService.updateUser(userToUpdate);
        String success = "Status użytkownika " + userToUpdate.getFirstName() + " zmieniono pomyślnie na: "
                + userToUpdate.getState().toString();
        model.addAttribute("success", success);
        return "regSuccess";
    }

    @RequestMapping(value = "/admin/upload_description", method = RequestMethod.GET)
    public String uploadDescription(ModelMap model) {
        getRoomTypes(model);
        model.addAttribute("roomDescImg", new RoomDescImg());
        return "roomImgDesc";
    }

    @RequestMapping(value = "/admin/upload_description", method = RequestMethod.POST)
    public String handleUploadDescription(@Valid RoomDescImg roomDescImg, BindingResult result, HttpServletRequest request, ModelMap model) throws IOException {
        if (result.hasErrors()) {
            getRoomTypes(model);
            return "roomImgDesc";
        }
        roomDescImgService.saveAndUploadImage(roomDescImg, request);
        model.addAttribute("success", "Opis pokoju oraz plik zapisano pomyślnie");
        return "regSuccess";
    }

    @RequestMapping(value = "/admin/delete_description", method = RequestMethod.GET)
    public String deleteDescriptionMenu(ModelMap model) {
        List<Integer> roomDescImgIds = new LinkedList<Integer>();
        for (RoomDescImg r: roomDescImgService.findAll()) {
            roomDescImgIds.add(r.getId());
        }
        model.addAttribute("roomDescImgIds", roomDescImgIds);
        return "deleteDescription";
    }

    @RequestMapping(value = "/admin/delete_description/{id}", method = RequestMethod.GET)
    public String deleteDescription(@PathVariable Integer id, ModelMap model) {
        RoomDescImg roomDescImg = roomDescImgService.findById(id);
        File file = new File(roomDescImg.getImageUrl());
        file.delete();
        roomDescImgService.deleteById(id);
        model.addAttribute("success", "Usuwanie opisu pokoju o id: " + id + " zakończone powodzeniem");
        return "regSuccess";
    }

    @RequestMapping(value = "/admin/room_menu", method = RequestMethod.GET)
    public String viewRoomMenu(ModelMap model) {
        model.addAttribute("rooms", roomService.findAllRooms());
        return "roomMenu";
    }

    @RequestMapping(value = "/admin/add_room", method = RequestMethod.GET)
    public String addRoom(ModelMap model) {
        getRoomTypes(model);
        model.addAttribute("room", new Room());
        return "addRoom";
    }

    @RequestMapping(value = "/admin/add_room", method = RequestMethod.POST)
    public String handleAddRoom(@Valid Room room, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            getRoomTypes(model);
            return "addRoom";
        }
        roomService.save(room);
        model.addAttribute("success", "Pokój o id: " + room.getId() + " typu: " + room.getType() + " dodany pomyślnie");
        return "regSuccess";
    }


    @RequestMapping(value = "/admin/delete_room/{id}", method = RequestMethod.GET)
    public String deleteRoom(@PathVariable Integer id, Room room, BindingResult result, ModelMap model) {
        try {
            roomService.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "Usuwanie pokoju o id: " + id + " zakończone niepowodzeniem. " +
                    "Nie można usunąć pokoju którego dotyczą aktualne rezerwacje.");
            return "regFail";
        }
        model.addAttribute("success", "Usuwanie pokoju o id: " + id + " zakończone powodzeniem");
        return "regSuccess";
    }


    public void getUserProfTypes(ModelMap model) {
        UserProfileType[] userProfileTypes = UserProfileType.values();
        Set<UserProfile> userProfiles = new HashSet<UserProfile>();
        for (UserProfileType uP: userProfileTypes) {
            UserProfile userProfile = new UserProfile();
            userProfile.setType(uP.getUserProfileType());
            userProfiles.add(userProfile);
        }
        model.addAttribute("userProfiles", userProfiles);

    }

    private void getRoomTypes(ModelMap model) {
        RoomType[] roomTypes = RoomType.values();
        List<Room> rooms = new LinkedList<Room>();
        for (RoomType rT: roomTypes) {
            Room room = new Room();
            room.setType(rT.getRoomType());
            rooms.add(room);
        }
        model.addAttribute("roomTypes", rooms);
    }

    @ModelAttribute("loggedUserProfile")
    public String getLoggedUserProfile() {
        return "admin";
    }

}
