import { TestBed, inject } from '@angular/core/testing';

import { LoginEventService } from './login-event.service';

describe('LoginEventService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoginEventService]
    });
  });

  it('should be created', inject([LoginEventService], (service: LoginEventService) => {
    expect(service).toBeTruthy();
  }));
});
