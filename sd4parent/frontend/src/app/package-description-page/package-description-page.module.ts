import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import {RouterModule} from "@angular/router";
import { NgxSpinnerModule } from 'ngx-spinner';

import { PackageDescriptionComponent } from './package-description/package-description.component';
import { PackageDescriptionPageComponent } from './package-description-page.component';
import { SubscribeComponent } from './subscribe/subscribe.component';

@NgModule({
  imports: [
    CommonModule,
    ModalModule,
    NgxSpinnerModule,
    RouterModule.forRoot([
      {
        path: 'packages/:id',
        component: PackageDescriptionPageComponent
      }
    ])
  ],
  declarations: [PackageDescriptionComponent, PackageDescriptionPageComponent, SubscribeComponent]
})
export class PackageDescriptionPageModule { }
