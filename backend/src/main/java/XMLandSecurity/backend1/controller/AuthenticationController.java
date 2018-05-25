package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Role;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.model.dto.ChangePasswordDto;
import XMLandSecurity.backend1.model.json.request.AuthenticationRequest;
import XMLandSecurity.backend1.model.json.response.AuthenticationResponse;
import XMLandSecurity.backend1.model.security.CustomUser;
import XMLandSecurity.backend1.security.TokenUtils;
import XMLandSecurity.backend1.service.EmailService;
import XMLandSecurity.backend1.service.UserService;
import XMLandSecurity.backend1.utility.EncDecSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Base64;
import java.util.logging.Logger;

@RestController
public class AuthenticationController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EncDecSimple encDecSimple;


    @RequestMapping(method = RequestMethod.POST, value = "${route.authentication}")
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

        // Perform the authentication
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        User user = userService.findByUsername(userDetails.getUsername());
        if(!user.isActivated()){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        String token = this.tokenUtils.generateToken(userDetails, device);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @RequestMapping(value = "${route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
        String token = request.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        CustomUser user = (CustomUser) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
            String refreshedToken = this.tokenUtils.refreshToken(token);
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody User user) {
        if ((userService.findByUsername(user.getUsername()) != null) || (userService.findByEmail(user.getEmail()) != null)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        user.setRole(Role.USER);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
        user.setActivated(false);
        User savedUser = userService.save(user);
        emailService.sendActivationMail(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    // ===
    @RequestMapping(
            value = "/registerAgent",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerAgent(@RequestBody User user) {
        if ((userService.findByUsername(user.getUsername()) != null) ) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        user.setRole(Role.AGENT);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
        user.setActivated(false);
        User savedUser = userService.save(user);
         //emailService.sendActivationMail(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // ===

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

    @RequestMapping(
            value = "/reset-password-req",
            method = RequestMethod.POST)
    public ResponseEntity<User> forgotPasswordReq(@RequestBody String email){
        User user =userService.findByEmail(email);
        if((user == null) || (user.getRole() != Role.USER)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        emailService.sendResetPassword(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @RequestMapping(
            value = "/reset-password/{code}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> forgotPassword(@PathVariable String code, @RequestBody ChangePasswordDto chp){
        String username = EncDecSimple.decrypt(code);
        User user =userService.findByUsername(username);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        user.setPasswordHash(bc.encode(chp.getNewPw()));
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/activate/{username}",
            method = RequestMethod.GET)
    public String activateUser(@PathVariable("username") String username ){
        User user = userService.findByUsername(username);
        if(user == null){
            return "Error!";
        }
        user.setActivated(true);
        userService.save(user);
        return "Your account is now activated!";
    }

}
