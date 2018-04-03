package XMLandSecurity.backend1.service;

import java.util.ArrayList;

/**
 * Created by Ivan V. on 02-Apr-18
 */
public interface KeyStoreService {

    ArrayList<String> getKeyStores();

    boolean createKeyStore(String alias, String password);

    void delete(String id);
}
