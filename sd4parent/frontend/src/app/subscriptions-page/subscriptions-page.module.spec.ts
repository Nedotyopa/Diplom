import { SubscriptionsPageModule } from './subscriptions-page.module';

describe('SubscriptionsPageModule', () => {
  let subscriptionsPageModule: SubscriptionsPageModule;

  beforeEach(() => {
    subscriptionsPageModule = new SubscriptionsPageModule();
  });

  it('should create an instance', () => {
    expect(subscriptionsPageModule).toBeTruthy();
  });
});
