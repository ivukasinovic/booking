import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TypeLodgingComponent} from './type-lodging.component';

describe('TypeLodgingComponent', () => {
  let component: TypeLodgingComponent;
  let fixture: ComponentFixture<TypeLodgingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TypeLodgingComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeLodgingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
