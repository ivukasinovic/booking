package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.model.dto.CertificateDTO;
import XMLandSecurity.backend1.service.CertificateService;
import XMLandSecurity.backend1.service.KeyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private KeyStoreService keyStoreService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CertificateDTO>> getCerticatesAll() {
        List<CertificateDTO> certificateDTOS = keyStoreService.getCertificatesDTO();
        return new ResponseEntity<>(certificateDTOS, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CertificateDTO> genCertificate(@RequestBody CertificateDTO dto) {
        if (dto.getCaa() == 1) {
            dto.setCa(true);
        } else {
            dto.setCa(false);
        }
        if(dto.getUid() == null){
            dto.setUid("");
        }
        Certificate created = certificateService.generateCertificate(dto);
        if (created != null) {
            CertificateDTO creDto = new CertificateDTO(created);
            return new ResponseEntity<>(creDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
        String respond;
        if (certificateService.check(id)) {
            respond = "good";
        } else {
            respond = "revoked";
        }

        return new ResponseEntity<>(respond, HttpStatus.OK);
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> download(@PathVariable String id) {
        String file = certificateService.download(id);
        return new ResponseEntity<>(file, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/revoke/{id}", method = RequestMethod.GET)
    public ResponseEntity<CertificateDTO> revoke(@PathVariable String id) {
        certificateService.revoke(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CertificateDTO> delete(@PathVariable String id) {
        keyStoreService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}