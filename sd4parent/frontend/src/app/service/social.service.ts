import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http"
import { Observable } from "rxjs"
import { URLModel } from "../model/urlModel";
import {Token} from "../model/token";

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  constructor(private http: HttpClient) { }

  createFacebookAuthorization(): Observable<URLModel> {
    return this.http.get<URLModel>("/social/createFacebookAuthorization");
  }

  createGoogleAuthorization(): Observable<URLModel> {
    return this.http.get<URLModel>("/social/createGoogleAuthorization");
  }

  getSocialUser(): Observable<Token> {
    return this.http.get<Token>("/social/social-auth");
  }
}
