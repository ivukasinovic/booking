package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.City;
import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.repository.LodgingRepository;
import XMLandSecurity.backend1.service.LodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LodgingServiceImpl implements LodgingService {

    @Autowired
    private LodgingRepository lodgingRepository;


    @Override
    public List<Lodging> findAll() {
        return lodgingRepository.findAll();
    }

    @Override
    public Lodging findOne(Long id) {
        return lodgingRepository.findOne(id);
    }

    @Override
    public Lodging save(Lodging user) {
        return lodgingRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        lodgingRepository.delete(id);
    }

    @Override
    public Lodging findByAgent(Long id) {
        return lodgingRepository.findByAgent(id);
    }

    @Override
    public List<Lodging> findByCityAndPersons_number(String city, Integer personsNbr) {
        return lodgingRepository.findByCityNameIgnoreCaseContainingAndPersonsNumber(city, personsNbr);
    }
    @Override
    public List<Lodging> findByCityName(String cityName, Date startDate, Date endDate) {
        return lodgingRepository.findByCityNameIgnoreCaseContainingAndReservationsDateStartBeforeOrReservationsDateStartAfterAndReservationsDateEndBeforeOrReservationsDateEndAfter(cityName,startDate,endDate,startDate,endDate);
    }
//    @Override
//    public List<Lodging> findByAdditionalServiceList(Long id) {
//        return  lodgingRepository.findByAdditionalServiceList(id);
//    }


}
