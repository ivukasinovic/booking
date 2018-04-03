import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewKeyStoreComponent} from './new-key-store.component';

describe('NewKeyStoreComponent', () => {
  let component: NewKeyStoreComponent;
  let fixture: ComponentFixture<NewKeyStoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [NewKeyStoreComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewKeyStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
