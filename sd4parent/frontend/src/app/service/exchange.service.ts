import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  public result: any;

  constructor(private http: Http) { }

}
