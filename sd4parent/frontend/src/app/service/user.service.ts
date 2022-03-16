import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http"
import { Observable } from "rxjs"
import { User } from "../model/user"
import {Token} from "../model/token";
import { Debt } from "../model/debt";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUser(userId: string): Observable<User> {
    return this.http.get<User>("api/u/" + userId);
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>("/api/u");
  }

  saveUser(user: User): Observable<Token> {
    return this.http.post<Token>('/api/u/login', user)
  }

  deleteUser(userId: string): Observable<void> {
    return this.http.delete<void>('api/u/' + userId)
  }

  getUserByLogin(login: string): Observable<User> {
    return this.http.get<User>("api/u/?login=" + login);
  }

  banUser(userId: string): Observable<void> {
    return this.http.get<void>("api/u/ban?id=" + userId);
  }

  checkUser(login: string): Observable<void> {
    return this.http.get<void>("api/u/check?login=" + login);
  }

  updateDebt(debt: Debt): Observable<void> {
    return this.http.post<void>("api/u/update-debt", debt);
  }
}
