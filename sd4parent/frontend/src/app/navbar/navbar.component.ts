import {Component, Inject, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import { UserService } from '../service/user.service';
import { Subscription } from 'rxjs/internal/Subscription';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthService } from "../service/auth.service";
import { Token } from "../model/token";
import { TokenStorage } from "../storage/token.storage";
import { LoginUser } from "../model/loginUser";
import {Router} from "@angular/router";
import { Decode } from "../model/decode";
import {User} from "../model/user";
import { PasswordService } from "../service/password.service";
import { SocialService } from "../service/social.service";
import {URLModel} from "../model/urlModel";
import { LoginEventService } from "../service/login-event.service";
import { SocialEventService } from "../service/social-event.service";
import { SubscriptionService } from "../service/subscription.service";
import { ChargeEventService } from "../service/charge-event.service";

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {

  public user: Decode;
  public resetEmail: string;
  public role: string;
  public ban: boolean = false;
  public loginUser: LoginUser = new LoginUser();
  private subscriptions: Subscription[] = [];
  public modalRef: BsModalRef;
  public newUser: User = new User();
  private address: URLModel;
  public emailResult = true;
  public access: boolean = true;
  public emailAccess: boolean = true;
  public registerAccess: boolean = true;
  public exceptionMessage = "";

  constructor(private userService: UserService, private modalService: BsModalService,
              private router: Router, private loadingService: NgxSpinnerService,
              private authService: AuthService, private tokenStorage: TokenStorage,
              private passwordService: PasswordService, private socialService: SocialService,
              private loginEventService: LoginEventService, private socialEventService: SocialEventService,
              private subscriptionService: SubscriptionService, private chargeEventService: ChargeEventService) { }

  ngOnInit() {

    if(this.tokenStorage.getToken()) {
      this.user = this.authService.decodeJwt(this.tokenStorage.getToken());
      this.role = this.user.scopes.split(",")[0];
    }
    this.socialEventService.skipClicked.subscribe( value => {
      if(value == true) {
        if(this.tokenStorage.getToken()) {
          this.user = this.authService.decodeJwt(this.tokenStorage.getToken());
          this.role = this.user.scopes.split(",")[0];
        }
      }
    })

  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public _closeModal(): void {
    // this.loginUser.login = null;
    // this.loginUser.password = null;
    // this.newUser.login = null;
    // this.newUser.password = null;
    // this.newUser.email = null;
    this.ban = false;
    this.modalRef.hide();
  }

  public _openModal(template: TemplateRef<any>) {
    this.access = true;
    this.modalRef = this.modalService.show(template);
  }

  public authorizate(): void {
    this.loadingService.show();
    this.ban = false;
    this.access = true;
    this.emailAccess = true;
    this.subscriptions.push(this.authService.attemptAuth(this.loginUser).subscribe(authToken => {
      if(authToken == null) {
        console.log("Unauthorizate");
        this.access = false;
      }
      else {
        let token: Token = authToken as Token;
        this.tokenStorage.saveToken(token.token);

        this.user = this.authService.decodeJwt(token.token);

        console.log(this.user);
        this.role = this.user.scopes.split(",")[0];
        this.ban = JSON.parse(this.user.scopes.split(",")[1]);
        console.log(this.ban);
        if(this.ban)
          this.signOut();
        else
          this._closeModal();
        this.access = true;
      }
    }));

    this.userService.checkUser(this.loginUser.login).subscribe(() => {

    });
    this.loadingService.hide();

  }

  public signUp(): void {
    this.loadingService.show();
    this.registerAccess = true;
    this.subscriptions.push(this.userService.saveUser(this.newUser).subscribe(authToken => {
      if(authToken.token != "This login already exists" && authToken.token != "This email already exists") {
        let token: Token = authToken as Token;
        this.tokenStorage.saveToken(token.token);

        this.user = this.authService.decodeJwt(token.token);

        console.log(this.user);
        this.role = this.user.scopes.split(",")[0];
        this.loadingService.hide();
        this._closeModal();
        console.log(this.newUser);
      } else {
        this.registerAccess = false;
        this.exceptionMessage = authToken.token;
      }

    }));
    this.loadingService.hide();
  }

  public signOut(): void {
    this.tokenStorage.signOut();
    this.user = undefined;
    this.loginEventService.setClicked();
  }

  public sentEmail(): void {
    this.loadingService.show();
    this.subscriptions.push(this.passwordService.forgotPassword(this.resetEmail).subscribe(result => {
      this.emailResult = result;
      if(!result) {
        this.resetEmail = null;
        this.emailAccess = false;
      }
      else {
        this.emailAccess = false;
        this._closeModal();
      }
      this.loadingService.hide();

    }))
  }

  public facebookAuth(): void {
    this.subscriptions.push(this.socialService.createFacebookAuthorization().subscribe(addr => {
      this.address = addr as URLModel;
      console.log(this.address.url);
      window.open(this.address.url, "_self");
    }))
  }

  public googleAuth(): void {
    this.subscriptions.push(this.socialService.createGoogleAuthorization().subscribe(addr => {
      this.address = addr as URLModel;
      console.log(this.address.url);
      window.open(this.address.url, "_self");
    }))
  }

  public charge(): void {
    this.loadingService.show();
    this.subscriptions.push(this.subscriptionService.chargeMoney().subscribe(() => {
      this.loadingService.hide();
      this.chargeEventService.setClicked();
    }))
  }
}
