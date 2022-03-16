import { PackageDescriptionPageModule } from './package-description-page.module';

describe('PackageDescriptionPageModule', () => {
  let packageDescriptionPageModule: PackageDescriptionPageModule;

  beforeEach(() => {
    packageDescriptionPageModule = new PackageDescriptionPageModule();
  });

  it('should create an instance', () => {
    expect(packageDescriptionPageModule).toBeTruthy();
  });
});
