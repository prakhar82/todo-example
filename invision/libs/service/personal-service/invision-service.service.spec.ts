import { TestBed } from '@angular/core/testing';

import { InvisionServiceService } from './invision-service.service';

describe('InvisionServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvisionServiceService = TestBed.get(InvisionServiceService);
    expect(service).toBeTruthy();
  });
});
