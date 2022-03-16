import { NewPostPageModule } from './new-post-page.module';

describe('NewPostPageModule', () => {
  let newPostPageModule: NewPostPageModule;

  beforeEach(() => {
    newPostPageModule = new NewPostPageModule();
  });

  it('should create an instance', () => {
    expect(newPostPageModule).toBeTruthy();
  });
});
