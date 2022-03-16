import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import { SubscriptionPost } from "../../model/subscription";
import { SubscriptionService } from "../../service/subscription.service";
import { Subscription } from "rxjs/internal/Subscription"
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthService } from "../../service/auth.service";
import {Router} from "@angular/router";
import { LoginEventService } from "../../service/login-event.service";
import { AdminUserService } from "../../service/admin-user.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import { PostDataService } from "../../service/post-data.service";
import { SubscribeCondition } from "../../model/subscribeCondition";
import {Post} from "../../model/post";
import { SubscriptionRenewal } from "../../model/subscriptionRenewal";

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit, OnDestroy {

  public subscriptionPosts: SubscriptionPost[];
  private subscriptions: Subscription[] = [];
  private username: string;
  public handler: boolean = true;
  public modalRef: BsModalRef;
  public resultCondition: SubscribeCondition;
  public currentDuration: number = 6;
  public numbers: number[] = [1,2,3,4,5,6,7,8,9,10,11,12];
  public currentPost: Post;
  private currentId: string;

  constructor(private subscriptionService: SubscriptionService, private loadingService: NgxSpinnerService,
              private authService: AuthService, private router: Router,
              private loginEventService: LoginEventService, private adminUserService: AdminUserService,
              private modalService: BsModalService, private postDataService: PostDataService) { }

  ngOnInit() {
    this.checkUsername();
    this.loginEventService.skipClicked.subscribe( value => {
      if(value == true) {
        this.router.navigate(['']);
      }
    })
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  private loadPosts(login: string): void {
    this.loadingService.show();
    this.subscriptions.push(this.subscriptionService.getSubscriptionsByLogin(login).subscribe(subs => {
      this.subscriptionPosts = subs as SubscriptionPost[];
      this.loadingService.hide();
    }))
  }

  public cancelSubscription(subscriptionId: string): void {
    this.loadingService.show();
    this.subscriptions.push(this.subscriptionService.deleteSubscription(subscriptionId).subscribe(() => {
      this.updateSubscriptions();
    }))
  }

  private updateSubscriptions(): void {
    this.loadPosts(this.authService.getUsername());
  }

  public _shortDescription(description: string): string {
    let point: number;
    description = description.slice(0,792);
    if(description.length === 220) {
      for(let j: number = 0; j<description.length; j++) {
        if(description[j] === '.')
          point = j+1;
      }
    }
    return description.slice(0,point);
  }

  private checkUsername(): void {
    if(this.authService.getRole() == "ADMIN") {
      this.handler = false;
      this.adminUserService.getLogin().subscribe(login => {
        this.username = login;
      });
    }
    else if(this.authService.getRole() == "USER")
      this.username = this.authService.getUsername();
    else
      this.router.navigate(['']);

    this.loadPosts(this.username);
  }

  public openModal(template, sub: SubscriptionPost) {
    this.modalRef = this.modalService.show(template);
    this.currentPost = sub.post;
    this.currentId = sub.id;
    this._computePrice(6, sub.post.discount, sub.post.price);
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  public _computePrice(duration, discount, price): void {
    let currentCondition = new SubscribeCondition();
    currentCondition.price = price;
    currentCondition.discount = discount;
    currentCondition.duration = duration;

    this.subscriptions.push(this.subscriptionService.computePrice(currentCondition).subscribe( result => {
      this.resultCondition = result as SubscribeCondition;
    } ))
  }

  public extendSubscriptions(condition: SubscribeCondition): void {
    this.loadingService.show()

    let renewal: SubscriptionRenewal = new SubscriptionRenewal();
    renewal.id = this.currentId;
    renewal.cost = condition.price;
    renewal.duration = condition.duration;

    this.subscriptions.push(this.subscriptionService.extendSubscription(renewal).subscribe(() => {
      this.loadPosts(this.authService.getUsername());
      this.loadingService.hide();
      this.closeModal();
    }))
  }

}
