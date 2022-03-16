import {Component, OnDestroy, OnInit} from '@angular/core';
import { Router } from "@angular/router";
import { Subscription } from "rxjs";

import { Token } from "../model/token";
import { TokenStorage } from "../storage/token.storage";
import { SocialService } from "../service/social.service";
import { SocialEventService } from "../service/social-event.service";

@Component({
  selector: 'app-get-social',
  templateUrl: './get-social.component.html',
  styleUrls: ['./get-social.component.css']
})
export class GetSocialComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];

  constructor(private tokenStorage: TokenStorage, private router: Router,
              private socialService: SocialService, private socialEvent: SocialEventService) { }

  ngOnInit() {
    this.getUser();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  private getUser(): void {
    this.subscriptions.push(this.socialService.getSocialUser().subscribe(authToken => {
      if(authToken == null)
        this.router.navigate(['']);
      else {
        let token: Token = authToken as Token;
        this.tokenStorage.saveToken(token.token);
        this.socialEvent.setClicked();
        this.router.navigate(['']);
      }
    }))
  }

}
