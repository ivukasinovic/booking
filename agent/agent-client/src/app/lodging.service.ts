import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {Reservation} from './model';

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
}
