package com.project.services;

import com.project.model.AdditionalService;
import com.project.model.MessageRes;
import com.project.model.CategoryOfLodging;
import com.project.model.ReservationRes;
import com.project.model.TypeOfLodging;
import com.project.model.LodgingRes;

import com.project.repository.*;

import com.project.ws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Smekac (Dejan Stojkic). on 19-Jun-18
 */

@Service
public class SyncServiceImpl implements SyncService {

    @Autowired
    private AdditionServiceRepository additionServiceRepository;

    @Autowired
    private CategoryOfLodgingRepository categoryOfLodgingRepository;

    @Autowired
    private LodgingResRepository lodgingResRepository;

    @Autowired
    private MessageResRepository messageResRepository;

    @Autowired
    private ReservationResRepository reservationResRepository;

    @Autowired
    private  TypeOfLodgingRepository typeOfLodgingRepository;

    @Autowired
    private ImageRepository imageRepository;

    static int brojac =0;

    String getUsername(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        com.project.model.User user = (com.project.model.User) session.getAttribute("user");
        return user.getUsername();
    }

    public void brisiSve(){
        try {
        List<LodgingRes> res =  lodgingResRepository.findAll();
            lodgingResRepository.deleteAll();

        }catch (Exception e){
            System.out.print("Ne moze da izbrise Smestajj !!! __ ! ");
        }
        try {
            additionServiceRepository.deleteAll();
        }catch (Exception e){
            System.out.print("Ne moze da izbrise dodatni servisi !!! __ ! ");

        }
        try {
            categoryOfLodgingRepository.deleteAll();
        }catch (Exception e){

        }
        try{
            reservationResRepository.deleteAll();

        }catch (Exception e){

        }
        try {
            messageResRepository.deleteAll();
        }catch (Exception e){

        }
        try {
            typeOfLodgingRepository.deleteAll();
        }catch (Exception e){

        }

        try{
            imageRepository.deleteAll();
        }catch (Exception e){

        }

    }


