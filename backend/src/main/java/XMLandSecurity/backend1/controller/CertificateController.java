package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.model.dto.CertificateDTO;
import XMLandSecurity.backend1.service.CertificateService;
import XMLandSecurity.backend1.service.KeyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.security.KeyStore;
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

    @RequestMapping(value = "/genCertificate",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> genCertificate(@RequestHeader("keyStoreName") String keyStoreName, @RequestHeader("keyStorePw") String keyStorePw,@RequestBody CertificateDTO dto) {
        Certificate certificate =  certificateService.generateCertificate(dto, keyStoreName, keyStorePw);
//        if( dto.isCa() != true ){
//            dto.setCa(true);
//        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(value="/getIssuers", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getIssuers(@RequestHeader("keyStoreName") String keyStoreName, @RequestHeader("keyStorePw") String keyStorePw){
        ArrayList<String> issuers = keyStoreService.getIssuers(keyStoreName,keyStorePw);
        return new ResponseEntity<>(issuers,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<CertificateDTO> getCertificate(@PathVariable String id, @RequestHeader("keyStoreName") String keyStoreName, @RequestHeader("keyStorePw") String keyStorePw){
        Certificate cert = keyStoreService.getCert(keyStoreName,keyStorePw,id);
        CertificateDTO certificateDTO = new CertificateDTO(cert);
        return new ResponseEntity<>(certificateDTO,HttpStatus.OK);
    }


}