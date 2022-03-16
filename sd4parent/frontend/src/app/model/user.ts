import {Wallet} from "./wallet";

export class User {
  id: string;
  login: string;
  password: string;
  role: string;
  email: string;
  lastDateLogin: string;
  wallet: Wallet;
  debt: number;
  ban: boolean;
  count: number;

}

