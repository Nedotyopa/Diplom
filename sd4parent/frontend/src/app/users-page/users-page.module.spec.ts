import { UsersPageModule } from './users-page.module';

describe('UsersPageModule', () => {
  let usersPageModule: UsersPageModule;

  beforeEach(() => {
    usersPageModule = new UsersPageModule();
  });

  it('should create an instance', () => {
    expect(usersPageModule).toBeTruthy();
  });
});
