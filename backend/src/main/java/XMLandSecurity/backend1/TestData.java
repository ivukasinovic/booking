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

    public TestData() {
    }

    @PostConstruct
    private void init() {
//        System.out.println("Kreiranje keystorova");
//        keyStoreService.createKeyStores();

//        //generating hash for passwords
//        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
////        System.out.println(bc.encode("Dejan 1010"));
////        System.out.println(bc.encode("Stefan 1010"));
////        System.out.println(bc.encode("Jovana 1010"));
////        System.out.println(bc.encode("Ivan 1010"));
////        System.out.println(bc.encode("Admin 1010"));
////        System.out.println(bc.encode("Agent 1010"));

    }

}
