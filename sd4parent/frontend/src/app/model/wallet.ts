import {User} from "./user";

export class Wallet {
  id: string;
  money: number;
  owner: User;

  static cloneBase(wallet: Wallet): Wallet {
    let clonedWallet: Wallet = new Wallet();
    clonedWallet.id = wallet.id;
    clonedWallet.money = wallet.money;
    clonedWallet.owner = wallet.owner;
    return clonedWallet;
  }

}
