import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpParams} from '@angular/common/http';
import {AditionalServices, Lodging, LodgingRes, Message, Reservation} from './model';

@Injectable()
export class LodgingService {

  constructor(private router: Router, private http: HttpClient) { }
  lodgingRes : LodgingRes;
  getReservations() {
    return this.http.get('api/reservations');
  }
  setCompleted(id: number) {
    const adr = '/api/reservations/completed/' + id;
    return this.http.get(adr);
  }
  getCities() {
    return this.http.get('api/lodgings/cities');
  }
  getCategories() {
    return this.http.get('api/lodgings/categories');
  }
  getTypes() {
    return this.http.get('api/lodgings/types');
  }
  createLodging(lodging: Lodging) {
    this.lodgingRes = new LodgingRes();
    this.lodgingRes.lodging = lodging;
    return this.http.post('api/lodgings', this.lodgingRes);
  }

  getAdditionalServices() {
    return this.http.get('api/lodgings/additions');
  }
  getMessages() {
    return this.http.get('api/reservations/messages');
  }
  reply(message: Message) {
    return this.http.post('api/reservations/reply', message);
  }
  // reserve(reservation: Reservation) {
  //   return this.http.post('api/reservations', reservation);
  // }
  reserve(reservation: Reservation) {
    let params = new HttpParams();
    params = params.append('id', reservation.id.toString())
    params = params.append('dateStart', reservation.dateStart.toString())
    params = params.append('dateEnd', reservation.dateEnd.toString())
    return this.http.get('api/reservations/occupancy', {params: params });
  }
}
