import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription'
import { NgxSpinnerService } from 'ngx-spinner';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";

import { UserService } from "../../service/user.service";
import { Transaction } from "../../model/transaction";
import { TransactionService } from "../../service/transaction.service";
import { WalletService } from "../../service/wallet.service";
import { MoneyOperation } from "../../model/moneyOperation";
import { AuthService } from "../../service/auth.service";
import {Wallet} from "../../model/wallet";
import { WalletDataService } from "../../service/wallet-data.service";
import { LoginEventService } from "../../service/login-event.service";
import { ChargeEventService} from "../../service/charge-event.service";

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.css']
})
export class WalletComponent implements OnInit, OnDestroy {

  public wallet: Wallet;
  public  amount: number;
  private subscriptions: Subscription[] = [];
  public modalRef: BsModalRef;
  private transaction: Transaction = new Transaction();
  private username: string;
  public handler: boolean = true;

  constructor(private transactionService: TransactionService, private userService: UserService,
              private loadingService: NgxSpinnerService, private modalService: BsModalService,
              private authService: AuthService, private walletService: WalletService,
              private walletDataService: WalletDataService, private loginEventService: LoginEventService,
              private router: Router, private chargeEventService: ChargeEventService) { }

  ngOnInit() {

    if(this.authService.getUsername() != null) {
      if(this.authService.getRole() == "ADMIN") {
        this.handler = false;
      }
    }
    else
      this.router.navigate(['']);

    this.loadWallet(this.authService.getUsername());
    this.loginEventService.skipClicked.subscribe( value => {
      if(value == true) {
        this.router.navigate(['']);
      }
    });
    this.chargeEventService.skipClicked.subscribe(value => {
      if(value == true)
        this.loadWallet(this.authService.getUsername());
    })
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe())
  }

  private loadWallet(login: string) {
    this.loadingService.show();
    this.subscriptions.push(this.userService.getUserByLogin(login).subscribe(account => {
      this.wallet = account.wallet as Wallet;
      console.log("Wallet: " + this.wallet.money);
      this.loadingService.hide();
    }));
  }

  public _openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  private closeModal(): void {
    this.modalRef.hide();
  }

  private updateWallet(): void {
    this.loadWallet(this.authService.getUsername());
  }

  private createTransaction(): Transaction {
    this.transaction.title = "Fill Up";
    this.transaction.amount = this.amount;
    this.transaction.action = "PLUS";
    this.transaction.wallet = this.wallet;
    return this.transaction;
  }

  public _fillUp(idWallet: string): void {

    let operation: MoneyOperation = new MoneyOperation();

    operation.amount = +this.amount;
    operation.id = idWallet;

    this.subscriptions.push(this.walletService.setMoney(operation).subscribe(result => {
      console.log(result);
    }));

    this.subscriptions.push(this.transactionService.saveTransaction(this.createTransaction()).subscribe(() => {
      this.walletDataService.setClicked();
      console.log("Fill up")
      this.updateWallet();
      this.closeModal();
    }));

  }



}
