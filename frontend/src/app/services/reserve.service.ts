import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Reservation} from '../model';

@Injectable()
export class ReserveService {

  constructor(private http: HttpClient) { }

  reserve(res: Reservation, idL: string) {
    return this.http.post('api/reservations/' + idL, res);
  }


  cancel(id: string) {
    return this.http.get('api/reservations/cancel/' + id);
  }


}
