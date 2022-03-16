import { GetSocialModule } from './get-social.module';

describe('GetSocialModule', () => {
  let getSocialModule: GetSocialModule;

  beforeEach(() => {
    getSocialModule = new GetSocialModule();
  });

  it('should create an instance', () => {
    expect(getSocialModule).toBeTruthy();
  });
});
