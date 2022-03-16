import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginEventService {

  skipClicked: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() { }

  setClicked(): void {
    console.log("clicked");
    this.skipClicked.next(true);
  }

}
