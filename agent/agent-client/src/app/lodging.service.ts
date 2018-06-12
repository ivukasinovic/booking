import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {AditionalServices, Lodging, Message, Reservation} from './model';

@Injectable()
export class LodgingService {

  constructor(private router: Router, private http: HttpClient) { }

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
    return this.http.post('api/lodgings', lodging);
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
}
