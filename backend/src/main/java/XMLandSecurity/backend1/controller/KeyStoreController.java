package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.model.dto.KeyStoreDTO;
import XMLandSecurity.backend1.service.KeyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.security.KeyStore;
import java.util.ArrayList;

/**
 * Created by Ivan V. on 02-Apr-18
 */
@RestController
@RequestMapping("/keyStore")
public class KeyStoreController {

    @Autowired
    KeyStoreService keyStoreService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<String>> getKeyStores() {
        ArrayList<String> keyStores = keyStoreService.getKeyStores();
        return new ResponseEntity<>(keyStores, HttpStatus.OK);
    }
    @RequestMapping(
            value = "/getKeyStore",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyStoreDTO> getKeyStore(@RequestBody KeyStoreDTO keyStore){
        keyStore = keyStoreService.getKeyStore(keyStore.getName(),keyStore.getPassword());
        if(keyStore == null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(keyStore,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createKeyStore(@RequestParam String name, @RequestParam String password) {
        boolean saved = keyStoreService.createKeyStore(name, password);
        if (!saved) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(name, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/{name}",
            method = RequestMethod.DELETE)
    public ResponseEntity<KeyStore> deleteKeyStore(@PathVariable("name") String name) {
        keyStoreService.delete(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
