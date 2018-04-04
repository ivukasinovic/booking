package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.model.dto.CertificateDTO;
import XMLandSecurity.backend1.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/certificates")
public class CertificateController {


    @Autowired
    private CertificateService certificateService;

    @RequestMapping(value = "/genCertificate", method = RequestMethod.POST)
    public ResponseEntity<String> genCertificate(@RequestBody CertificateDTO dto) {

        certificateService.generateCertificate(dto);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getCertificateDTO(@PathVariable("id") String idCert) {

        certificateService.getCertificateDTO(idCert);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}