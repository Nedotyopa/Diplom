import {Component, OnDestroy, OnInit} from '@angular/core';
import { User } from '../../model/user';
import { UserService } from '../../service/user.service';
import { Subscription } from 'rxjs/internal/Subscription'
import { NgxSpinnerService } from 'ngx-spinner';
import { LoginEventService } from "../../service/login-event.service";
import { AdminUserService } from "../../service/admin-user.service";
import { Router } from "@angular/router";
import { Debt } from "../../model/debt";
import { SubscriptionService } from "../../service/subscription.service";
import {Count} from "../../model/count";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit, OnDestroy {

  public users: User[] = [];
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService, private loadingService: NgxSpinnerService,
              private loginEventService: LoginEventService, private adminUserService: AdminUserService,
              private router: Router, private subscriptionService: SubscriptionService) { }

  ngOnInit() {
    this.loadUsers();
    this.loginEventService.skipClicked.subscribe( value => {
      if(value == true) {
        this.router.navigate(['']);
      }
    })
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  private loadUsers(): void {
    this.loadingService.show();
    this.subscriptions.push(this.userService.getUsers().subscribe(newUsers => {
      this.users = newUsers;

      this.loadingService.hide();
    }))
  }

  public _deleteUser(userId: string): void {
    this.subscriptions.push(this.userService.deleteUser(userId).subscribe(() => {
      this.updateUsers();
    }))
  }

  public banUser(userId: string): void {
    this.subscriptions.push(this.userService.banUser(userId).subscribe(() => {
      this.updateUsers();
    }))
  }

  public checkStatus(ban: string): string {
    if(ban)
      return "Unban";
    else
      return "Ban";
  }

  private updateUsers(): void {
    this.loadUsers();
  }

  public getSubscriptions(login: string): void {
    this.adminUserService.putLogin(login);
    this.router.navigate(['/subscriptions'])
  }

  public updateDebt(user: User): void {
    this.loadingService.show();
    let debt: Debt = new Debt();
    debt.id = user.id;
    debt.debt = user.debt;

    this.subscriptions.push(this.userService.updateDebt(debt).subscribe(() => {
      this.loadingService.hide();
      this.loadUsers();
    }))
  }

}
