package XMLandSecurity.backend1.model.dto;


/**
 * Created by Ivan V. on 19-May-18
 */
public class ChangePasswordDto {
    private String oldPw;
    private String newPw;

    public ChangePasswordDto(){

    }
    public ChangePasswordDto(String oldPw, String newPw) {
        this.oldPw = oldPw;
        this.newPw = newPw;
    }

    public String getOldPw() {
        return oldPw;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setOldPw(String oldPw) {
        this.oldPw = oldPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }
}
