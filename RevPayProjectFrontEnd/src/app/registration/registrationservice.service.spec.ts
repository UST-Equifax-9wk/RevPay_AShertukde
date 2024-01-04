import { TestBed } from '@angular/core/testing';

import { RegistrationService } from './registration.service';

describe('RegistrationserviceService', () => {
  let service: RegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
