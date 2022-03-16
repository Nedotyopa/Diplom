import {EventEmitter, Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SocialEventService {

  skipClicked: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() { }

  setClicked(): void {
    console.log("clicked");
    this.skipClicked.next(true);
  }
}
