package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.TypeOfLodging;
import XMLandSecurity.backend1.repository.TypeOfLodgingRepository;
import XMLandSecurity.backend1.service.TypeOfLodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfLodgingServiceImpl implements TypeOfLodgingService {

    @Autowired
    private TypeOfLodgingRepository typeOfLodgingRepository;


    @Override
    public List<TypeOfLodging> findAll() {
        return typeOfLodgingRepository.findAll();
    }

    @Override
    public TypeOfLodging findOne(Long id) {
        return typeOfLodgingRepository.findOne(id);
    }

    @Override
    public TypeOfLodging save(TypeOfLodging typeOfLodging) {
        return typeOfLodgingRepository.save(typeOfLodging);
    }

    @Override
    public void delete(Long id) {
        typeOfLodgingRepository.delete(id);
    }
}
