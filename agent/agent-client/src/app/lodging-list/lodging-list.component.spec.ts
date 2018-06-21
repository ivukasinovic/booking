import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LodgingListComponent } from './lodging-list.component';

describe('LodgingListComponent', () => {
  let component: LodgingListComponent;
  let fixture: ComponentFixture<LodgingListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LodgingListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LodgingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
