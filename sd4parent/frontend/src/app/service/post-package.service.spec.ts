import { TestBed, inject } from '@angular/core/testing';

import { PostPackageService } from './post-package.service';

describe('PostPackageService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PostPackageService]
    });
  });

  it('should be created', inject([PostPackageService], (service: PostPackageService) => {
    expect(service).toBeTruthy();
  }));
});
