import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PricePlanNewComponent } from './price-plan-new.component';

describe('PricePlanNewComponent', () => {
  let component: PricePlanNewComponent;
  let fixture: ComponentFixture<PricePlanNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PricePlanNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PricePlanNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
