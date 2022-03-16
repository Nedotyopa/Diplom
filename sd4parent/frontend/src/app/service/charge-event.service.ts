import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChargeEventService {

  skipClicked: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() { }

  setClicked(): void {
    console.log("clicked");
    this.skipClicked.next(true);
  }
}
