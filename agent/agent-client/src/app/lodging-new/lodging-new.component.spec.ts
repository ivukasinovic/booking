import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LodgingNewComponent } from './lodging-new.component';

describe('LodgingNewComponent', () => {
  let component: LodgingNewComponent;
  let fixture: ComponentFixture<LodgingNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LodgingNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LodgingNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
