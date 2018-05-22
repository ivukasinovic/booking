import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdditionalLodgingComponent } from './additional-lodging.component';

describe('AdditionalLodgingComponent', () => {
  let component: AdditionalLodgingComponent;
  let fixture: ComponentFixture<AdditionalLodgingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdditionalLodgingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdditionalLodgingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
