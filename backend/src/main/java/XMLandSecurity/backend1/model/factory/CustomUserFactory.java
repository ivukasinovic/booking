package XMLandSecurity.backend1.model.factory;


import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.model.security.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class CustomUserFactory {

    public static CustomUser create(User user) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().toString());
        } catch (Exception e) {
            authorities = null;
        }
        return new CustomUser(
                user.getId(),
                user.getUsername(),
                user.getPasswordHash(),
                user.getEmail(),
                user.getLastPasswordReset(),
                authorities
        );
    }

}