    @Override
    public void syncWholeDb() {

        if(brojac == 0) {
            brisiSve();
        } else {
            messageResRepository.deleteAll();           // Ako se samo poruke menjaju u zavisnosti od ulogovanog agenta !!!
        }

        List<AdditionalService> listaDodatnihServisa =  additionServiceRepository.findAll();


        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        // ========
        //imageRepository
        // pROVERII !!!!!
        GetImagesRequest getImageRequest = new GetImagesRequest();
        GetImagesResponse slikice = objPort.getImages(getImageRequest);
        List<com.project.ws.Image> images= slikice.getImagesList();


        List<String> listaSlika = new ArrayList<>();

        for(int i=0; i < images.size();i++ ){


            Long idid = Long.valueOf(i+1);
            com.project.model.Image slika = imageRepository.findOne(idid);

            if(slika == null){

                com.project.model.Image image = new com.project.model.Image();
                image.setUrl(images.get(i).getUrl());
                LodgingRes lodgingRes1 = lodgingResRepository.findOne(images.get(i).getId());
                listaSlika.add(image.getUrl());

//                LodgingRes lodging = new LodgingRes();                                              // Ne moze zato sto nemam informaciju o Lodgingu !!!
//                    lodging.setAgent(images.get(i).getLodging().getAgent().getUsername());
//                    lodging.setAddress(images.get(i).getLodging().getAddress());
//                    lodging.setCategory(images.get(i).getLodging().getCategory().getId());
//                    lodging.setCity(images.get(i).getLodging().getCity().getId());
//                    lodging.setDetails(images.get(i).getLodging().getDetails());
//                    lodging.setPersonsNumber( BigInteger.valueOf( images.get(i).getLodging().getPersonsNumber()) );
//                    lodging.setType( images.get(i).getLodging().getType().getId());
//                    lodging.setTitle(images.get(i).getLodging().getTitle());
//
//                image.setLodging(lodging);



               // imageRepository.save(image);

            }else{
             //   imageRepository.save(slika);
                listaSlika.add(slika.getUrl());
            }

        }


        GetLodgingsRequest requestLod = new GetLodgingsRequest();
        requestLod.setLodgings("all");
        GetLodgingsResponse odg = objPort.getLodgings(requestLod);
        List<com.project.ws.LodgingRes> lodgingRes = odg.getLodgingRes();
        List<LodgingRes> res = new ArrayList<>();
        List<LodgingRes> resNiz = new ArrayList<>();


        List<List<String>> nizNiz = new ArrayList<>();


        for(int i =0; i < lodgingRes.size() ; i++){
            List<String> adicioni = new ArrayList<>();
            List<AdditionalService> adicioniPravi = new ArrayList<>();

            LodgingRes lodgingRes1 = new LodgingRes();
            lodgingRes1.setType(lodgingRes.get(i).getType());
            lodgingRes1.setAgent(lodgingRes.get(i).getAgent());
            lodgingRes1.setCategory(lodgingRes.get(i).getCategory());
            lodgingRes1.setAddress(lodgingRes.get(i).getAddress());
            lodgingRes1.setCity(lodgingRes.get(i).getCity());
            lodgingRes1.setDetails(lodgingRes.get(i).getDetails());
//            lodgingRes1.setImages(lodgingRes.get(i).getImages());
            lodgingRes1.setPersonsNumber( lodgingRes.get(i).getPersonsNumber() );
            lodgingRes1.setTitle(lodgingRes.get(i).getTitle());

           // lodgingRes1.setImages(lodgingRes.get(i).getImagesList() );
        //    lodgingRes1.setImages(images1);

            //                if(brojac ==0) {
//                    if (lodgingRes.get(i).getAdditionService().size() > 0) {
//                        List<String> niz = new ArrayList<>();
//                        for (String novi : lodgingRes.get(i).getAdditionService()) {
//                            niz.add(novi);
//                            adicioniPravi.add(new AdditionalService());
//                        }
//                        nizNiz.add(niz);
//                        for (int q = 0; q < niz.size(); q++) {
//                            adicioniPravi.get(q).setName(niz.get(q));
//                            // lodgingRes1.getAdditionService().add(adicioniPravi.get(q));
//                        }
//                        res.add(lodgingRes1);
//                        resNiz.add(lodgingRes1);
//                        //  lodgingRes1.setAdditionService(adicioniPravi);
//                    }
//                    lodgingResRepository.save(lodgingRes1);
//                }else {
                    Long broj= Long.valueOf(i) + 1;

                        LodgingRes lod = lodgingResRepository.findOne(Long.valueOf(broj));
                        if(lod != null) {
                            lodgingResRepository.save(lod);
                        }else{
                            List<String> niz = new ArrayList<>();
                            for (String novi : lodgingRes.get(i).getAdditionService()) {
                                niz.add(novi);
                                adicioniPravi.add(new AdditionalService());
                            }
                            nizNiz.add(niz);
                            for (int q = 0; q < niz.size(); q++) {
                                adicioniPravi.get(q).setName(niz.get(q));
                                // lodgingRes1.getAdditionService().add(adicioniPravi.get(q));
                            }
                            res.add(lodgingRes1);
                            resNiz.add(lodgingRes1);
                            //  lodgingRes1.setAdditionService(adicioniPravi);
                            lodgingResRepository.save(lodgingRes1);

                        }


            List<com.project.model.Image> images1 = new ArrayList<>();
            for( int w=0; w < listaSlika.size(); w ++ ){


                if(  brojac == 0) {

                    com.project.model.Image slikaa = new com.project.model.Image();

                    for (int z = 0; z < lodgingRes.get(i).getImagesList().size(); z++) {


                        if (lodgingRes.get(i).getImagesList().get(z).equals(listaSlika.get(w))) {
                            String url = listaSlika.get(w);
                            //slikaa.setId(Long.valueOf(w+1));
                            slikaa.setUrl(url);
                            slikaa.setLodging(lodgingRes1);
                            images1.add(slikaa);
                            if (images1.size() <= lodgingRes.get(i).getImagesList().size()) {
                                imageRepository.save(slikaa);
                            }
                        }
                    }
                } else {
                        Long njegovId = Long.valueOf(w) + 1;

                        try {
                            com.project.model.Image picture = imageRepository.findOne(njegovId);
                            imageRepository.save(picture);
                        }catch (Exception e){

                            com.project.model.Image slikaa = new com.project.model.Image();

                            for (int z = 0; z < lodgingRes.get(i).getImagesList().size(); z++) {


                                if (lodgingRes.get(i).getImagesList().get(z).equals(listaSlika.get(w))) {
                                    String url = listaSlika.get(w);
                                    //slikaa.setId(Long.valueOf(w+1));
                                    slikaa.setUrl(url);
                                    slikaa.setLodging(lodgingResRepository.findOne(Long.valueOf(i+1)));
                                    images1.add(slikaa);
                                    if (images1.size() <= lodgingRes.get(i).getImagesList().size()) {
                                        imageRepository.save(slikaa);
                                    }

                                }
                            }
                        }

                }

            }



        }
            //    }

          // lodgingRes1.setAdditionService(adicioniPravi);
        //}


        GetAdditionsRequest request12 = new GetAdditionsRequest();
        request12.setTypes("all");
        GetAdditionsResponse response11 = objPort.getAdditions(request12);
        List<com.project.ws.AdditionalService> additionalServices = response11.getTypes();


        for(int i=0;i < additionalServices.size();i++){
            com.project.model.AdditionalService additionalService = new AdditionalService();

            additionalService.setName(additionalServices.get(i).getName());


            if(brojac == 0) {
                List<LodgingRes> samoTi = new ArrayList<>();

                for (int q = 0; q < nizNiz.size(); q++) {
                    //if ( (q+1) ==  Integer.parseInt(resNiz.get(i).getAdditionService().get(q).getName()) ) { //resNiz.get(i).getAdditionService().size() > 0 ||  ) {
                    for (int j = 0; j < nizNiz.get(q).size(); j++) {
                        if (Integer.parseInt(nizNiz.get(q).get(j)) == (i + 1)) {
                            samoTi.add(resNiz.get(q));
                            //     additionalService.getLodgingList().add(resNiz.get(q)); //setLodgingList(resNiz);
                        }
                    }
                }
                additionalService.setLodgingList(samoTi);
                additionServiceRepository.save( additionalService );
            }else{
                Long broj = Long.valueOf(i)+1;
               try {
                   AdditionalService nek = additionServiceRepository.findOne(broj);
                   additionServiceRepository.save(nek);
               }catch (Exception e){
                   List<LodgingRes> samoTi = new ArrayList<>();

                   for (int q = 0; q < nizNiz.size(); q++) {
                       //if ( (q+1) ==  Integer.parseInt(resNiz.get(i).getAdditionService().get(q).getName()) ) { //resNiz.get(i).getAdditionService().size() > 0 ||  ) {
                       for (int j = 0; j < nizNiz.get(q).size(); j++) {
                           if (Integer.parseInt(nizNiz.get(q).get(j)) == (i + 1)) {
                               samoTi.add(resNiz.get(q));
                               //     additionalService.getLodgingList().add(resNiz.get(q)); //setLodgingList(resNiz);
                           }
                       }
                   }
                   additionalService.setLodgingList(samoTi);
                   additionServiceRepository.save( additionalService );
               }
            }


        }


        GetMessagesRequest request = new GetMessagesRequest();
        String username = getUsername();
        request.setResponse(username);
        GetMessagesResponse response = objPort.getMessages(request);
        List<com.project.ws.MessageRes> messageResList = response.getMessageRes();

                 for(int i=0;i < messageResList.size();i++){
                    MessageRes messageRes1 = new MessageRes();

                    try {
                        String stron = messageResList.get(i).getDateSent().toString();
                        messageRes1.setDateSent( stron );
                    }catch (Exception e){

                    }
                     messageRes1.setBody( messageResList.get(i).getBody());
                     messageRes1.setReceiver(messageResList.get(i).getReceiver());
                     messageRes1.setSender(messageResList.get(i).getSender());
                     messageRes1.setTitle(messageResList.get(i).getTitle());
                     Long broj = Long.valueOf(i)+1;
                    try {
                        MessageRes nek = messageResRepository.findOne(broj);
                        messageResRepository.save(nek);
                    }catch (Exception e){
                        messageResRepository.save( messageRes1 );
                    }
                }



        GetLodgingCategoriesRequest requestCategory = new GetLodgingCategoriesRequest();
        requestCategory.setTypes("all");
        GetLodgingCategoriesResponse categoriesResponse= objPort.getLodgingCategories(requestCategory);
        List<com.project.ws.CategoryOfLodging> categoryOfLodgings= categoriesResponse.getTypes();

        for(int i=0;i < categoryOfLodgings.size();i++){
            CategoryOfLodging categoryOfLodging12  = new CategoryOfLodging();

            categoryOfLodging12.setName(categoryOfLodgings.get(i).getName());
            categoryOfLodging12.setLabel(categoryOfLodgings.get(i).getLabel());
            Long broj = Long.valueOf(i)+1;

                CategoryOfLodging ofLodging = categoryOfLodgingRepository.findOne(broj);
                if(ofLodging != null) {
                    categoryOfLodgingRepository.save(ofLodging);
                }else {
                    categoryOfLodgingRepository.save(categoryOfLodging12);
                }

        }

        GetReservationsRequest getReservationsRequest = new GetReservationsRequest();
        getReservationsRequest.setType("all");
        GetReservationsResponse getReservationsResponse = objPort.getReservations(getReservationsRequest);
        List<com.project.ws.Reservation> reservations=  getReservationsResponse.getReservations();

        for(int i=0;i < reservations.size();i++){
             ReservationRes  reservation = new ReservationRes();

             String pocetni = reservations.get(i).getDateStart().toString();
             String krajni = reservations.get(i).getDateEnd().toString();

            reservation.setUser(reservations.get(i).getUser().getUsername());
            reservation.setActive(reservations.get(i).isActive());
            reservation.setVisited(reservations.get(i).isVisited());
            reservation.setDateStart( pocetni);
            reservation.setDateEnd( krajni );

            Long broj = Long.valueOf(i)+1;
            try {
                ReservationRes res1= reservationResRepository.findOne(broj);
                reservationResRepository.save(res1);
            }catch (Exception e){
                reservationResRepository.save(reservation);
            }
        }

        GetLodgingTypesRequest getLodgingTypesRequest= new GetLodgingTypesRequest();
        getLodgingTypesRequest.setTypes("all");
        GetLodgingTypesResponse getLodgingTypesResponse= objPort.getLodgingTypes(getLodgingTypesRequest);
        List<com.project.ws.TypeOfLodging> typeOfLodgings =  getLodgingTypesResponse.getTypes();

        for(int i=0;i < typeOfLodgings.size();i++){
            TypeOfLodging  typeOfLodging= new TypeOfLodging();

            typeOfLodging.setLabel(typeOfLodgings.get(i).getLabel());
            Long broj = Long.valueOf(i)+1;
            try {
                TypeOfLodging typeOfLodging1 = typeOfLodgingRepository.findOne(broj);
                typeOfLodgingRepository.save(typeOfLodging1);
            }catch (Exception e) {
                typeOfLodgingRepository.save(typeOfLodging);
            }
        }

        brojac++;
    }
    @Override
    public void syncReservations() {

    }

    @Override
    public void syncMessages() {

    }

    @Override
    public void syncLodgings() {

    }
}