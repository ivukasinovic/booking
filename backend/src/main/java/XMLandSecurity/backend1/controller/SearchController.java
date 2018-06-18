package XMLandSecurity.backend1.controller;


import XMLandSecurity.backend1.domain.AdditionalService;
import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchController {


    @Autowired
    private LodgingService lodgingService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AdditionalServiceService additionalServiceService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;


    @RequestMapping(
            value = "/searchLodging/{cityName}/{personsNbr}/{dateStart}/{dateEnd}/{typeLodging}/{catLodging}/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> search(@PathVariable("cityName") String cityName, @PathVariable("personsNbr") String personsNbr,
                                    @PathVariable("dateStart") String dateStart, @PathVariable("dateEnd") String dateEnd,
                                    @PathVariable("typeLodging") String typeLodging, @PathVariable("catLodging") String catLodging,
                                    @RequestBody ArrayList<Long> checkedArray){
        List<Lodging> reservatedLodgings = new ArrayList<Lodging>();
        List<Lodging> retLodgings = new ArrayList<Lodging>();
        List<Lodging> pom = new ArrayList<Lodging>();
        List<Lodging> ret = new ArrayList<Lodging>();
        List<AdditionalService> aditionalS = new ArrayList<AdditionalService>();
        List<Lodging> retLodDate = new ArrayList<Lodging>();
        for(Long l : checkedArray) {
            aditionalS.add(additionalServiceService.findOne(l));

        }
        int broj;

        if(dateStart.equals("undefined") || dateStart.equals("")) {
            dateStart ="1995-09-16" ;
        }
        if(dateEnd.equals("undefined") || dateEnd.equals("")) {
            dateEnd ="1995-09-16";
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateS = null,dateE = null;
        try {
            dateS = format.parse(dateStart);
            dateE = format.parse(dateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(typeLodging.equals("undefined") || typeLodging.equals("null")) {
            typeLodging = "";
        }
        if(catLodging.equals("undefined") || catLodging.equals("null")) {
            catLodging = "";
        }else if(catLodging.equals("uncategorized") ){
            catLodging= "0";
        }
        if(cityName.equals("undefined") || cityName.equals("null")) {
            cityName = "";
        }

        if(personsNbr.equals("undefined") || personsNbr.equals("") || personsNbr.equals("null")) {
            //ne bi trebalo nikad da udje
            System.out.println("error serach...");
            retLodgings = lodgingService.findAll();
        }else {
            System.out.println("\n Search ... " );
            broj = Integer.parseInt(personsNbr);
            reservatedLodgings = lodgingService.findByReservationsDateStartBetweenAndReservationsDateEndBetween(dateS,dateE,dateS,dateE);
            retLodgings = lodgingService.findByCityAndPersons_number(cityName, broj,typeLodging, catLodging);


            int flag = 0;//petlja za proveravanje koji smestaji su dozvoljeni zbog termina rezervacija
            for(Lodging rl : retLodgings ){
                for(Lodging rll : reservatedLodgings ) {
                    if (rl.getId() == rll.getId()) {
                        //ako je isti on mi ne treba
                        flag = 1;
                    }
                }
                if(flag ==0){
                    retLodDate.add(rl);

                }
            }

            //petlja za prihvatanje odgovarajucih smestaja na osnvu additiona service

            for(Lodging lod : retLodDate ){// prolazim kroz sve , oni koji imaju sve additionalS njih saljem dalje
                int flag1 = 0;
                for(AdditionalService adsOne : aditionalS){//kroz korisnikove zelje
                    List<AdditionalService> lodAds = lod.getAdditionalServiceList();
                    // ako je 1 znaci da postoji u smestaju
                    for(AdditionalService lodAdsOne : lodAds){// ako jedan od zeljenh ne postoji u svim od smestaja, izbacujemo smestaj
                        if(adsOne.getId() == lodAdsOne.getId()){
                            if((aditionalS.indexOf(adsOne)+1)== aditionalS.size()){
                                ret.add(lod);
                            }
                            break;
                        }
                        flag=1;

                    }
                }
            }
        }
        //ako nema zahteva za add Serv onda mu prosledjuemo proslu listu  sa svim vrednostima
        if(aditionalS.size() ==0){
            ret = retLodDate;
        }

//promeni vrednost liste za nazad
        return new ResponseEntity(ret, HttpStatus.OK);
    }

}