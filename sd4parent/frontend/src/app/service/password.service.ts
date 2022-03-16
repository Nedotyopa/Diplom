import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http"
import { Observable } from "rxjs"
import {Token} from "../model/token";
import { Password } from "../model/password";

@Injectable({
  providedIn: 'root'
})
export class PasswordService {

  constructor(private http: HttpClient) {}

  forgotPassword(email: string): Observable<boolean> {
    return this.http.get<boolean>("/api/forgot?email=" + email);
  }

  getResetUser(): Observable<Token> {
    return this.http.get<Token>("/api/reset-user");
  }

  updatePassword(pass: Password): Observable<void> {
    return this.http.post<void>("/api/update-password", pass);
  }
}
