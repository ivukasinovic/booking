package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.PriceList;
import XMLandSecurity.backend1.repository.PriceListRepository;
import XMLandSecurity.backend1.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;


    @Override
    public List<PriceList> findAll() {
        return priceListRepository.findAll();
    }

    @Override
    public PriceList findOne(Long id) {
        return priceListRepository.findOne(id);
    }

    @Override
    public PriceList save(PriceList priceList) {
        return priceListRepository.save(priceList);
    }

    @Override
    public void delete(Long id) {
        priceListRepository.delete(id);
    }
}
