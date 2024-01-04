import { TestBed } from '@angular/core/testing';

import { CustomcookieService } from './customcookie.service';

describe('CustomcookieService', () => {
  let service: CustomcookieService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomcookieService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
