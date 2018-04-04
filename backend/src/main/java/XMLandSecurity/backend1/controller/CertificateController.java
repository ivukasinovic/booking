package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.model.dto.CertificateDTO;
import XMLandSecurity.backend1.service.CertificateService;
import XMLandSecurity.backend1.service.KeyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
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

    @RequestMapping(value = "/genCertificate", method = RequestMethod.POST)
    public ResponseEntity<String> genCertificate(@RequestBody CertificateDTO dto) {
        Certificate certificate =  certificateService.generateCertificate(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(value="/getIssuers", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getIssuers(@RequestHeader("keyStoreName") String keyStoreName, @RequestHeader("keyStorePw") String keyStorePw){
        ArrayList<String> issuers = keyStoreService.getIssuers(keyStoreName,keyStorePw);
        return new ResponseEntity<>(issuers,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<CertificateDTO> getCertificate(@PathVariable String id, @RequestHeader("keyStoreName") String keyStoreName, @RequestHeader("keyStorePw") String keyStorePw){
        System.out.println("Primio " + id + "KEYSTORE: " + keyStoreName +  keyStorePw);
        //treba da vrati DTO
        //CertificateDTO certificateDTO = new CertificateDTO(Certificate.....)
        CertificateDTO certificateDTO = null;
        return new ResponseEntity<>(certificateDTO,HttpStatus.OK);

    }


}