import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellCheckoutComponent } from './sell-checkout.component';

describe('SellCheckoutComponent', () => {
  let component: SellCheckoutComponent;
  let fixture: ComponentFixture<SellCheckoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellCheckoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
