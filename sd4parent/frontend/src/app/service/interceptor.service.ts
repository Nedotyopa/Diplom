import {Injectable} from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
  HttpResponse
} from '@angular/common/http';
import {Observable, throwError} from "rxjs";
import { TokenStorage } from "../storage/token.storage";
import {catchError, tap} from "rxjs/operators";
import {Router} from "@angular/router";
import { AuthService } from "./auth.service";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router, private authService: AuthService) {
  }


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Interceptor works");
    let authReq = req;
    if (this.token.getToken() != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
    }
    return next.handle(authReq).pipe(tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          console.log("Interceptor log response", event);
        }
      }, (err: any) => {
        console.log('req url :: ' + req.url);
        console.error("Interceptor log error", err);
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.router.navigate(['']);
          }
        }
      }
    ));
  }
}
