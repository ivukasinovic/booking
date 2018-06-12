import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Message, Rating, Reservation} from '../model';

@Injectable()
export class ReserveService {

  constructor(private http: HttpClient) { }

  reserve(res: Reservation, idL: string) {
    return this.http.post('api/reservations/' + idL, res);
  }

  cancel(id: string) {
    return this.http.get('api/reservations/cancel/' + id);
  }

  sendMsg(id: string, msg: Message) {
    return this.http.post('api/messages/' + id, msg);
  }

  getLodging(id: any) {
    return this.http.get('api/lodging/' + id);
  }

  getVisited() {
    return this.http.get('api/users/visited');
  }

  postComment(com: Comment, id: string) {
    return this.http.post('api/comment/' + id, com);
  }

  postRating(rating: Rating, id: string) {
    return this.http.post('api/ratings/' + id, rating);
  }

  checkAvaliability(id: number, dateStart: Date, dateEnd: Date) {
    return this.http.get('/api/reservations/check/' + id + '/' + dateStart + '/' + dateEnd);
  }
}
