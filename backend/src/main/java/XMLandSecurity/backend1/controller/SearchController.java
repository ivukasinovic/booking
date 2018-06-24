package XMLandSecurity.backend1.controller;


import XMLandSecurity.backend1.domain.AdditionalService;
import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.model.dto.RatingCloudDto;
import XMLandSecurity.backend1.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
            value = "/searchLodging/{cityName}/{personsNbr}/{dateStart}/{dateEnd}/{typeLodging}/{catLodging}/{ratingLod}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> search(@PathVariable("cityName") String cityName, @PathVariable("personsNbr") String personsNbr,
                                    @PathVariable("dateStart") String dateStart, @PathVariable("dateEnd") String dateEnd,
                                    @PathVariable("typeLodging") String typeLodging, @PathVariable("catLodging") String catLodging,
                                    @PathVariable("ratingLod") String ratingLod, @RequestBody ArrayList<Long> checkedArray){
        List<Lodging> reservatedLodgings = new ArrayList<Lodging>();
        List<Lodging> retLodgings = new ArrayList<Lodging>();
        List<Lodging> ratingLodgings = new ArrayList<Lodging>();
        List<Lodging> pom = new ArrayList<Lodging>();
        List<Lodging> ret = new ArrayList<Lodging>();
        List<AdditionalService> aditionalS = new ArrayList<AdditionalService>();
        List<Lodging> retLodDate = new ArrayList<Lodging>();
        RatingCloudDto ratingCloudDto = new RatingCloudDto();
        for(Long l : checkedArray) {
            aditionalS.add(additionalServiceService.findOne(l));

        }
        int broj, ratingNumb;

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

        //pretraga po rejtingu ako ga nije uneo, nista ne radi ako ga je uneo izbaci iz liste one koje nemaju takav rejting
        if(ratingLod.equals("undefined") || ratingLod.equals("null") || personsNbr.equals("")){
            ratingLod ="0";
        }else{ //ako je uneo neki promeni krajnju ret listu
            ArrayList<Long> ids = new ArrayList<Long>();
            for(Lodging lods : ret){
                ids.add(lods.getId());

            }




            ratingNumb = Integer.parseInt(ratingLod);
            int flagg = 0;//nema odgovarajucih
            //provera ako se rejtin ne nalazi u okviru ostalih ne zovi servis!!!
            for(Lodging ser : ret){
                if(ser.getRating() <  ratingNumb+1 ){
                    if(ser.getRating() > ratingNumb-1){
                        flagg =1; // ima barem jedan odgovarajuc
                    }
                }
            }

            if(flagg == 1){
                ratingCloudDto.setRatingValue(ratingNumb);
                ratingCloudDto.setLodgingId(ids);
                //pocetak formiranja jsona za cloud
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json="{\"queriedQuestion\":\"Is there pain in your hand?\"}";
                try {
                    json = ow.writeValueAsString(ratingCloudDto);
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                RestTemplate restTemplate = new RestTemplate();
                String path = "http://localhost:8010/cloud-demo/us-central1/searchRating/";
                //String url=path+"set";
                String url=path;
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<String>(json,headers);
                String answer = restTemplate.postForObject(url, entity, String.class);
                System.out.println("Id of lodgings from cloud: " + answer);
                if(answer.equals(null)){
                    ret = new ArrayList<Lodging>();
                }else { // ako je vratio null znaci da nema nista u blizini te ocene te nista ne vracaj

                    List<String> listaIdStr = Arrays.asList(answer.split(","));
                    ArrayList<Long> noviId = new ArrayList<Long>();
                    for (String st : listaIdStr) { // parsiram rezultate id od odgovarajucih lodginga
                        Long ll = Long.parseLong(st, 10);
                        noviId.add(ll);
                    }

                    for (Long lo : noviId) {
                        System.out.println("Writing lodging with id : " + lo);
                        ratingLodgings.add(lodgingService.findOne(lo));
                    }

                    ret = ratingLodgings;
                }
            }else{
                System.out.println("No use of calling cloud,there is no lodgings with that rating!");
                ret = new ArrayList<Lodging>();
            }
        }

//promeni vrednost liste za nazad
        return new ResponseEntity(ret, HttpStatus.OK);
    }

}
