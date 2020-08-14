import { CsManagedFilterModule } from './cs-managed-filter.module';

describe('CsManagedFilterModule', () => {
  let csManagedFilterModule: CsManagedFilterModule;

  beforeEach(() => {
    csManagedFilterModule = new CsManagedFilterModule();
  });

  it('should create an instance', () => {
    expect(csManagedFilterModule).toBeTruthy();
  });
});
