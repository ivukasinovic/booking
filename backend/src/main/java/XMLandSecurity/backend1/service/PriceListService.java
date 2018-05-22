package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.PriceList;

import java.util.List;

public interface PriceListService {


    List<PriceList> findAll();

    PriceList findOne(Long id);

    PriceList save(PriceList priceList);

    void delete(Long id);

}
