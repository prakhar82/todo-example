import { CsManagedMultiSelectModule } from './cs-managed-multi-select.module';

describe('CsManagedMultiSelectModule', () => {
  let csManagedMultiSelectModule: CsManagedMultiSelectModule;

  beforeEach(() => {
    csManagedMultiSelectModule = new CsManagedMultiSelectModule();
  });

  it('should create an instance', () => {
    expect(csManagedMultiSelectModule).toBeTruthy();
  });
});
