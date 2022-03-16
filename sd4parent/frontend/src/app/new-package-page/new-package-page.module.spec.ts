import { NewPackagePageModule } from './new-package-page.module';

describe('NewPackagePageModule', () => {
  let newPackagePageModule: NewPackagePageModule;

  beforeEach(() => {
    newPackagePageModule = new NewPackagePageModule();
  });

  it('should create an instance', () => {
    expect(newPackagePageModule).toBeTruthy();
  });
});
