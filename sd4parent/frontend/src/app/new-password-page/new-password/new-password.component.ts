import {Component, OnDestroy, OnInit} from '@angular/core';
import { Password } from "../../model/password";
import { PasswordService } from "../../service/password.service";
import { Token } from "../../model/token";
import { TokenStorage } from "../../storage/token.storage";

import { Subscription } from "rxjs/internal/Subscription"
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthService } from "../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  public newPassword: Password = new Password();
  public repeatPass: string;

  constructor(private passwordService: PasswordService, private loadingService: NgxSpinnerService,
              private authService: AuthService, private router: Router, private tokenStorage: TokenStorage) { }

  ngOnInit() {
     this.getUser();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  private getUser(): void {
    this.loadingService.show();
    this.subscriptions.push(this.passwordService.getResetUser().subscribe(authToken => {
      if(authToken == null)
        this.router.navigate(['']);
      else {
        let token: Token = authToken as Token;
        this.tokenStorage.saveToken(token.token);
      }
      this.loadingService.hide();
    }))
  }

  public updatePassword(): void {
    this.newPassword.login = this.authService.getUsername();

    this.loadingService.show();
    this.subscriptions.push(this.passwordService.updatePassword(this.newPassword).subscribe(() => {
      this.loadingService.hide();
    }));
    this.tokenStorage.signOut();
    this.router.navigate(['']);
  }

}
