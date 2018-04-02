package XMLandSecurity.backend1.service.impl;
import XMLandSecurity.backend1.service.KeyStoreService;
import org.springframework.stereotype.Service;
import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.util.ArrayList;

/**
 * Created by Ivan V. on 02-Apr-18
 */
@Service
public class KeyStoreImpl implements KeyStoreService {
    private KeyStore keyStore;

    public KeyStoreImpl() {
        try {
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getKeyStores() {
        File folder = new File("keystores");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> keyStores = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                keyStores.add(listOfFiles[i].getName());
            }
        }
        return keyStores;
    }

    @Override
    public boolean createKeyStore(String alias, String password) {
        loadKeyStore(null, password.toCharArray());
        return saveKeyStore(alias, password.toCharArray());
    }

    @Override
    public void delete(String alias) {
        File file = new File("keystores/" + alias);
        boolean uspeo = file.delete();
    }

    public void loadKeyStore(String fileName, char[] password) {
        try {
            if (fileName != null) {
                keyStore.load(new FileInputStream("keystores/" + fileName), password);
            } else {
                //Ako je cilj kreirati novi KeyStore poziva se i dalje load, pri cemu je prvi parametar null
                keyStore.load(null, password);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean saveKeyStore(String fileName, char[] password) {
        try {
            keyStore.store(new FileOutputStream("keystores/" + fileName), password);
            return true;
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
