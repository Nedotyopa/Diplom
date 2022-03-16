import { Injectable } from '@angular/core';
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AdminUserService {

  private login = new BehaviorSubject(null);

  constructor() { }

  public putLogin(acc: string) {
    this.login.next(acc);
  }

  public getLogin() {
    let currentLogin = this.login.asObservable();
    this.login = new BehaviorSubject(null);
    return currentLogin;
  }

}
