import { Component, OnInit } from '@angular/core';
import {ReserveService} from '../services/reserve.service';
import {Image, Lodging, Rating} from '../model';
import {CarouselModule} from 'ngx-bootstrap/carousel';

@Component({
  selector: 'app-rate-and-comment',
  templateUrl: './rate-and-comment.component.html',
  styleUrls: ['./rate-and-comment.component.css']
})
export class RateAndCommentComponent implements OnInit {

  lodgins: Lodging[] = [];
  com: Comment;
  rating: Rating;
  collapsed: number;

  constructor(private reserveService: ReserveService) {
    this.com = new Comment();
    this.com.text = '';

    this.rating = new Rating();
    this.rating.star = 1;

    this.collapsed = -1;
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
    this.collapsed = -1;
    this.rating = new Rating();
    this.rating.star = 1;
    this.com = new Comment();
  }

  setCollapse( id: number) {
   this.collapsed = id;
  }

  isCollapsed(id: number) {
    return this.collapsed === id;
  }
}
