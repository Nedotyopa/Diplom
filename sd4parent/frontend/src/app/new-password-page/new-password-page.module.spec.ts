import { NewPasswordPageModule } from './new-password-page.module';

describe('NewPasswordPageModule', () => {
  let newPasswordPageModule: NewPasswordPageModule;

  beforeEach(() => {
    newPasswordPageModule = new NewPasswordPageModule();
  });

  it('should create an instance', () => {
    expect(newPasswordPageModule).toBeTruthy();
  });
});
