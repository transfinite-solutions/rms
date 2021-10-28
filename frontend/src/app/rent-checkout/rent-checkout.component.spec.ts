import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentCheckoutComponent } from './rent-checkout.component';

describe('RentCheckoutComponent', () => {
  let component: RentCheckoutComponent;
  let fixture: ComponentFixture<RentCheckoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentCheckoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
