import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from "@angular/router";
import {Ng2Webstorage} from 'ngx-webstorage';
import { HttpModule } from '@angular/http';
import { NgxSpinnerModule } from 'ngx-spinner';
import { FormsModule }   from '@angular/forms';

import { WalletPageComponent } from './wallet-page.component';
import { WalletComponent } from "./wallet/wallet.component";
import { ExchangeService } from "../service/exchange.service";
import { TransactionsComponent } from './transactions/transactions.component';

@NgModule({
  imports: [
    CommonModule,
    Ng2Webstorage,
    NgxSpinnerModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: 'wallet',
        component: WalletPageComponent
      }
    ])
  ],
  declarations: [
    WalletPageComponent,
    WalletComponent,
    TransactionsComponent
  ],
  providers: [ExchangeService]
})
export class WalletPageModule { }
