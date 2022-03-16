import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {PostService} from "../../service/post.service";
import {ActivatedRoute} from "@angular/router"
import {NgxSpinnerService} from "ngx-spinner";

import { SubscribeCondition } from "../../model/subscribeCondition";
import { Package } from "../../model/package";
import { PackageService } from "../../service/package.service";
import { SubscriptionPost } from "../../model/subscription";
import { TransactionService } from "../../service/transaction.service";
import { SubscriptionService } from "../../service/subscription.service";
import { AuthService } from "../../service/auth.service";
import { LoginEventService } from "../../service/login-event.service";
import {User} from "../../model/user";
import { UserService } from "../../service/user.service";
import {Post} from "../../model/post";
import {PackageSubscription} from "../../model/packageSubscription";

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent implements OnInit, OnDestroy {

  public modalRef: BsModalRef;
  public pack: Package;
  private subscriptionPost: SubscriptionPost = new SubscriptionPost();
  private packageSubscription: PackageSubscription = new PackageSubscription();
  private subscriptions: Subscription[] = [];
  private packageId: string;
  public numbers: number[] = [1,2,3,4,5,6,7,8,9,10,11,12];
  public resultPrice: number = 0;
  public auth: boolean = true;
  public currentDuration: number = 6;
  public user: User;
  public subscribeAccess: string = "Successful";

  constructor(private modalService: BsModalService, private route: ActivatedRoute,
              private packageService: PackageService, private loadingService: NgxSpinnerService,
              private transactionService: TransactionService, private subscriptionService: SubscriptionService,
              private authService: AuthService, private loginEventService: LoginEventService,
              private userService: UserService) { }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      this.packageId = params.get('id');
    });
    if(this.authService.getUsername() != null)
      this.getUser();
    this.loadPackage(this.packageId);
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  private loadPackage(packageId: string) {
    this.loadingService.show();
    this.subscriptions.push(this.packageService.getPackage(packageId).subscribe(bundle => {
      this.pack = bundle;
      this.packagePrice(bundle, 6);
    }))
  }

  private closeModal(): void {
    this.modalRef.hide();
  }

  public _openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  private computePrice(packDiscount, duration, discount, price): void {

    let currentCondition = new SubscribeCondition();
    currentCondition.price = price;
    currentCondition.discount = discount + packDiscount;
    currentCondition.duration = duration;

    this.subscriptions.push(this.subscriptionService.computePrice(currentCondition).subscribe(result => {
      this.resultPrice += result.price;
    }))
  }

  public packagePrice(pack: Package, duration: number): void {
    this.resultPrice = 0;
    for(let post of pack.posts) {
      this.computePrice(pack.discount, duration, post.discount, post.price);
    }
  }

  private getUser(): void {
    this.subscriptions.push(this.userService.getUserByLogin(this.authService.getUsername()).subscribe(account => {
      this.user = account as User;
    }))
  }

  private createSubscription(post: Post): SubscriptionPost {

    let tempSub: SubscriptionPost = new SubscriptionPost();
    tempSub.user = this.user;
    tempSub.post = post;
    tempSub.duration = this.currentDuration;
    tempSub.cost = post.price;
    this.subscriptionPost = tempSub;
    return tempSub;
  }

  private createPackageSubscription(pack: Package): PackageSubscription {
    let subscriptionPosts: SubscriptionPost[] = [];
    for(let post of pack.posts) {
      subscriptionPosts.push(this.createSubscription(post));
      console.log(subscriptionPosts);
    }
    this.packageSubscription.subscriptions = subscriptionPosts;
    console.log(this.packageSubscription);
    return this.packageSubscription;

  }

  public subscribe(pack: Package) {
    this.loadingService.show();
    this.subscriptionService.savePackageSubscription(this.createPackageSubscription(pack)).subscribe(result => {
      console.log(result.stringResponse);
      if(result.stringResponse.endsWith(",")) {
        result.stringResponse = "You are already subscribed to: " + result.stringResponse.substring(0, result.stringResponse.length - 1);
      }
      if(result.stringResponse == "Successful")
        this.closeModal();
      this.subscribeAccess = result.stringResponse;
      this.loadingService.hide();
    })
  }

}
