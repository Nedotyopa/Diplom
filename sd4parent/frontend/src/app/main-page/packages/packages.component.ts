import {Component, OnDestroy, OnInit} from '@angular/core';
import { Package} from "../../model/package";
import { PackageService } from "../../service/package.service";
import { Subscription } from "rxjs/internal/Subscription"
import { NgxSpinnerService } from 'ngx-spinner';
import { PostPackageService } from "../../service/post-package.service";

@Component({
  selector: 'app-packages',
  templateUrl: './packages.component.html',
  styleUrls: ['./packages.component.css']
})
export class PackagesComponent implements OnInit, OnDestroy {

  public packages: Package[];
  private subscriptions: Subscription[] = [];
  public pages: number[] = [];
  public total: number;
  public currentPage: number = 1;
  public quantity: number = 6;

  constructor(private packageService: PackageService, private loadingService: NgxSpinnerService,
              private postPackageService: PostPackageService) { }

  ngOnInit() {
    this.getPackagesByPage(1);
    this.getTotalPages();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public getPackagesByPage(page: number): void {
    this.loadingService.show();
    this.subscriptions.push(this.packageService.getPackagesByPage(page, this.quantity).subscribe(bundles => {
      this.packages = bundles as Package[];
      this.loadingService.hide();
    }))
  }

  private getTotalPages(): void {
    this.subscriptions.push(this.packageService.getTotalPages(this.quantity).subscribe(totalPages => {
      this.total = totalPages;
      this.pages = [];
      console.log(this.total);
      for(let i=1; i<=totalPages; i++) {
        this.pages.push(i);
      }
    }))
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

  public getPrice(pack: Package): number {
    let price: number = 0;

    for(let post of pack.posts) {
      price += post.price;
    }
    return price;
  }

  public setType(): void {
    this.postPackageService.setShow("posts");
  }

  public setQuantity(qt: number): void {
    this.quantity = qt;
    this.getPackagesByPage(1);
    this.currentPage = 1;
    this.getTotalPages();
  }

}
