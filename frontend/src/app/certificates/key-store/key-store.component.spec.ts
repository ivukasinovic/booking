import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {KeyStoreComponent} from './key-store.component';

describe('KeyStoreComponent', () => {
  let component: KeyStoreComponent;
  let fixture: ComponentFixture<KeyStoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [KeyStoreComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KeyStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
