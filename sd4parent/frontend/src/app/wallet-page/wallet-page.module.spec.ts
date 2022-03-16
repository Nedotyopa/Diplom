import { WalletPageModule } from './wallet-page.module';

describe('WalletPageModule', () => {
  let walletPageModule: WalletPageModule;

  beforeEach(() => {
    walletPageModule = new WalletPageModule();
  });

  it('should create an instance', () => {
    expect(walletPageModule).toBeTruthy();
  });
});
