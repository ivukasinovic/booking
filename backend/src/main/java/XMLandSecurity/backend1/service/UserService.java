package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.User;

import java.util.List;

/**
 * Created by Ivan V. on 07-May-18
 */
public interface UserService {
    List<User> findAll();

    User findOne(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User save(User user);

    void delete(Long id);

    //void delete(User user);


}

