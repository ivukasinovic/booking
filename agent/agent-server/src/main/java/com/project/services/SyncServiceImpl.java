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

import java.lang.reflect.Array;
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

    static int brojac =0;

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

    }


    @Override
    public void syncWholeDb() {

        if(brojac == 0) {
            brisiSve();
        }
      //  brojac++;

        List<AdditionalService> listaDodatnihServisa =  additionServiceRepository.findAll();



        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();

        // ========

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
            lodgingRes1.setImage(lodgingRes.get(i).getImage());
            lodgingRes1.setPersonsNumber( lodgingRes.get(i).getPersonsNumber() );
            lodgingRes1.setTitle(lodgingRes.get(i).getTitle());
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
        request.setResponse("all");
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