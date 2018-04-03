import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SelectKeyStoreComponent} from './select-key-store.component';

describe('SelectKeyStoreComponent', () => {
  let component: SelectKeyStoreComponent;
  let fixture: ComponentFixture<SelectKeyStoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SelectKeyStoreComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectKeyStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
