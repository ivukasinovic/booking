import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RateAndCommentComponent } from './rate-and-comment.component';

describe('RateAndCommentComponent', () => {
  let component: RateAndCommentComponent;
  let fixture: ComponentFixture<RateAndCommentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RateAndCommentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RateAndCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
