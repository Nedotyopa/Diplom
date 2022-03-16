import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubscriptionsPageComponent } from './subscriptions-page.component';
import { SubscriptionsComponent } from "./subscriptions/subscriptions.component";
import { RouterModule } from "@angular/router";
import { NgxSpinnerModule } from 'ngx-spinner';
import {Ng2Webstorage} from 'ngx-webstorage';
import { SharedModule } from "../shared/shared.module";

@NgModule({
  imports: [
    CommonModule,
    NgxSpinnerModule,
    Ng2Webstorage,
    SharedModule,
    RouterModule.forRoot([
      {
        path: 'subscriptions',
        component: SubscriptionsPageComponent
      }
    ])
  ],
  declarations: [
    SubscriptionsPageComponent,
    SubscriptionsComponent
  ],
  bootstrap: [SubscriptionsPageComponent]
})
export class SubscriptionsPageModule { }
