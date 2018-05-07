package XMLandSecurity.backend1;

import XMLandSecurity.backend1.service.KeyStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Ivan V. on 01-May-18
 */
@Component
public class TestData {

    @Autowired
    private KeyStoreService keyStoreService;

    @PostConstruct
    private void init() {
//        System.out.println("Kreiranje keystorova");
//        keyStoreService.createKeyStores();

        //generating hash for passwords
//        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
//        System.out.println(bc.encode("agent"));

    }

}
