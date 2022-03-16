import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import { NgxSpinnerService } from 'ngx-spinner';
import { Router } from "@angular/router";

import { Package } from "../../model/package";
import { PackageService } from "../../service/package.service";
import { AuthService } from "../../service/auth.service";
import { TokenStorage } from "../../storage/token.storage";

@Component({
  selector: 'app-package-description',
  templateUrl: './package-description.component.html',
  styleUrls: ['./package-description.component.css']
})
export class PackageDescriptionComponent implements OnInit, OnDestroy {

  public pack: Package;
  private subscriptions: Subscription[] = [];
  public packageId: string;

  constructor(private packageService: PackageService, private route: ActivatedRoute, private tokenStorage: TokenStorage,
              private loadingService: NgxSpinnerService, private authService: AuthService,
              private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.packageId = params.get('id');
    });
    this.loadPackage(this.packageId);
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  private loadPackage(packageId: string): void {
    this.loadingService.show();
    this.subscriptions.push(this.packageService.getPackage(packageId).subscribe(bundle => {
      this.pack = bundle as Package;
      console.log("Package: " + this.pack);
      this.loadingService.hide();
    }))
  }

  public deletePackage(packageId: string): void {
    this.loadingService.show();
    this.subscriptions.push(this.packageService.deletePackage(packageId).subscribe(() => {
    }))
  }

  public getRole(): string {
    if(this.tokenStorage.getToken() != null)
      return this.authService.getRole();
  }

  public _shortDescription(description: string): string {
    let point: number;
    description = description.slice(0,220);
    if(description.length === 220) {
      for(let j: number = 0; j<description.length; j++) {
        if(description[j] === '.')
          point = j+1;
      }
    }
    return description.slice(0,point);
  }

}
