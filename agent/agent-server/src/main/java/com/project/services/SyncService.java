package com.project.services;

/**
 * Created by Ivan V. on 18-Jun-18
 */

public interface SyncService {

    void syncWholeDb();

    void syncReservations();

    void syncMessages();

    void syncLodgings();
}
