package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Role;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.model.dto.CertificateDTO;
import XMLandSecurity.backend1.service.CertificateService;
import XMLandSecurity.backend1.service.KeyStoreService;
import XMLandSecurity.backend1.service.PermissionService;
import XMLandSecurity.backend1.service.UserService;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private UserService userService;

    @Autowired
    private KeyStoreService keyStoreService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CertificateDTO>> getCerticatesAll() {
        List<CertificateDTO> certificateDTOS = keyStoreService.getCertificatesDTO();
        return new ResponseEntity<>(certificateDTOS, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CertificateDTO> genCertificate(@RequestBody CertificateDTO dto, Principal principal) {
        if (dto.getCaa() == 1) {
            dto.setIsCa(true);
        } else {
            dto.setIsCa(false);
        }
        if (dto.getisCa()) {
            User user = userService.findByUsername(principal.getName());
            if (user.getRole() != Role.ADMIN) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        X509Certificate created = certificateService.generateCertificate(dto);
        if (created != null) {
            CertificateDTO creDto = new CertificateDTO(created);
            return new ResponseEntity<>(creDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/request",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CertificateDTO> genCertificateRequest(@RequestBody CertificateDTO dto, Principal principal) {


        PKCS10CertificationRequest created = certificateService.generateCertificateRequest(dto);
        if (created != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/request",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificateDTO>> getCertificateRequest(Principal principal) {

        User user = userService.findByUsername(principal.getName());
        if (user.getRole() != Role.ADMIN) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<CertificateDTO> csrList = certificateService.getAllCSRs();

        return new ResponseEntity<>(csrList, HttpStatus.OK);


    }

    @RequestMapping(value = "/request/approve/{id}",
            method = RequestMethod.GET)
    public ResponseEntity aproveCertificateRequest(@PathVariable("id") String id, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        if (user.getRole() != Role.ADMIN) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        certificateService.aproveCSR(id);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/request/delete/{id}",
            method = RequestMethod.GET)
    public ResponseEntity deleteCertificateRequest(@PathVariable("id") String id, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        if (user.getRole() != Role.ADMIN) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        certificateService.deleteCSR(id);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/issuers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getIssuers() {
        ArrayList<String> issuers = keyStoreService.getIssuers();
        return new ResponseEntity<>(issuers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CertificateDTO> getCertificate(@PathVariable String id) {
        CertificateDTO certDto = keyStoreService.getCertificateDTO(id);
        return new ResponseEntity<>(certDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> checkCertificate(@PathVariable String id) {
        String respond = certificateService.check(id);
        return new ResponseEntity<>(respond, HttpStatus.OK);
    }

    @RequestMapping(value = "/crl", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<X509Certificate>> getCRLlist() {

        List<X509Certificate> listRevoked = certificateService.readRevoked();

        return new ResponseEntity<>(listRevoked, HttpStatus.OK);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> download(@PathVariable String id) {
        String file = certificateService.download(id);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    @RequestMapping(value = "/revoke/{id}", method = RequestMethod.GET)
    public ResponseEntity<CertificateDTO> revoke(@PathVariable String id, Principal principal) {
        Role permission = permissionService.findByEndpointAndMethod("/certificates/revoke/{id}", "GET").getRole();
        if (!permission.equals(getRole(principal.getName()))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        certificateService.revoke(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CertificateDTO> delete(@PathVariable String id, Principal principal) {
        Role permission = permissionService.findByEndpointAndMethod("/certificates/{id}", "DELETE").getRole();
        if (!permission.equals(Role.USER)) {
            if (!permission.equals(getRole(principal.getName()))) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        keyStoreService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    Role getRole(String username) {
        User user = userService.findByUsername(username);
        return user.getRole();
    }

}