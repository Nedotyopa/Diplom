import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import { NgxSpinnerModule } from 'ngx-spinner';

import { MainPageComponent } from './main-page.component';
import { PostsComponent } from "./posts/posts.component";
import { PackagesComponent } from './packages/packages.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    NgxSpinnerModule,
    RouterModule.forRoot([
      {
        path: '',
        component: MainPageComponent
      }
    ])
  ],
  declarations: [
    MainPageComponent,
    PostsComponent,
    PackagesComponent
  ]
})
export class MainPageModule { }
