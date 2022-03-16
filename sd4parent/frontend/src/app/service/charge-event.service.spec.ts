import { TestBed, inject } from '@angular/core/testing';

import { ChargeEventService } from './charge-event.service';

describe('ChargeEventService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ChargeEventService]
    });
  });

  it('should be created', inject([ChargeEventService], (service: ChargeEventService) => {
    expect(service).toBeTruthy();
  }));
});
