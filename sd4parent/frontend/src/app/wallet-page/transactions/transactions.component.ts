import {Component, OnDestroy, OnInit} from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription'
import { Router } from "@angular/router";

import { Transaction } from '../../model/transaction'
import { TransactionService } from "../../service/transaction.service";
import { AuthService } from "../../service/auth.service";
import { WalletDataService } from "../../service/wallet-data.service";
import { ChargeEventService } from "../../service/charge-event.service";

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit, OnDestroy {

  public transactions: Transaction[];
  public chargeTransactions: Transaction[] = [];
  public refillTransactions: Transaction[] = [];
  private subscriptions: Subscription[] = [];
  public currentType = 'ALL';
  public handler: boolean = true;

  constructor(private transactionService: TransactionService, private authService: AuthService,
              private walletDataService: WalletDataService, private router: Router,
              private chargeEventService: ChargeEventService) { }

  ngOnInit() {
    if(this.authService.getUsername() != null) {
      if(this.authService.getRole() == "ADMIN") {
        this.handler = false;
      }
    }
    else
      this.router.navigate(['']);

    this.loadTransactions(this.authService.getUsername());

    this.walletDataService.skipClicked.subscribe(value => {
      if(value)
        this.loadTransactions(this.authService.getUsername());
    })
    this.chargeEventService.skipClicked.subscribe(value => {
      if(value == true)
        this.loadTransactions(this.authService.getUsername());
    })
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  loadTransactions(login: string) {
    this.subscriptions.push(this.transactionService.getTransactionsByLogin(login).subscribe(actions => {
     this.transactions = actions as Transaction[];
     for(let action of actions) {
       if(action.action == "MINUS")
         this.chargeTransactions.push(action);
       else
         this.refillTransactions.push(action);
     }
     console.log("Transactions: " + this.transactions);
    }))
  }

  public _subDate(date: string): string {
    return date.substring(0, 10);
  }

  public setType(type: string): void {
    this.currentType = type;
  }

}
