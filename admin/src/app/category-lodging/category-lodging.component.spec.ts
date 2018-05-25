import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CategoryLodgingComponent} from './category-lodging.component';

describe('CategoryLodgingComponent', () => {
  let component: CategoryLodgingComponent;
  let fixture: ComponentFixture<CategoryLodgingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CategoryLodgingComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryLodgingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
