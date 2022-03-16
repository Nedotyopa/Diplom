import { DescriptionPageModule } from './description-page.module';

describe('DescriptionPageModule', () => {
  let descriptionPageModule: DescriptionPageModule;

  beforeEach(() => {
    descriptionPageModule = new DescriptionPageModule();
  });

  it('should create an instance', () => {
    expect(descriptionPageModule).toBeTruthy();
  });
});
