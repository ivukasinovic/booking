package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.TypeOfLodging;

import java.util.List;

public interface TypeOfLodgingService {


    List<TypeOfLodging> findAll();

    TypeOfLodging findOne(Long id);

    TypeOfLodging save(TypeOfLodging typeOfLodging);

    void delete(Long id);


}
