import { TestBed, inject } from '@angular/core/testing';

import { SocialEventService } from './social-event.service';

describe('SocialEventService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SocialEventService]
    });
  });

  it('should be created', inject([SocialEventService], (service: SocialEventService) => {
    expect(service).toBeTruthy();
  }));
});
