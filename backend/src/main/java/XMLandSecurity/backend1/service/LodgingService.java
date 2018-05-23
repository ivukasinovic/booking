package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.Lodging;

import java.util.List;

public interface LodgingService {

    List<Lodging> findAll();

    Lodging findOne(Long id);

    Lodging save(Lodging user);

    void delete(Long id);

}
