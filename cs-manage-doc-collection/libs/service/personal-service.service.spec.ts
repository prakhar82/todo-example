import { TestBed } from '@angular/core/testing';

import { PersonalServiceService } from './personal-service.service';

describe('PersonalServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PersonalServiceService = TestBed.get(PersonalServiceService);
    expect(service).toBeTruthy();
  });
});
