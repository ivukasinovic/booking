import { Component, OnInit } from '@angular/core';
import {ReserveService} from '../services/reserve.service';
import {Lodging, Rating} from '../model';

@Component({
  selector: 'app-rate-and-comment',
  templateUrl: './rate-and-comment.component.html',
  styleUrls: ['./rate-and-comment.component.css']
})
export class RateAndCommentComponent implements OnInit {

  lodgins: Lodging[] = [];
  com: Comment;
  rating: Rating;

  constructor(private reserveService: ReserveService) {
    this.com = new Comment();
    this.rating = new Rating();
  }

  ngOnInit() {
    this.reserveService.getVisited().subscribe(
      (response: Lodging[]) => {
        this.lodgins = response;
      });
  }

  comment(id: string) {
    this.reserveService.postComment(this.com, id).subscribe();
    this.reserveService.postRating(this.rating, id).subscribe();
  }

}
