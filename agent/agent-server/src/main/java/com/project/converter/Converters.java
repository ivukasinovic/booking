package com.project.converter;

import com.project.model.AdditionalService;
import com.project.model.MessageRes;
import com.project.model.ReservationRes;
import com.project.repository.AdditionServiceRepository;
import com.project.repository.LodgingResRepository;
import com.project.repository.MessageResRepository;
import com.project.repository.ReservationResRepository;
import com.project.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan V. on 20-Jun-18
 */
@Service
public class Converters {

    @Autowired
    private LodgingResRepository lodgingResRepository;

    @Autowired
    private AdditionServiceRepository additionServiceRepository;

    @Autowired
    private MessageResRepository messageResRepository;

    @Autowired
    private ReservationResRepository reservationResRepository;

    @Autowired
    private RepositoryService repositoryService;

     public com.project.model.LodgingRes convertLodging(com.project.ws.LodgingRes lodgingRes){

         com.project.model.LodgingRes lodgingRes1 = new com.project.model.LodgingRes();
         lodgingRes1.setType(lodgingRes.getType());
         lodgingRes1.setAgent(lodgingRes.getAgent());
         lodgingRes1.setCategory(lodgingRes.getCategory());
         lodgingRes1.setAddress(lodgingRes.getAddress());
         lodgingRes1.setCity(lodgingRes.getCity());
         lodgingRes1.setDetails(lodgingRes.getDetails());
         lodgingRes1.setImage(lodgingRes.getImage());
         lodgingRes1.setPersonsNumber( lodgingRes.getPersonsNumber() );
         lodgingRes1.setTitle(lodgingRes.getTitle());

         if(lodgingRes.getAdditionService().size() > 0) {
             List<AdditionalService> niz = new ArrayList<>();
             for (String novi : lodgingRes.getAdditionService()) {
                 Long id = Long.valueOf(novi);
                 additionServiceRepository.findOne(id);
             }
             lodgingRes1.setAdditionService(niz);
         }
        return lodgingRes1;
    }

    public com.project.model.MessageRes convertMessage(com.project.ws.MessageRes messageRes){

        MessageRes messageRes1 = new MessageRes();

        try {
            String stron = messageRes.getDateSent().toString();
            messageRes1.setDateSent( stron );
        }catch (Exception e){
            System.out.print("Nije dobro namapirano datum!!!");
        }

        messageRes1.setBody( messageRes.getBody());
        messageRes1.setReceiver(messageRes.getReceiver());
        messageRes1.setSender(messageRes.getSender());
        messageRes1.setTitle(messageRes.getTitle());

      return  messageRes1;
     }



    public com.project.model.ReservationRes updateLodging(Long id,String start,String end){

        //com.project.model.ReservationRes lodgingRes1 =  reservationResRepository.findOne(id); // new com.project.model.LodgingRes();
        com.project.model.ReservationRes lodgingRes1 = new ReservationRes();

        lodgingRes1.setUser(lodgingRes1.getUser());
        // lodgingRes1.setVisited(lodgingRes1.getUser());
        // lodgingRes1.set
        lodgingRes1.setDateStart( start);
        lodgingRes1.setDateEnd( end );


    return  lodgingRes1;
    }

}
