import {Component, OnDestroy, OnInit} from '@angular/core';
import { LoginEventService } from "../../service/login-event.service";
import { AuthService } from "../../service/auth.service";
import {Router} from "@angular/router";
import { Subscription } from "rxjs/internal/Subscription"
import { NgxSpinnerService } from 'ngx-spinner';

import { Package } from "../../model/package";
import { PackageService } from "../../service/package.service";
import {Post} from "../../model/post";
import { PackageDataService } from "../../service/package-data.service";

@Component({
  selector: 'app-new-package',
  templateUrl: './new-package.component.html',
  styleUrls: ['./new-package.component.css']
})
export class NewPackageComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  public newPackage: Package = new Package();
  private posts: Post[];

  constructor(private authService: AuthService, private router: Router,
              private loginEventService: LoginEventService, private packageService: PackageService,
              private loadingService: NgxSpinnerService, private packageDataService: PackageDataService) { }

  ngOnInit() {
    this.checkUsername();
    this.loginEventService.skipClicked.subscribe( value => {
      if(value == true) {
        this.router.navigate(['']);
      }
    })
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe())
  }

  private checkUsername(): void {
    if(this.authService.getUsername() == null)
      this.router.navigate(['']);
    else if(this.authService.getRole() != "ADMIN")
      this.router.navigate(['']);
  }

  public addPackage(): void {
    this.loadingService.show();
    console.log("Posts: " + this.posts);
    this.newPackage.posts = this.packageDataService.getPosts();;
    console.log("Posts: " + this.newPackage.posts);
    this.subscriptions.push(this.packageService.savePackage(this.newPackage).subscribe(() => {
      this.loadingService.hide();
    }))
  }

  public disablePostButton(): boolean {

    if(this.newPackage.title == null || this.newPackage.description == null || this.newPackage.discount == null || this.packageDataService.getCount() < 2) {
      return true;
    }
    return false;
  }

}
