import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilebusinessComponent } from './profilebusiness.component';

describe('ProfilebusinessComponent', () => {
  let component: ProfilebusinessComponent;
  let fixture: ComponentFixture<ProfilebusinessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfilebusinessComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProfilebusinessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
