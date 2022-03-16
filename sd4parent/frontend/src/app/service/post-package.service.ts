import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PostPackageService {

  private show = new BehaviorSubject("posts");

  constructor() { }

  public setShow(type: string) {
    this.show.next(type);
  }

  public getShow() {
    return this.show.asObservable();;
  }
}
