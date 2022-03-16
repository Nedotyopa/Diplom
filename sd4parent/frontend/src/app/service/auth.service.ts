import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginUser } from "../model/loginUser";
import { TokenStorage } from "../storage/token.storage";
import {Token} from "../model/token";
import { Decode } from "../model/decode";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private token: TokenStorage) {
  }

  attemptAuth(loginUser: LoginUser): Observable<Token> {
    return this.http.post<Token>('token/generate-token', loginUser);
  }

  decodeJwt(token: string): any {
    if(token) {
      let encodedJwt = token.split('.')[1];
      let decodedJwt = window.atob(encodedJwt);
      return JSON.parse(decodedJwt);
    } else {
      return null;
    }

  }

  getUsername(): string {
    let decodeObj: Decode = this.decodeJwt(this.token.getToken());
    if(decodeObj != null)
      return decodeObj.sub;
    else
      return null;
  }

  getRole(): string {
    let decodeObj: Decode = this.decodeJwt(this.token.getToken());
    if(decodeObj != null)
      return decodeObj.scopes.split(",")[0];
    else
      return null;
  }

  getBan(): boolean {
    let decodeObj: Decode = this.decodeJwt(this.token.getToken());
    if(decodeObj != null)
      return JSON.parse(decodeObj.scopes.split(",")[1]);
    else
      return null;
  }

}
