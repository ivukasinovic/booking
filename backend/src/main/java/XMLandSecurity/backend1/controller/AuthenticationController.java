package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.configuration.LoginAttemptService;
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
import org.slf4j.LoggerFactory;
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
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Date;
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
    private LoginAttemptService loginAttemptService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EncDecSimple encDecSimple;

    @Autowired
    private HttpServletRequest request;


    @RequestMapping(method = RequestMethod.POST, value = "${route.authentication}")  // /login  ${route.authentication}
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest, Device device, HttpServletResponse response) throws AuthenticationException, IOException {
            // Perform the authentication
        org.slf4j.Logger loggeri= LoggerFactory.getLogger(AuthenticationController.class);

        Authentication authentication = null;
        try{
            authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
            loginAttemptService.loginSucceeded(getClientIP());
            loggeri.info("Ulogovao se !!!!!!!!  " + loggeri.getName());
        }catch (Exception e) {
            XMLandSecurity.backend1.logger.Logger.getInstance().logTrenutni(" ,Nije uspelo logovanje: " + "  " + new Date());
            loginAttemptService.loginFailed(getClientIP());
            loggeri.warn("Niste se ulogovalii !!!....");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

            SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-authentication so we can generate token
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        User user = userService.findByUsername(userDetails.getUsername());

        if (user == null) {
            XMLandSecurity.backend1.logger.Logger.getInstance().logTrenutni("Pokusao logovanje sa korisnickim imenom: " + user.getUsername() + "  " + new Date());
        } else {
            XMLandSecurity.backend1.logger.Logger.getInstance().log(" ,Ulogovao se: " + user.getUsername() + "  " + new Date());
        }
        if (!user.isActivated()) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String fingerprint = getFingerprint();
        String fingerprintCookie = "__Secure-Fgp=" + fingerprint + "; SameSite=Strict; HttpOnly; Secure";
        response.addHeader("Set-Cookie", fingerprintCookie);
        String fingerprintHash = getFingerprintHash(fingerprint);
        String token = this.tokenUtils.generateToken(userDetails, device, fingerprintHash);
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
    public ResponseEntity<?> register(@Validated @RequestBody User user, Errors error) {
        User savedUser = null;
        if (error.hasErrors()) {
            return new ResponseEntity<String>(error.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }

        user.setRole(Role.USER);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
        user.setActivated(false);
        try{
            savedUser = userService.save(user);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        emailService.sendActivationMail(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/registerAgent",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerAgent(@RequestBody User user) {
        if ((userService.findByUsername(user.getUsername()) != null)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        user.setRole(Role.AGENT);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
        user.setActivated(false);
        User savedUser = userService.save(user);
        //emailService.sendActivationMail(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @RequestMapping(
            value = "/change-password",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@Validated @RequestBody ChangePasswordDto chp, Principal principal, Errors errors) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        if (errors.hasErrors()) {
            return new ResponseEntity<String>(errors.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUsername(principal.getName());
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        if (bc.matches(chp.getOldPw(), user.getPasswordHash())) {
            user.setPasswordHash(bc.encode(chp.getNewPw()));
            userService.save(user);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(user, status);
    }

    @RequestMapping(
            value = "/reset-password-req",
            method = RequestMethod.POST)
    public ResponseEntity<User> forgotPasswordReq(@RequestBody String email) {
        User user = userService.findByEmail(email);
        if ((user == null) || (user.getRole() != Role.USER)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        emailService.sendResetPassword(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/reset-password/{code}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> forgotPassword(@PathVariable String code, @RequestBody ChangePasswordDto chp) {
        String username = EncDecSimple.decrypt(code);
        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        user.setPasswordHash(bc.encode(chp.getNewPw()));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/activate/{username}",
            method = RequestMethod.GET)
    public String activateUser(@PathVariable("username") String username) {
        username = EncDecSimple.decrypt(username);
        User user = userService.findByUsername(username);
        if (user == null) {
            return "Error!";
        }
        user.setActivated(true);
        userService.save(user);
        return "Your account is now activated!";
    }

    private String getFingerprint() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomFgp = new byte[50];
        secureRandom.nextBytes(randomFgp);
        String userFingerprint = DatatypeConverter.printHexBinary(randomFgp);

        //Add the fingerprint in a hardened cookie - Add cookie manually because SameSite attribute is not supported by javax.servlet.http.Cookie class
        return userFingerprint;
    }

    private String getFingerprintHash(String fingerprintCookie) {
        MessageDigest digest = null;
        byte[] userFingerprintDigest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            userFingerprintDigest = digest.digest(fingerprintCookie.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return DatatypeConverter.printHexBinary(userFingerprintDigest);
    }
    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

}
