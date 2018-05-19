package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.model.dto.ChangePasswordDto;
import XMLandSecurity.backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Ivan V. on 19-May-18
 */
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/change-password",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> changePassword(@RequestBody ChangePasswordDto chp, Principal principal){
        HttpStatus status = HttpStatus.FORBIDDEN;
        User user = userService.findByUsername(principal.getName());
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        if(bc.matches(chp.getOldPw(),user.getPasswordHash())){
            user.setPasswordHash(bc.encode(chp.getNewPw()));
            userService.save(user);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(user,status);
    }
}
