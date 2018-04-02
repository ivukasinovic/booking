package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.service.KeyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createKeyStore(@RequestParam String alias, @RequestParam String password) {
        boolean saved = keyStoreService.createKeyStore(alias, password);
        if (!saved) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(alias, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/{name}",
            method = RequestMethod.DELETE)
    public ResponseEntity<KeyStore> deleteKeyStore(@PathVariable("name") String alias) {
        keyStoreService.delete(alias);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
