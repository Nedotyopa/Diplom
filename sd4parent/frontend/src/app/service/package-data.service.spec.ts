import { TestBed, inject } from '@angular/core/testing';

import { PackageDataService } from './package-data.service';

describe('PackageDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PackageDataService]
    });
  });

  it('should be created', inject([PackageDataService], (service: PackageDataService) => {
    expect(service).toBeTruthy();
  }));
});
