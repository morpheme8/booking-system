package pl.morpheme.bookingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.morpheme.bookingsystem.domain.User;
import pl.morpheme.bookingsystem.domain.UserProfile;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sylwek on 2016-06-01.
 */
@Service
@Transactional
public class UserPrincipalService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    /**
     * Niestety SecurityContextHolder obchodzi się z danymi usera metodą getUsername(),
     * której nazwa w danym przypadku jest mylna. Zamiast imienia używkownika w całym projekcie
     * do indentyfikacji używam jego adresu email.
     **/
    public String getPrincipal() {
        String email = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails && principal != null) {
            email = ((UserDetails) principal).getUsername();

        } else {
            return null;
        }
        return email;
    }

    public User changeEmailPrincipalIntoUser() {
        return userService.findByEmail(getPrincipal());
    }

    /**
     * Dzięki tej metodzie można upewnić się czy użytkownik zalogował się w bierzącej sesji
     * czy też przy pomocy remember-me.
     **/
    public boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    public String getLoggedUserProfile() {
        Set<UserProfile> userProfiles = new HashSet<UserProfile>();
        Set<String> userProfileTypes = new HashSet<String>();
        if (getPrincipal() != null) {
            userProfiles = changeEmailPrincipalIntoUser().getUserProfiles();
        }
        for (UserProfile uP: userProfiles) {
            userProfileTypes.add(uP.getType());
        }
        if (userProfileTypes.contains("ADMIN")) {
            return "admin";
        } else {
            return "user";
        }
    }


}
