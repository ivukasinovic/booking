package XMLandSecurity.backend1.model.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ivan V. on 04-Apr-18
 */
public class KeyStoreDTO {
    private String name;
    private String password;
    private ArrayList<String> aliases;
    private Date date;

    public KeyStoreDTO(){

    }
    public KeyStoreDTO(String name, String password, ArrayList<String> certificates, Date date) {
        this.name = name;
        this.password = password;
        this.aliases = certificates;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}