import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BussinessregistrationComponent } from './bussinessregistration.component';

describe('BussinessregistrationComponent', () => {
  let component: BussinessregistrationComponent;
  let fixture: ComponentFixture<BussinessregistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BussinessregistrationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BussinessregistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
