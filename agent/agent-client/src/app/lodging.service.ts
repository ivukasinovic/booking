import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class LodgingService {

  constructor(private router: Router, private http: HttpClient) { }

  getReservations() {
    return this.http.get('api/reservations');
  }
}
