import {Wallet} from "./wallet";

export class Transaction {

  id: string;
  wallet: Wallet;
  action: string;
  amount: number;
  date: string;
  title: string;

}
